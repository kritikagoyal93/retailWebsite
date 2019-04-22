package com.retail.product.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Customer")
public class Customer {
	
	@Id 
	@Column(name = "ID")
	UUID customerID;
	@Column(name = "FirstName")
	String customerfirstName;
	@Column(name = "LastName")
	String customerLastName;
	@Column(name = "EmailAddress")
	String customerEmail;
	

}
