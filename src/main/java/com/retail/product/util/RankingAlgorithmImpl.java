package com.retail.product.util;

import com.retail.product.entity.ProductPost;

public class RankingAlgorithmImpl implements RankingAlgorithm {

	@Override
	public int compare(ProductPost p1, ProductPost p2) {
		
		if(p1.getCreationTime() != null)
			return p2.getCreationTime().compareTo(p1.getCreationTime());
			else
			return -1;

	}

}
