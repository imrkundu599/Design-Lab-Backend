package com.Group2.InterestCalc.Resources;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;



@Entity
@IdClass(InterestRateId.class)
public class InterestRates{

	@Id
    private String accountType;
	@Id
    private String ageGroup;
	
    private double interstRate;
    
    @Column(columnDefinition = "integer default 1")
    private int numberOfInterestInAYear;

    public InterestRates() {
    }


    public InterestRates(String accountType, String ageGroup, double interstRate, int numberOfInterestInAYear) {

		this.accountType = accountType;
		this.ageGroup = ageGroup;
		this.interstRate = interstRate;
		this.numberOfInterestInAYear = numberOfInterestInAYear;
	}




	public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAgeGroup() {
        return this.ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public double getInterstRate() {
        return this.interstRate;
    }

    public void setInterstRate(double interstRate) {
        this.interstRate = interstRate;
    }

	public int getNumberOfInterestInAYear() {
		return numberOfInterestInAYear;
	}

	public void setNumberOfInterestInAYear(int numberOfInterestInAYear) {
		this.numberOfInterestInAYear = numberOfInterestInAYear;
	}
    

}

