package com.flipkart.product.service;
import com.flipkart.product.util.ProductConsts;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import com.flipkart.product.entity.Seller;

@Service
public class SellerService {
	
	public String createSeller(Seller sellerData)
	{
		Seller s1 = new Seller();
		s1.setSellerID(sellerData.getSellerID());
		s1.setSellerName(sellerData.getSellerName());
		s1.setSellerRating(sellerData.getSellerRating());
		if(ProductConsts.sellerMap.containsKey(sellerData.getSellerID()))
		{
			System.out.println("Seller already exists, updating info");
		}
		
		ProductConsts.sellerMap.put(sellerData.getSellerID(), s1);
		return "Seller successfully created/updated";
	}
	
	
	public HashMap<UUID,Seller> fetchAllSellers()
	{	
		return ProductConsts.sellerMap;
	}
	
	public String deleteSellerByID(UUID sellerID)
	{
		if(!ProductConsts.sellerMap.containsKey(sellerID))
			return "The seller you are trying to delete does not exist";
		else
		{
			ProductConsts.sellerMap.remove(sellerID);
			
			HashMap<String,HashSet<UUID>> subscriptions = ProductConsts.subscriptionData;
		
			Set<String> keySet = subscriptions.keySet();
			
			for(String key : keySet)
			{
				HashSet<UUID> subSet = subscriptions.get(key);
				for(UUID sub : subSet)
				{
					if(sub.equals(sellerID))
						subSet.remove(sub);
				}
			}
			return "Seller successfully deleted";
		}
	}
}
