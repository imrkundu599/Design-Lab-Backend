package com.Group2.InterestCalc.Controllers; 

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.Group2.InterestCalc.Repositary.RoleRepo;
import com.Group2.InterestCalc.Repositary.UserRepo;
import com.Group2.InterestCalc.Resources.Role;
import com.Group2.InterestCalc.Resources.User;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleRepo roleRepo;
	
	@DeleteMapping("/removeUser")
	public ResponseEntity<String> removeUser(@RequestBody String email){

		User user=userRepo.findByEmail(email);

		if(user!=null) {
			user.removeAllAuthorities();
			userRepo.delete(user);
		}
		else {
			System.out.println("not...");
		}
		return ResponseEntity.ok("Deleted..");						
	}
	
	@GetMapping("/viewAllUser")
	public ResponseEntity<List<User>> viewAllUser() {
		Role role=roleRepo.findById(2).get();
		try {
			return ResponseEntity.ok(userRepo.findByAuthoritiesNotContaining(role));						
		} catch (Exception e) {
			// TODO: handle exception
			return ResponseEntity.notFound().eTag("Not found any User!!").build();					
		}

	}
}
