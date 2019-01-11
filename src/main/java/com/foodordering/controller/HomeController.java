package com.foodordering.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodordering.dto.UserDTO;
import com.foodordering.entity.Restaurant;
import com.foodordering.entity.User;
import com.foodordering.service.RestaurantService;
import com.foodordering.service.UserService;

@Controller
public class HomeController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
    public String listRestaurants(Model model) {
        List<Restaurant> restaurants = restaurantService.getAllRestaurants();
        
        model.addAttribute("restaurants", restaurants);
        
        return "home";
    }
	
	@GetMapping("/register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", modelMapper.map(user, UserDTO.class));
		
		return "register";
	}
	
	@PostMapping("/register")
    public ModelAndView createNewUser(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (bindingResult.hasErrors()) {
            modelAndView.addObject("user", userDTO);
            modelAndView.setViewName("register");
        } else {
            if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
                modelAndView.addObject("errorMessage", "Password doesn't matches confirmation password!");
                modelAndView.addObject("user", userDTO);
                modelAndView.setViewName("register");
                return modelAndView;
            }

            User userExists = userService.findUserByEmail(userDTO.getEmail());
            if (userExists != null) {
                modelAndView.addObject("errorMessage", "User with given email already exists!");
                modelAndView.addObject("user", userDTO);
                modelAndView.setViewName("register");
                return modelAndView;
            }
            User user = modelMapper.map(userDTO, User.class);
            user = userService.saveUser(user);
            modelAndView.addObject("successMessage", "Successful registration! Please login!");
            modelAndView.setViewName("home");

        }
        return modelAndView;
    }
	
	@GetMapping("/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
	
}
