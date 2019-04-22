package com.johnlewis.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.johnlewis.domain.LabelTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.johnlewis.converter.ProductToProductModelConverter;
import com.johnlewis.model.ProductModel;
import com.johnlewis.repository.PriceReductionRepository;

@Service
public class PriceReductionServiceImpl implements PriceReductionService {
	
	@Autowired
	private PriceReductionRepository priceReductionRepository;

	@Override
	public List<ProductModel> getProducts(Integer categoryId, Optional<LabelTypes> priceLabelType) {
		
		// converter
		ProductToProductModelConverter productConverter = new ProductToProductModelConverter();
		
		// convert product to productModel and map elements to productModel list
		List<ProductModel> productModels = priceReductionRepository.getProducts(categoryId).stream().map(product ->
								{ return productConverter.convert(product, priceLabelType);}
						).collect(Collectors.toList());
		
		return productModels;
	}

	

}
