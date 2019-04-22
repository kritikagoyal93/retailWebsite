package com.flipkart.product.entity;

import java.util.UUID;

import lombok.Data;

@Data
public class CustomerSubscription {

	String customerEmails;
	UUID sellerID;
}
