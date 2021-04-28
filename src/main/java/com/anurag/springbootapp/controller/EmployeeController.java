package com.anurag.springbootapp.controller;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.anurag.springbootapp.dao.EmployeeDao;
import com.anurag.springbootapp.dao.UserJpaDAO;
import com.anurag.springbootapp.domain.User;
import com.anurag.springbootapp.domain.UserAddress;
import com.anurag.springbootapp.exception.UserNotFoundException;
import com.anurag.springbootapp.service.QueryExecutionByJDBC;

import io.swagger.annotations.ApiOperation;

@RestController
public class EmployeeController {
	
	private static Logger logger=LoggerFactory.getLogger(EmployeeController.class.getName());
	
	@Autowired
	private EmployeeDao employeeDao;
	
	@Autowired
	private UserJpaDAO userJpaDao;
	
	@Autowired
	private MessageSource messageSource;
	
	@Autowired
	private QueryExecutionByJDBC plainOldJdbc;
	
	@GetMapping(value="/employee/{name}")
	@ApiOperation(code = 200, value = "Fetch Employee with employee name and a link to see all the employees", 
				  notes = "You can see Employee details for the username passed as well as a hyperlink to view rest of the employees", response = User.class)
	public User getEmployee(@PathVariable String name){
		logger.debug("Getting employee for name: {}",name);
		User emp=employeeDao.findByName(name);
		if(emp == null){
			//Custom message and Exception Handling implementation using CustomExceptionHandler
			throw new UserNotFoundException(String.format("User %s cannot be found in the system. Please try some other user.",name));
		}
		
		//Adding a link to all the employees in the response
		EntityModel<User> model = EntityModel.of(emp);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllEmployees());
		model.add(linkTo.withRel("all-employees"));
		return emp;
	}
	
	@GetMapping(value="/employee/{id}/{name}", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(code = 200, value = "Using Spring Data-JPA, fetch employee for emp id and emp name", notes = "Using Spring Data-JPA, fetch employee", response = User.class)
	public User getUserByIdAndName(@PathVariable Long id, @PathVariable  String name){
		logger.debug("Getting employee for id : {} and name: {}", id, name);
		
		//Using Spring Data JPA
		User user=employeeDao.findByIdAndName(id, name);
		
		//Using plain old JDBC
		//User user=plainOldJdbc.jdbcBoilerPlateCodeToExecuteQuery(id, name);
		
		//Using Spring's JDBC template
		//User user=plainOldJdbc.jdbcTemplateToExecuteQuery(id, name);
		return user;
	}
	
	@GetMapping(value="/employees")
	@ApiOperation(code = 200, value = "All user's detailed list", notes = "You can see all Users list", response = User.class)
	public List<User> getAllEmployees(){
		List<User> users= employeeDao.findAll();
		return users;	
	}	
	
	@PostMapping(value="/employee" , consumes=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(code = 200, value = "Save a User to the database", notes = "Will return an instance of ResponseEntity")
	public ResponseEntity<User> saveEmployee(@Valid@RequestBody User employee){
		if(employee == null || employee.getName() == null || employee.getDepartment() == null){
			throw new IllegalArgumentException("Employee cannot be null.");
		}
		User emp=employeeDao.save(employee);
		URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(emp.getEid()).toUri();
		//new ResponseEntity(body, httpHeaders, HttpStatus.ACCEPTED);
		return ResponseEntity.accepted().build();
	}
	
	@PostMapping(value="/employeeAndAddress/{userId}" , consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> saveEmployeeJpaAssociationExample(@PathVariable Long userId, @RequestBody UserAddress address){
		User user=userJpaDao.findUser(userId);
		User emp=plainOldJdbc.saveUserAndAddress(user, address);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping(value="/employeeAddresses", produces=MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(code = 200, value = "All user's detailed list", notes = "You can see all Users list", response = User.class)
	public List<User> getAllEmployeesAndAddresses(){
		List<User> users= plainOldJdbc.findAllData();
		for(User user:users){
			List<UserAddress> adds=user.getAddresses();
		}
		return users;	
	}
	
	@GetMapping(value="/hello-world-i18n")
	@ApiOperation(code = 200, value = "Demo test of internationalization feature", notes = "Uses LocalResolver and MessageResource. Default locale is US.", response = Locale.class)
	public String displayHelloWorldMessage(@RequestHeader(value="Accept-Language",required=false) Locale locale){
		return messageSource.getMessage("hello.world.message", null, locale);
	}
	

}
