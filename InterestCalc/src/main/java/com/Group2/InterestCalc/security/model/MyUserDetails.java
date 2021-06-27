package com.Group2.InterestCalc.security.model;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.Group2.InterestCalc.Resources.User;

public class MyUserDetails implements UserDetails{


	private String email;
	private String password;
	private Collection<GrantedAuthority> authorities;
	
	
	public MyUserDetails() {

	}

	public MyUserDetails(User user) {
		this.email=user.getEmail();
		this.password=user.getPassword();
		this.authorities=user.allAuthoritiesString().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	public MyUserDetails(String email, String password, Collection<GrantedAuthority> authorities) {

		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public Collection< GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.authorities;
	}
	

	@Override
	public String toString() {
		return "MyUserDetails [email=" + email + ", password=" + password + ", authorities=" + authorities + "]";
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
