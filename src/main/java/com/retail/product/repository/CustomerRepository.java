package com.retail.product.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retail.product.entity.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long>{
	
	public Customer findBycustomerID(UUID uuid);

}
