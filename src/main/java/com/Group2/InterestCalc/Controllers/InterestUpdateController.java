package com.Group2.InterestCalc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Group2.InterestCalc.Repositary.InterestRateRepo;
import com.Group2.InterestCalc.Resources.InterestRates;





@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class InterestUpdateController {
	@Autowired
	InterestRateRepo interestRateRepo;
	
	@PostMapping("/interestRates")
	public ResponseEntity<InterestRates> postIntersetRates(@RequestBody InterestRates intersetRates ) {
		try {
			return ResponseEntity.created(null).body(interestRateRepo.saveAndFlush(intersetRates));					
		}catch (Exception e) {
			System.out.println(e);
			return ResponseEntity.badRequest().eTag("Failed to create Interest Rates!!").body(null);						
		}
		
	}
	
	@PutMapping("/updateInterestRates")
	public ResponseEntity<InterestRates> updateInterstRates(@RequestBody InterestRates interestRates){
		try {
			return ResponseEntity.ok(interestRateRepo.save(interestRates));				
		} catch (IllegalArgumentException e) {
			// TODO: handle exception
			return ResponseEntity.badRequest().eTag("Failed to update Interest Rates!!").body(null);				
		}
	}
}
