package com.anurag.springbootapp.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.anurag.springbootapp.dao.UserJpaDAO;
import com.anurag.springbootapp.domain.User;
import com.anurag.springbootapp.domain.UserAddress;

@Service
public class QueryExecutionByJDBC {
	
	private static final String DUMMY_EMP_NAME = "ABC";
	private static final String DUMMY_DEPTT_NAME = "XYZ";
	
	@Autowired
	private UserJpaDAO userJpaDAO;
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public User jdbcBoilerPlateCodeToExecuteQuery(Long id, String name){
		User user = new User(DUMMY_EMP_NAME, DUMMY_DEPTT_NAME);
		try {
			Connection conn=dataSource.getConnection();
			PreparedStatement ps =conn.prepareStatement("select * from User where EMP_ID=? and NAME=?");
			//setting query parameters
			ps.setLong(1, id);
			ps.setString(2, name);
			ResultSet resultSet=ps.executeQuery();
			
			if(resultSet.next()){
				user.setEid(resultSet.getLong("EMP_ID"));
				user.setName(resultSet.getString("NAME"));
				user.setDepartment(resultSet.getString("DEPARTMENT"));
				user.setDob(resultSet.getTimestamp("DATE_OF_BIRTH"));
			}
			ps.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public User jdbcTemplateToExecuteQuery(Long id, String name){
		String deptt = jdbcTemplate.queryForObject("select department from User where EMP_ID=? and NAME=?", new Object[]{id,name}, String.class);
		User user = new User(DUMMY_EMP_NAME, DUMMY_DEPTT_NAME);
		user.setDepartment(deptt);
		return user;
		
	}
	
	public void testEntityManagerOperations(){
		User jack=new User("Jack","Admin");
		Long jackEid=userJpaDAO.insertUser(jack);
		jack.setDepartment("New deptt");
		User jackFromPersistencContext=userJpaDAO.findUser(jackEid);
		User jackFromPersistencContext2=userJpaDAO.findUser(jackEid);
		Assert.isTrue("New deptt".equals(jackFromPersistencContext2.getDepartment()), "Both instances are same");
		Assert.isTrue(jack.getEid().equals(jackFromPersistencContext.getEid()), "Both instances have same ids");
		
	}
	
	public User saveUserAndAddress(User user, UserAddress userAddress){
		User jill = userJpaDAO.findUser(user.getEid());
		//User jill=new User("Jill","Admin");
		UserAddress address=new UserAddress("50, Binsar Apartments, Indirapuram");
		address.setUser(jill);
		UserAddress address2=new UserAddress("51, Media Apartments, Indirapuram");
		address2.setUser(jill);
		UserAddress address3=new UserAddress("52, Shipra Apartments, Indirapuram");
		address3.setUser(jill);
		List<UserAddress> addresses = new ArrayList<UserAddress>();
		addresses.add(address);
		addresses.add(address2);
		addresses.add(address3);
		jill.setAddresses(addresses);
		
		userJpaDAO.updateUser(jill);
		
		return jill;
		
	}

	public List<User> findAllData() {
		Map<String, String> singletonMap = Collections.singletonMap("anurag", "a");
		return userJpaDAO.findAllData();
	}
	
	

}
