package com.retail.product.util;

import java.util.Comparator;

import com.retail.product.entity.ProductPost;

public interface RankingAlgorithm extends Comparator<ProductPost>{
	
	public int compare(ProductPost p1, ProductPost p2);
}
