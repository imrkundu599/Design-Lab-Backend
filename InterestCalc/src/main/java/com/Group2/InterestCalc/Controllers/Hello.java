package com.Group2.InterestCalc.Controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "http://localhost:4200")
class Hello{
	@GetMapping("/hello")
	public String hello() {
		return "hello Pritam";
	}
}