package com.retail.product.service;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

import com.retail.product.entity.ProductPost;
import com.retail.product.entity.Seller;
import com.retail.product.util.ProductConsts;

@Service
public class ProductService {
	
	public String createPost(ProductPost postData)
	{
		ProductPost newPost = new ProductPost();
		newPost.setSellerID(postData.getSellerID());
		newPost.setProductCost(postData.getProductCost());
		newPost.setProductName(postData.getProductName());
		newPost.setProductID(postData.getProductID());
		newPost.setUserRating(postData.getUserRating());
		newPost.setCreationTime(new Date());
		
		if(!ProductConsts.sellerMap.containsKey(postData.getSellerID()))
		return "Kindly create seller before you create posts";
		
		if(ProductConsts.postData.containsKey(postData.getSellerID()))
		{
			LinkedList<ProductPost> currPosts = ProductConsts.postData.get(postData.getSellerID());
			currPosts.add(newPost);
			ProductConsts.postData.put(postData.getSellerID(),currPosts);
		}
		else
		{
			LinkedList<ProductPost> currPosts = new LinkedList<ProductPost>();
			currPosts.add(newPost);
			ProductConsts.postData.put(postData.getSellerID(),currPosts);
		}
		return "Post successfully posted";
	}
	
	
	public LinkedList<ProductPost> getPostBySellerID(UUID sellerID)
	{	
		return ProductConsts.postData.get(sellerID);
	}
	
	public String deletePostBySellerAndProductID(UUID sellerID,UUID productID)
	{
		if(!ProductConsts.postData.containsKey(sellerID))
			return "The post you are trying to delete does not exist";
		else
		{
			LinkedList<ProductPost> postData = ProductConsts.postData.get(sellerID);
			boolean exists = false;
			for(ProductPost post : postData)
			{
				if(post.getProductID().equals(productID))
				{
					postData.remove(post);
					exists = true;
				}
			}
			
			ProductConsts.postData.put(sellerID, postData);
			if(exists)
				return "Post successfully deleted";
			else
				return "The post you are trying to delete does not exist";
		}
	}
}
