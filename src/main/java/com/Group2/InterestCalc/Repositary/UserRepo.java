package com.Group2.InterestCalc.Repositary;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Group2.InterestCalc.Resources.Role;
import com.Group2.InterestCalc.Resources.User;


@Repository
public interface UserRepo extends JpaRepository<User, String> {
	
	public User findByEmail(String email);

	public List<User> findByAuthoritiesNot(Role role) ;
	
	public List<User> findByAuthoritiesNotContaining(Role role) ;
}
