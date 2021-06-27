package com.Group2.InterestCalc.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;
import com.Group2.InterestCalc.security.filter.JwtRequestFilter;
import com.Group2.InterestCalc.security.service.MyUserDetailsService;



@Service
@EnableWebSecurity
public class SecurityConfigurer extends WebSecurityConfigurerAdapter{

	@Autowired
	private MyUserDetailsService userDetailsService;
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}

	@Bean
	public PasswordEncoder paswEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")
			//.authenticated()

			.antMatchers("/user/authenticate").permitAll()
			.antMatchers("/user/register").permitAll()
			
			.antMatchers("/user/**")
			.hasRole("USER")
			
			
			.antMatchers("/swagger-ui.html").permitAll()
			.antMatchers("/swagger-resources/**").permitAll()
			.antMatchers("/webjars/**").permitAll()
			.antMatchers("/v2/api-docs").permitAll()
			
			
			
			.antMatchers("/h2-console/**").permitAll()
			.antMatchers("/logout").permitAll()
			.anyRequest().authenticated()
			.and().sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.cors();
		
//		http.cors(c->{
//			
//			CorsConfigurationSource cs=r->{
//				CorsConfiguration cc=new CorsConfiguration();
//				cc.setAllowedOrigins(Arrays.asList("*"));
//				cc.setAllowedMethods(Arrays.asList("GET","POST"));
//				return cc;
//				
//			};
//			c.configurationSource(cs);
//		});
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		http.headers().frameOptions().disable();
	}



	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}
	
//    @Bean
//    public WebMvcConfigurer configure() {
//    	return new WebMvcConfigurer() {
//
//			@Override
//			public void addCorsMappings(CorsRegistry registry) {
//				registry.addMapping("/*").allowedOrigins("http://localhost:4200");
//			}
//    		
//		};
//    }
}
