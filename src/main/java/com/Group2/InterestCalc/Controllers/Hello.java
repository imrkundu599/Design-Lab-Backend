package com.Group2.InterestCalc.Controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
@CrossOrigin(origins = "http://localhost:4200")
class Hello{
	
	
	@GetMapping("/hello")
	public ResponseEntity<String> hello() {
		/*
		 * simple hello endpoint for
		test the application
		*/
		
  		return ResponseEntity.ok("Hello Pritam!!");

	}
}