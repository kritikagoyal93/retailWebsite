package com.retail.product.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.UUID;

import com.retail.product.entity.Customer;
import com.retail.product.entity.ProductPost;
import com.retail.product.entity.Seller;
import com.retail.product.util.ProductConsts;
import com.retail.product.util.RankingAlgorithm;

@Service
public class CustomerService {

	public String registerCustomer(Customer customerData) {
		if (ProductConsts.customerMap.containsKey(customerData.getCustomerEmail())) {
			Customer c1 = ProductConsts.customerMap.get(customerData.getCustomerEmail());
			{
				c1.setCustomerfirstName(customerData.getCustomerfirstName());
			}
			return "Successfully updated your details";
		}

		Customer cust = new Customer();

		cust.setCustomerfirstName(customerData.getCustomerfirstName());
		cust.setCustomerEmail(customerData.getCustomerEmail());

		ProductConsts.customerMap.put(customerData.getCustomerEmail(), cust);
		return "Registered successfully";
	}

	public String subscribeToSeller(String customerID, UUID sellerID) {
		if(!ProductConsts.customerMap.containsKey(customerID))
			return "Kindly create customer before you create a subscription";
		if(!ProductConsts.sellerMap.containsKey(sellerID))
			return "Kindly create seller before you create a subscription";
		
		
		if (ProductConsts.subscriptionData.containsKey(customerID)) {
			HashSet<UUID> sellerSet = ProductConsts.subscriptionData.get(customerID);
			{
				sellerSet.add(sellerID);
			}
			ProductConsts.subscriptionData.put(customerID, sellerSet);

		} else {
			HashSet<UUID> sellerSet = new HashSet<UUID>();
			sellerSet.add(sellerID);
			ProductConsts.subscriptionData.put(customerID, sellerSet);
		}
		return "Successfully subscribed";
	}
	
	
	
	public String unsubscribeToSeller(String customerID, UUID sellerID) {
		if (ProductConsts.subscriptionData.containsKey(customerID)) {
			HashSet<UUID> sellerSet = ProductConsts.subscriptionData.get(customerID);
			{
				sellerSet.remove(sellerID);
			}
			ProductConsts.subscriptionData.put(customerID, sellerSet);
			return "Successfully subscribed";
		}
		else
		{
			return "Check your input data";
		}
		
	}

	public LinkedList<ProductPost> fetchFeedForUser(String customerID, RankingAlgorithm rankingAlgo, int n) {
		if(!ProductConsts.customerMap.containsKey(customerID))
			return null;

		LinkedList<ProductPost> feedList = new LinkedList<ProductPost>();

		HashSet<UUID> sellers = ProductConsts.subscriptionData.get(customerID);
		int sellerPostCount = n / sellers.size();

		sellers.forEach(seller -> {
			LinkedList<ProductPost> currSellerProductList = ProductConsts.postData.get(seller);
			if(currSellerProductList!=null)
			feedList.addAll(currSellerProductList);

		});
		
		if(feedList == null)
			return null;
		
		Collections.sort(feedList, rankingAlgo);

		int count = 0;

		LinkedList<ProductPost> finalFeedList = new LinkedList<ProductPost>();
		HashMap<UUID, Integer> sellerCount = new HashMap<UUID, Integer>();
		for (int i = 0; i < feedList.size(); i++) {
			if (count < n) {

				if (sellerCount.containsKey(feedList.get(i).getSellerID())) {
					if (sellerCount.get(feedList.get(i).getSellerID()) > sellerPostCount) {
						continue;
					} else {
						sellerCount.put(feedList.get(i).getSellerID(),
								sellerCount.get(feedList.get(i).getSellerID()) + 1);
						finalFeedList.add(feedList.get(i));
						count++;
					}
				} else {
					sellerCount.put(feedList.get(i).getSellerID(), 1);
					finalFeedList.add(feedList.get(i));
					count++;
				}

			} else
				break;
		}

		return finalFeedList;
	}
}
