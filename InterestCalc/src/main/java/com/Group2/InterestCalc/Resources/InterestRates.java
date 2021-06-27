package com.Group2.InterestCalc.Resources;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;



@Entity

public class InterestRates{
    @Id
    @GeneratedValue
    private long InterestRateId;
    private String accountType;
    private String ageGroup;
    private double interstRate;

    public InterestRates() {
    }

    public InterestRates(long InterestRateId, String accountType, String ageGroup, double interstRate) {
        this.InterestRateId = InterestRateId;
        this.accountType = accountType;
        this.ageGroup = ageGroup;
        this.interstRate = interstRate;
    }

    public long getInterestRateId() {
        return this.InterestRateId;
    }

    public void setInterestRateId(long InterestRateId) {
        this.InterestRateId = InterestRateId;
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

}

