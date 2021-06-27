package com.Group2.InterestCalc.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Group2.InterestCalc.Repositary.RoleRepo;
import com.Group2.InterestCalc.Repositary.UserRepo;
import com.Group2.InterestCalc.Resources.RegisterUser;
import com.Group2.InterestCalc.Resources.ResponseUser;
import com.Group2.InterestCalc.Resources.Role;
import com.Group2.InterestCalc.Resources.User;
import com.Group2.InterestCalc.security.model.AuthecticationResponse;
import com.Group2.InterestCalc.security.model.AuthenticationRequest;
import com.Group2.InterestCalc.security.service.MyUserDetailsService;
import com.Group2.InterestCalc.security.util.JwtUtil;





@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
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
	public ResponseEntity<ResponseUser> registerUser(@RequestBody RegisterUser registerUser) throws Exception { 
		try {
			User user=new User(registerUser);
			Optional<Role> role=roleRepo.findById(1);
			if(role.isPresent()) {
				user.insertAuthority(role.get());
				ResponseUser newUser=new ResponseUser(userRepo.saveAndFlush(user));	
				return ResponseEntity.created(null).body(newUser);
			}
			else {
				Role roleAuth=new Role(1,"ROLE_USER");
				user.insertAuthority(roleAuth);

				ResponseUser newUser=new ResponseUser(userRepo.saveAndFlush(user));
				return ResponseEntity.created(null).body(newUser);
				
			}
		}catch (Exception e) {
			return ResponseEntity.badRequest().eTag("Failed to Register!!").body(null);
		}		
		
	}
	
	@GetMapping("/hello")
	public String hello2() {
		return "hello user";
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthecticationResponse> createAunthenticationStringString(@RequestBody AuthenticationRequest authenticationRequest) {
		User user=userRepo.findByEmail(authenticationRequest.getEmail());
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
					);
				}catch (BadCredentialsException e) {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).eTag("Bad Credentials").body(null);							

				}
		final UserDetails userDetails=userDetailervice.loadUserByUsername(authenticationRequest.getEmail());
		final String jwt=jwtUtil.generateToken(userDetails);
		return  ResponseEntity.ok(new AuthecticationResponse(jwt,user));			
		
	}
}
