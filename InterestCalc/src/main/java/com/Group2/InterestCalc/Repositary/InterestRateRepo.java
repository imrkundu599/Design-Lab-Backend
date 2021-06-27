package com.Group2.InterestCalc.Repositary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Group2.InterestCalc.Resources.InterestRates;


@Repository
public interface InterestRateRepo extends JpaRepository<InterestRates, Long>{

	public InterestRates findByAccountTypeAndAgeGroup(String accountType, String ageGroup);
}
