package com.foodordering.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
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
import org.springframework.web.servlet.ModelAndView;

import com.foodordering.dto.GroupOrderDTO;
import com.foodordering.dto.OrderDTO;
import com.foodordering.dto.RestaurantDTO;
import com.foodordering.dto.UserDTO;
import com.foodordering.entity.GroupOrder;
import com.foodordering.entity.Order;
import com.foodordering.entity.Restaurant;
import com.foodordering.entity.User;
import com.foodordering.service.EmailService;
import com.foodordering.service.GroupOrderService;
import com.foodordering.service.OrderService;
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

	@Autowired
	private GroupOrderService groupOrderService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private EmailService emailService;

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

	@GetMapping("/restaurant-details/{id}")
	public ModelAndView viewRestaurantDetails(@PathVariable("id") String id) {
		ModelAndView modelAndView = new ModelAndView("restaurant-details");

		Restaurant restaurant = restaurantService.getRestaurantById(UUID.fromString(id));
		modelAndView.addObject("restaurant", modelMapper.map(restaurant, RestaurantDTO.class));

		return modelAndView;
	}

	@GetMapping("/add-group-order/{id}")
	public ModelAndView createGroupOrder(@PathVariable("id") String restaurantId) {
		ModelAndView modelAndView = new ModelAndView("add-group-order");
		GroupOrderDTO groupOrderDto = new GroupOrderDTO();
		groupOrderDto.setRestaurantIdString(restaurantId);
		modelAndView.addObject("groupOrder", groupOrderDto);

		return modelAndView;
	}

	@PostMapping("/add-group-order")
	public ModelAndView createGroupOrder(@Valid @ModelAttribute("groupOrder") GroupOrderDTO groupOrder,
			BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("groupOrder", groupOrder);
			modelAndView.setViewName("add-group-order");
		} else {
			Restaurant restaurant = restaurantService
					.getRestaurantById(UUID.fromString(groupOrder.getRestaurantIdString()));
			groupOrder.setRestaurant(modelMapper.map(restaurant, RestaurantDTO.class));
			groupOrder.setCreated(new Date());

			groupOrder = modelMapper.map(
					groupOrderService.saveGroupOrder(modelMapper.map(groupOrder, GroupOrder.class)),
					GroupOrderDTO.class);

			long timestampForMail = groupOrder.getTimeout() * 60 * 1000 + 5000;
			emailService.emailTimer(timestampForMail, modelMapper.map(groupOrder, GroupOrder.class));

			OrderDTO orderDto = new OrderDTO();
			orderDto.setGroupOrderIdString(groupOrder.getId().toString());
			modelAndView.addObject("groupOrder", modelMapper.map(groupOrder, GroupOrderDTO.class));
			modelAndView.addObject("order", orderDto);
			modelAndView.setViewName("group-order");
			;
		}

		return modelAndView;
	}

	@GetMapping("/group-order/{id}")
	public ModelAndView viewGroupOrder(@PathVariable("id") String id) {
		GroupOrder groupOrder = groupOrderService.getGroupOrderById(UUID.fromString(id));

		ModelAndView modelAndView = new ModelAndView("group-order");

		modelAndView.addObject("groupOrder", modelMapper.map(groupOrder, GroupOrderDTO.class));
		OrderDTO orderDto = new OrderDTO();
		orderDto.setGroupOrderIdString(groupOrder.getId().toString());

		double total = orderService.getSumAllByGroupOrder(groupOrder);
		modelAndView.addObject("total", total);
		modelAndView.addObject("order", orderDto);
		return modelAndView;
	}

	@PostMapping("/add-order")
	public ModelAndView createOrder(@Valid @ModelAttribute("order") OrderDTO orderDto, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView("group-order");
		GroupOrder groupOrder = groupOrderService.getGroupOrderById(UUID.fromString(orderDto.getGroupOrderIdString()));
		orderDto.setGroupOrder(modelMapper.map(groupOrder, GroupOrderDTO.class));
		if (bindingResult.hasErrors()) {
			modelAndView.addObject("order", orderDto);
			modelAndView.addObject("groupOrder", modelMapper.map(groupOrder, GroupOrderDTO.class));
		} else {
			orderService.saveOrder(modelMapper.map(orderDto, Order.class));
			modelAndView.addObject("groupOrder", modelMapper.map(groupOrder, GroupOrderDTO.class));
			double total = orderService.getSumAllByGroupOrder(groupOrder);
			modelAndView.addObject("total", total);
		}

		return modelAndView;
	}

	@GetMapping("/send-email/{id}")
	public ModelAndView sendEmail(@PathVariable("id") String id, Model model) throws MessagingException {

		ModelAndView modelAndView = new ModelAndView();
		GroupOrder groupOrder = groupOrderService.getGroupOrderById(UUID.fromString(id));

		List<Order> orders = groupOrder.getOrders();
		if (!orders.isEmpty()) {
			emailService.sendMail(groupOrder);
		} else {
			modelAndView.addObject("errorMessage", "Error with sending order, please check all information!");
			modelAndView.setViewName("home");
			return modelAndView;
		}
		modelAndView.setViewName("redirect:/");

		return modelAndView;
	}

}
