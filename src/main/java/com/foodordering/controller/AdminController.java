package com.foodordering.controller;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

import com.foodordering.dto.GroupOrderDTO;
import com.foodordering.dto.RestaurantDTO;
import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Restaurant;
import com.foodordering.service.GroupOrderService;
import com.foodordering.service.OrderService;
import com.foodordering.service.RestaurantService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private RestaurantService restaurantService;
	
	@Autowired
	private GroupOrderService groupOrderService;
	
	@Autowired
	private OrderService orderService;
	
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
	
	@GetMapping("/active-group-orders/{id}")
	public ModelAndView listAllActiveGroupOrders(@PathVariable String id) {
		ModelAndView  modelAndView = new ModelAndView();
        List<GroupOrder> activeGroupOrders = groupOrderService.getAllActiveGroupOrders(UUID.fromString(id));

        GroupOrder groupOrder = new GroupOrder();
        groupOrder.setOrders(orderService.getAllOrders());
        modelAndView.addObject("groupOrder", modelMapper.map(groupOrder, GroupOrderDTO.class));
        
        List<GroupOrderDTO> groupOrdersDTO = activeGroupOrders.stream()
                .map(groupOrder1 -> convertToDto(groupOrder1))
                .collect(Collectors.toList());
        modelAndView.addObject("activeGroupOrders", groupOrdersDTO);
        
        modelAndView.addObject("restaurant", restaurantService.getRestaurantById(UUID.fromString(id)));
        modelAndView.setViewName("active-group-orders");
		
		return modelAndView;
	}
	
	private GroupOrderDTO convertToDto(GroupOrder groupOrder) {
        GroupOrderDTO groupOrderDto = modelMapper.map(groupOrder, GroupOrderDTO.class);
        return groupOrderDto;
    }
}
