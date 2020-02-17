package com.shubhajit.restservices.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@GetMapping("helloworld")
	public String helloWorld() {
		return "Hello World!";
	}

	@GetMapping("helloworld-bean")
	public UserDetails helloWorldBean() {
		return new UserDetails("Shubhajit", "Sahoo", "Bhubaneswar");
	}
}