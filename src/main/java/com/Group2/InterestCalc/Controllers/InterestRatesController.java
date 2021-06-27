package com.Group2.InterestCalc.Controllers;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Group2.InterestCalc.Repositary.InterestRateRepo;
import com.Group2.InterestCalc.Resources.InterestRates;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class InterestRatesController {
	
	@Autowired
	InterestRateRepo interestRateRepo;
	
	@GetMapping("/getInterestRate")
	public ResponseEntity<InterestRates> getInterestRates(@RequestParam String accountType, String ageGroup) {
		
		InterestRates interestRates=interestRateRepo.findByAccountTypeAndAgeGroup(accountType, ageGroup);
		if (interestRates!=null) {
			return  ResponseEntity.ok( interestRates);
		} else {
			return ResponseEntity.notFound().eTag("Interest Rates Not found!!").build();			
		}
		


		
	}
	
	@GetMapping("/interestCalculate")
	public ResponseEntity<HashMap<String, Double>> fdInterestCalculate(@RequestParam String accountType, String ageGroup, double principle , double time){	

		InterestRates interestRates= interestRateRepo.findByAccountTypeAndAgeGroup(accountType, ageGroup);	
		
		if (interestRates!=null) {
			
			double interstRate=interestRates.getInterstRate();
			double rate=interstRate/100;
			double numberOfInterestInAYear=interestRates.getNumberOfInterestInAYear();
			
			double total_ammount=principle  *  Math.pow( ( 1  + (  rate  /  numberOfInterestInAYear  )  ),		//base value

																(  time  *  numberOfInterestInAYear  )   );		//exponent
			
			double interest  =  total_ammount  -  principle;
			
			HashMap<String, Double> interesHashMap = new HashMap<>();
			interesHashMap.put("interest", interest);
			interesHashMap.put("total_ammount", total_ammount);
			interesHashMap.put("interestRate",interstRate);
			interesHashMap.put("numberOfInterestInAYear",numberOfInterestInAYear);
					

			return ResponseEntity.ok(interesHashMap);
		}
		else {
			return ResponseEntity.notFound().eTag("Interest Rates Not found!!").build();			
		}
		
	}

}
