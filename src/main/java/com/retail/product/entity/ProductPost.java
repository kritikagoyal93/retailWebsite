package com.retail.product.entity;

import java.util.Date;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductPost {

	UUID productID;
	UUID sellerID;
	String productName;
	float productCost;
	float userRating;
	Date creationTime;
}
