package com.flipkart.product.entity;

import java.util.UUID;

import lombok.Data;
@Data
public class Seller {

	UUID sellerID;
	String sellerName;
	int sellerRating;
}
