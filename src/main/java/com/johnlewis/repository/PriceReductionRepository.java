package com.johnlewis.repository;

import java.util.List;

import com.johnlewis.domain.Product;

public interface PriceReductionRepository {
	
	List<Product> getProducts(Integer categoryId);

}
