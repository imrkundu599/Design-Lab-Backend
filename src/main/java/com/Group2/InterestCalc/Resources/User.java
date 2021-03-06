package com.Group2.InterestCalc.Resources;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
	@Id
	@GeneratedValue(generator = "userId_gen")
	@GenericGenerator(name = "userId_gen", 
	parameters = @Parameter(name = "prefix", value = "STB"),
	strategy = "com.Group2.InterestCalc.Genaretor.UserIdGenerator"
	)
	private String userId;
	
	private String firstName;
	private String lastName;
	
	@Column(nullable=false, unique=true)
	private String email;
	
	private String password; 
	
	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Role> authorities;
	
	
	public User() {

	}

	public User(RegisterUser registerUser) {
		this.email=registerUser.getEmail();
		this.firstName=registerUser.getFirstName();
		this.lastName=registerUser.getLastName();
		this.password=registerUser.getPassword();
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", authorities=" + authorities + "]";
	}

	public User(String userId, String firstName, String lastName, String email, HashSet<Role> authorities) {

		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.authorities = authorities;
	}
	public Set<Role> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(HashSet<Role> authorities) {
		this.authorities = authorities;
	}
	public void insertAuthority(Role role) {
		if(authorities==null) {

			authorities=new HashSet<Role>();
		}
		this.authorities.add(role);
	}
	public void removeAllAuthorities() {
		authorities.clear();
	}
	public List<String> allAuthoritiesString() {
		return  getAuthorities().stream().map(role->role.getRole()).collect(Collectors.toList());

	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
