package com.Group2.InterestCalc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Group2.InterestCalc.Repositary.InterestRateRepo;
import com.Group2.InterestCalc.Resources.InterestRates;

@RestController
@RequestMapping("/user")
public class InterestRatesController {
	
	@Autowired
	InterestRateRepo interestRateRepo;
	
	@GetMapping("/getInterestRate")
	public InterestRates getInterestRates(@RequestParam String accountType, String ageGroup) {
		
		return interestRateRepo.findByAccountTypeAndAgeGroup(accountType, ageGroup);
		
	}

}
