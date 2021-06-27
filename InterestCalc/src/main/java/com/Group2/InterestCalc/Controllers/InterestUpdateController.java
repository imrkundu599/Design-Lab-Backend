package com.Group2.InterestCalc.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Group2.InterestCalc.Repositary.InterestRateRepo;
import com.Group2.InterestCalc.Resources.InterestRates;





@RestController
@RequestMapping("/admin")
public class InterestUpdateController {
	@Autowired
	InterestRateRepo interestRateRepo;
	
	@PostMapping("/interestRates")
	public InterestRates postIntersetRates(@RequestBody InterestRates intersetRates ) {
		return interestRateRepo.saveAndFlush(intersetRates);
		

		
	}
}
