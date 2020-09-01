package com.anurag.springbootapp.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Table(name = "User")
@ApiModel("All the details of a User")
@Entity
public class User {
	
	public User(String name, String deptt){
		this.name=name;
		this.department=deptt;
		this.dob=new Date();
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "EMP_ID", updatable = false, nullable = false)
	private Long eid;
	
	@Column(name = "NAME")
	@Size(min=3, message="Name cannot be less than 3 characters")
	@ApiModelProperty(notes="Name should be atlteast 3 characters")
	private String name;
	
	@Column(name = "DEPARTMENT")
	@NotEmpty(message="Department name cannot be blank")
	@ApiModelProperty(notes="Department name cannot be left blank")
	private String department;
	
	@Column(name = "DATE_OF_BIRTH")
	@Temporal(TemporalType.DATE)
	@ApiModelProperty(notes="Date of Birth cannot be a past date")
	private Date dob;
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy="user")
	private List<UserAddress> addresses = new ArrayList<UserAddress>();	
	
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Long getEid() {
		return eid;
	}
	public void setEid(Long eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public List<UserAddress> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<UserAddress> addresses) {
		this.addresses = addresses;
	}
	
	@Transient
	private ObjectMapper jsonMapper=new ObjectMapper();
	
	@Override
	public String toString() {
		return String.format("User: %s", name);
	}
	
}
