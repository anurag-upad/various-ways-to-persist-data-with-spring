package com.anurag.springbootapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.anurag.springbootapp.domain.User;

public interface EmployeeDao extends JpaRepository<User, Long>{

	User findByName(String empId);
	
	@Query("Select u from User u where u.eid in (?1) and u.name in (?2)")
	User findByIdAndName(Long id, String name);
	
	List<User> findByDepartment(String deptt);

}
