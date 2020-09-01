package com.anurag.springbootapp;

import org.springframework.boot.SpringApplication;

public class TestClass extends SpringApplication {
	
	@Override
	public void logStartupInfo(boolean root){
		System.out.println("trying to override logging");
		System.out.println("trying to override logging.....");
		System.out.println("trying to override logging...........");
		System.out.println("trying to override logging.................");
		
	}

}
