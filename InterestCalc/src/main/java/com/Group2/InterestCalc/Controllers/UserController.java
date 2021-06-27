package com.Group2.InterestCalc.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.CrossOrigin;

import com.Group2.InterestCalc.Repositary.RoleRepo;
import com.Group2.InterestCalc.Repositary.UserRepo;
import com.Group2.InterestCalc.Resources.Role;
import com.Group2.InterestCalc.Resources.User;
import com.Group2.InterestCalc.security.model.AuthecticationResponse;
import com.Group2.InterestCalc.security.model.AuthenticationRequest;
import com.Group2.InterestCalc.security.service.MyUserDetailsService;
import com.Group2.InterestCalc.security.util.JwtUtil;





@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4000")

public class UserController {
	@Autowired 
	UserRepo userRepo;
	
	@Autowired
	RoleRepo roleRepo;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailervice;

	@Autowired
	private JwtUtil jwtUtil;
	
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws Exception { 
		Optional<Role> role=roleRepo.findById(1);
		if(role.isPresent()) {
			List<Role> authorities=new ArrayList<Role>();
			authorities.add(role.get());
			user.setAuthorities(authorities);
//			MyUserDetails usrDetails=new MyUserDetails(user);
//			System.out.println(usrDetails);
			return userRepo.saveAndFlush(user);
			
			
			
			
			
		}else {
			throw new Exception("role not found");
		}
		
		
	}
	
	@GetMapping("/hello")
	public String hello2() {
		return "hello user";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAunthenticationStringString(@RequestBody AuthenticationRequest authenticationRequest) {
		
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
					);
				}catch (BadCredentialsException e) {
					return new ResponseEntity("unauthorize access",HttpStatus.UNAUTHORIZED);

				}
		final UserDetails userDetails=userDetailervice.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt=jwtUtil.generateToken(userDetails);
		return  ResponseEntity.ok(new AuthecticationResponse(jwt));

		
	}
}
