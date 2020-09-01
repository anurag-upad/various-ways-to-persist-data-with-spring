package com.anurag.springbootapp.service.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.anurag.springbootapp.dao.UserJpaDAO;
import com.anurag.springbootapp.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EntityManagerTest {
	

	@Autowired
	private UserJpaDAO userJpaDAO;
	
	@Test
	public void testEntityManagerOperations(){
		User jack=new User("Jack","Admin");
		//cannot test like this
		//userJpaDAO will be null, no bean found in context
		Long jackEid=userJpaDAO.insertUser(jack);
		User jackFromPersistencContext=userJpaDAO.findUser(jackEid);
		Assert.isTrue(jack.getDepartment().equals(jackFromPersistencContext.getDepartment()), "Both instances are same");
		Assert.isTrue(jack.getEid().equals(jackFromPersistencContext.getEid()), "Both instances have same ids");
		
		//User jill=new User("Jill","Finance");
		
	}
}
