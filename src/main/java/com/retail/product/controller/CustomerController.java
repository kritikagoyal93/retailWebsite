package com.retail.product.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.product.entity.Customer;
import com.retail.product.entity.ProductPost;
import com.retail.product.entity.Seller;
import com.retail.product.repository.CustomerRepository;
import com.retail.product.service.CustomerService;
import com.retail.product.service.ProductService;
import com.retail.product.service.SellerService;
import com.retail.product.util.RankingAlgorithmImpl;

import java.util.UUID;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerRepository custRep;
	
	@RequestMapping(value = "registerCustomer", method = RequestMethod.POST)
	public String createPost(@RequestBody Customer customerDetails)
	{
		
		return customerService.registerCustomer(customerDetails);
	}
	
	@RequestMapping(value = "fetchCustomer", method = RequestMethod.GET)
	public Customer fetchCustomer()
	{
		
		Customer cust = new Customer();
		cust.setCustomerEmail("kritika.goyal@sap.com");
		UUID uuid = UUID.randomUUID();
		cust.setCustomerID(uuid);
		custRep.save(cust);
		return custRep.findBycustomerID(uuid);
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
