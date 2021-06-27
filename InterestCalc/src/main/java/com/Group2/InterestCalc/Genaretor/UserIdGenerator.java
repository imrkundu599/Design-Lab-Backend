package com.Group2.InterestCalc.Genaretor;


import java.time.LocalTime;
import java.util.Properties;
import java.util.Random;  

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import com.Group2.InterestCalc.Resources.User;


public class UserIdGenerator 
implements IdentifierGenerator, Configurable {

  private String prefix;  
  
  @Override
  public String generate(
    SharedSessionContractImplementor session, Object obj) 
    throws HibernateException {
	  	  	
		  Random random = new Random();   
	     // Generates random integers 0 to 49  
		 Integer x = random.nextInt(858);  
		 User u=(User)obj;
		 LocalTime time=LocalTime.now();
		 String generatedIdString=prefix
				 +String.format("%03d", ((int)u.getFirstName().charAt(0)+time.getSecond()))
				 +String.format("%03d", ((int)u.getFirstName().charAt(1)+time.getMinute()))
				 +String.format("%03d", ((int)u.getLastName().charAt(0)+time.getHour()))
				 +String.format("%03d", time.getSecond()+time.getMinute()+time.getHour()+x);
	    
	     return generatedIdString;
	     
	     
	     
	 
  }

	@Override
	public void configure(Type type, Properties properties, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub
		prefix = properties.getProperty("prefix");
	}



}
