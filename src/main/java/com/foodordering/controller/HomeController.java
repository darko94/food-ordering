package com.foodordering.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodordering.dto.UserDTO;
import com.foodordering.entity.Restaurant;
import com.foodordering.entity.User;
import com.foodordering.service.RestaurantService;

@Controller
public class HomeController {

	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
	
}
