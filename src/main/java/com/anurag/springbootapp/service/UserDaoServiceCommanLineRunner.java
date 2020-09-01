package com.anurag.springbootapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.anurag.springbootapp.dao.UserJpaDAO;
import com.anurag.springbootapp.domain.User;

@Component
public class UserDaoServiceCommanLineRunner implements CommandLineRunner{
	
	private static final Logger log=LoggerFactory.getLogger(UserDaoServiceCommanLineRunner.class);
	
	@Autowired
	private UserJpaDAO userJpaDAO;

	@Override
	public void run(String... args) throws Exception {
		User jack=new User("Jack","Admin");
		Long jackEid=userJpaDAO.insertUser(jack);
		log.info("new instance of USER with id:{} has been created !!!!!!!!!!",jackEid);
		
	}

}
