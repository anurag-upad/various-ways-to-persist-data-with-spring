package com.anurag.springbootapp.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
//@Table(name = "UserAddress")
public class UserAddress {
	
	public UserAddress() {
	}
	
	public UserAddress(String addressLine) {
		super();
		this.addressLine = addressLine;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ADDRESS_ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name = "ADDRESS_LINE")
	private String addressLine;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	@JsonIgnore
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	
}
