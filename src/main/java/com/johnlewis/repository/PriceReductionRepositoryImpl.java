package com.johnlewis.repository;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.johnlewis.domain.Category;
import com.johnlewis.domain.Product;

@Repository
public class PriceReductionRepositoryImpl implements PriceReductionRepository {
	

	/**
	 * get products from services by category id
	 * 
	 * @param categoryId
	 */
	@Override
	public List<Product> getProducts(Integer categoryId) {
		
		RestTemplate restTemplate = new RestTemplate();
		
		String resourceUrl = "https://jl-nonprod-syst.apigee.net/v1/categories/"+categoryId+"/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";
		
		ResponseEntity<Category> response = restTemplate.exchange(resourceUrl,
					HttpMethod.GET,
					null,
					new ParameterizedTypeReference<Category>(){});

		return response.getBody().getProducts();
	}
	
	
	

}
