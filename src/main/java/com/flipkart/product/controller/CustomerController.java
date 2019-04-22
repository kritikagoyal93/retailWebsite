package com.flipkart.product.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flipkart.product.entity.Customer;
import com.flipkart.product.entity.ProductPost;
import com.flipkart.product.entity.Seller;
import com.flipkart.product.service.CustomerService;
import com.flipkart.product.service.ProductService;
import com.flipkart.product.service.SellerService;
import com.flipkart.product.util.RankingAlgorithmImpl;

import java.util.UUID;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "registerCustomer", method = RequestMethod.POST)
	public String createPost(@RequestBody Customer customerDetails)
	{
		return customerService.registerCustomer(customerDetails);
	}
	
	@RequestMapping(value = "getFeedForCustomer/{customerID}", method = RequestMethod.GET)
	public LinkedList<ProductPost> getFeedForCustomer(@PathVariable String customerID)
	{
		return customerService.fetchFeedForUser(customerID,new RankingAlgorithmImpl(), 10);
	}
	
	
	@RequestMapping(value = "subscribeToSeller/{customerID}/{sellerID}", method = RequestMethod.GET)
	public String subscribeToSeller(@PathVariable String customerID, @PathVariable UUID sellerID)
	{
		return customerService.subscribeToSeller(customerID,sellerID);
	}
	
	@RequestMapping(value = "unsubscribeToSeller/{customerID}/{sellerID}", method = RequestMethod.GET)
	public String unsubscribeToSeller(@PathVariable String customerID, @PathVariable UUID sellerID)
	{
		return customerService.unsubscribeToSeller(customerID,sellerID);
	}
	
}
