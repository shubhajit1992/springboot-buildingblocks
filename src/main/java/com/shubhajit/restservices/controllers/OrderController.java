package com.shubhajit.restservices.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shubhajit.restservices.entities.Order;
import com.shubhajit.restservices.entities.User;
import com.shubhajit.restservices.exceptions.OrderNotFoundException;
import com.shubhajit.restservices.exceptions.UserNotFoundException;
import com.shubhajit.restservices.repositories.OrderRepository;
import com.shubhajit.restservices.repositories.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found!!");
		}
		return userOptional.get().getOrders();
	}

	@PostMapping("/{userid}/orders")
	public void createOrder(@PathVariable Long userid, @RequestBody Order order) throws UserNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found!!");
		}
		User user = userOptional.get();
		order.setUser(user);
		orderRepository.save(order);
	}

	/*@GetMapping("/{userid}/orders/{orderid}")
	public Order getOrderByOrderId(@PathVariable Long userid, @PathVariable Long orderid)
			throws UserNotFoundException, OrderNotFoundException {
		Optional<User> userOptional = userRepository.findById(userid);
		Optional<Order> orderOptional = orderRepository.findById(orderid);
		Order order = null;

		if (!userOptional.isPresent()) {
			throw new UserNotFoundException("User not found!!");
		} else {
			if (!orderOptional.isPresent()) {
				throw new OrderNotFoundException("Order not found for user : " + userid);
			} else {
				order = orderOptional.get();
			}
		}
		return order;
	}*/
}