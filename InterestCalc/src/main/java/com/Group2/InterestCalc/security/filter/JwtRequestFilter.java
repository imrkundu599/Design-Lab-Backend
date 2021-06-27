package com.Group2.InterestCalc.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Group2.InterestCalc.security.model.MyUserDetails;
import com.Group2.InterestCalc.security.service.MyUserDetailsService;
import com.Group2.InterestCalc.security.util.JwtUtil;

import net.bytebuddy.asm.MemberSubstitution.Substitution.Chain;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private MyUserDetailsService userDetailService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authString=request.getHeader("Authorization");
		String email=null;
		String jwt=null;
		if (authString!= null && authString.startsWith("Bearer ")) {
			jwt=authString.substring(7);
			email=jwtUtil.extractUsername(jwt);
			
		}

		if(email!=null && jwt!=null && SecurityContextHolder.getContext().getAuthentication()==null) {

			UserDetails userDetails=this.userDetailService.loadUserByUsername(email);

			if(jwtUtil.validateToken(jwt, userDetails)) {

				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
						userDetails, null,userDetails.getAuthorities());
				
				usernamePasswordAuthenticationToken.setDetails(
						 new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
			
	
		}
		filterChain.doFilter(request, response);
	}

}
