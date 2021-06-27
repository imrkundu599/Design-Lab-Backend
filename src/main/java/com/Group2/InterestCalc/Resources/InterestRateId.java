package com.Group2.InterestCalc.Resources;

import java.io.Serializable;

public class InterestRateId implements Serializable {

    private String accountType;
    private String ageGroup;
    
	public InterestRateId() {

	}

	public InterestRateId(String accountType, String ageGroup) {
		this.accountType = accountType;
		this.ageGroup = ageGroup;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getAgeGroup() {
		return ageGroup;
	}

	public void setAgeGroup(String ageGroup) {
		this.ageGroup = ageGroup;
	}

	@Override
	public String toString() {
		return "InterestRateId [accountType=" + accountType + ", ageGroup=" + ageGroup + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + ((ageGroup == null) ? 0 : ageGroup.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterestRateId other = (InterestRateId) obj;
		if (accountType == null) {
			if (other.accountType != null)
				return false;
		} else if (!accountType.equals(other.accountType))
			return false;
		if (ageGroup == null) {
			if (other.ageGroup != null)
				return false;
		} else if (!ageGroup.equals(other.ageGroup))
			return false;
		return true;
	} 
	
}
