package com.retail.product.controller;

import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.retail.product.entity.Seller;
import com.retail.product.service.SellerService;

import java.util.UUID;

@RestController
public class SellerController {
	
	@Autowired
	SellerService sellerService;
	
	@RequestMapping(value = "createSeller", method = RequestMethod.POST)
	public String createBoard(@RequestBody Seller sellerData)
	{
		return sellerService.createSeller(sellerData);
	}
	
	@RequestMapping(value = "getAllSellers", method = RequestMethod.GET)
	public HashMap<UUID,Seller> fetchAllSellers()
	{
		return sellerService.fetchAllSellers();
	}
	
	@RequestMapping(value = "deleteSellerByID/{sellerID}", method = RequestMethod.GET)
	public String deleteSeller(@PathVariable UUID sellerID)
	{
		return sellerService.deleteSellerByID(sellerID);
	}
	
}
