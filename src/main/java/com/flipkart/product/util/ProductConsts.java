package com.flipkart.product.util;

import java.util.UUID;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

import com.flipkart.product.entity.*;
public class ProductConsts {
	
	public static HashMap<UUID,Seller> sellerMap = new HashMap<UUID,Seller>();
	public static HashMap<String,Customer> customerMap = new HashMap<String,Customer>();
	public static HashMap<UUID,LinkedList<ProductPost>> postData = new HashMap<UUID,LinkedList<ProductPost>>();
	public static HashMap<String,HashSet<UUID>> subscriptionData = new HashMap<String,HashSet<UUID>>();
}
