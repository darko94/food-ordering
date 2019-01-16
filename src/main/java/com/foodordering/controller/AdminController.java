package com.foodordering.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.foodordering.dto.RestaurantDTO;
import com.foodordering.entity.Restaurant;
import com.foodordering.service.RestaurantService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@GetMapping("/add-restaurant")
	public ModelAndView addRestaurant() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("add-restaurant");
		
		Restaurant restaurant = new Restaurant();
		modelAndView.addObject("restaurant", modelMapper.map(restaurant, RestaurantDTO.class));
		modelAndView.addObject("addOrEdit", "add");
		
		return modelAndView;
	}
	
	@PostMapping("/add-restaurant")
    public String addRestaurantPost(@Valid @ModelAttribute("restaurant") RestaurantDTO restaurantDto, BindingResult bindingResult, Model model, HttpServletRequest request) {

        if (bindingResult.hasErrors()) {
        	bindingResult.getAllErrors().stream().forEach(System.out::println);
            model.addAttribute("restaurant", restaurantDto);
            return "add-restaurant";
        } else {
            
            restaurantService.saveRestaurant(modelMapper.map(restaurantDto, Restaurant.class));

           
            return "redirect:/";
        }
    }
	
	@GetMapping("/edit-restaurant/{id}")
	public ModelAndView editRestaurant(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("add-restaurant");
		
		Restaurant restaurant = restaurantService.getRestaurantById(UUID.fromString(id));
		modelAndView.addObject("restaurant", modelMapper.map(restaurant, RestaurantDTO.class));
		modelAndView.addObject("addOrEdit", "edit");

		return modelAndView;
	}
	
	@GetMapping("/delete-restaurant/{id}")
	public String deleteRestaurant(@PathVariable("id") String id) {
		restaurantService.deleteRestaurantById(id);

		return "redirect:/";
	}
}
