package com.Group2.InterestCalc.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Group2.InterestCalc.Repositary.UserRepo;
import com.Group2.InterestCalc.Resources.User;
import com.Group2.InterestCalc.security.model.MyUserDetails;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user=userRepo.findByEmail(email);  
		if (user==null) {
			throw new UsernameNotFoundException("Not found any user by email"+email);
		}

		//return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
		return new MyUserDetails(user);
	}

}
