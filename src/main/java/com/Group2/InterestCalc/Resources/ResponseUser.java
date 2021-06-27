package com.Group2.InterestCalc.Resources;



public class ResponseUser {
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	public ResponseUser() {

	}
	
	public ResponseUser(User user) {
		this.email=user.getEmail();
		this.firstName=user.getFirstName();
		this.lastName=user.getLastName();
		this.userId=user.getUserId();
	}

	public ResponseUser(String userId, String firstName, String lastName, String email) {

		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


}
