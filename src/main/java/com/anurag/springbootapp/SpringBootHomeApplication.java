package com.anurag.springbootapp;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootHomeApplication {
	
	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(SpringBootHomeApplication.class);
	    //app.setBannerMode(Banner.Mode.OFF);
	   // app.setLazyInitialization(true);
	    //app.setDefaultProperties(new HashMap<String,Object>());
	    ApplicationContext applicationContext = app.run(args);
		//SpringApplication.run(SpringBootHomeApplication.class, args);
		//SpringApplication s=new SpringApplication();
		//s.setLazyInitialization(false);
	    
	    /*for (String string : applicationContext.getBeanDefinitionNames()) {
	    	System.out.println(string);
			
		}*/
		
	}

}
