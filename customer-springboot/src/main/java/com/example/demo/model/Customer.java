package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import java.io.Serializable;

@Entity
@Table(name="customer")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@SequenceGenerator(name="customerSequence", allocationSize=1, initialValue =1, sequenceName="customer_sequence11")
	
	@Id
	@GeneratedValue(generator="customerSequence")
	private long id;
	private String name;
	private String address;
	private String phone_number;
	private String email;
	
	@Column(name="id")
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name="name")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="address")
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	@Column(name="email")
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name="phone_number")
	public String getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
}