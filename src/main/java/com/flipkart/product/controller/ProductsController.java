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

import com.flipkart.product.entity.ProductPost;
import com.flipkart.product.entity.Seller;
import com.flipkart.product.service.ProductService;
import com.flipkart.product.service.SellerService;
import java.util.UUID;

@RestController
public class ProductsController {
	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value = "createPost", method = RequestMethod.POST)
	public String createPost(@RequestBody ProductPost postDetails)
	{
		return productService.createPost(postDetails);
	}
	
	@RequestMapping(value = "getPostBySellerID/{sellerID}", method = RequestMethod.GET)
	public LinkedList<ProductPost> getPostBySellerID(@PathVariable UUID sellerID)
	{
		return productService.getPostBySellerID(sellerID);
	}
	
	/*Safe assumption - you always have seller ID while deleting a post, as a seller can delete his own post and no1 else's*/
	
	@RequestMapping(value = "deletePostBySellerAndProductID/{sellerID}/{productID}", method = RequestMethod.GET)
	public String deletePost(@PathVariable UUID sellerID, @PathVariable UUID productID)
	{
		return productService.deletePostBySellerAndProductID(sellerID,productID);
	}
	
}
