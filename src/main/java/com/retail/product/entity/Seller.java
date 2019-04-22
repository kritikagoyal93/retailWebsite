package com.retail.product.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Data
@Entity
public class Seller {

	@Column(name = "SellerID")
	@Id
	UUID sellerID;
	@Column(name = "SellerName")
	String sellerName;
	@Column(name = "SellerRating")
	int sellerRating;
}
