package com.johnlewis.services;

import com.johnlewis.domain.LabelTypes;
import com.johnlewis.model.ProductModel;

import java.util.List;
import java.util.Optional;

public interface PriceReductionService {
	List<ProductModel> getProducts(Integer categoryId, Optional<LabelTypes> priceLabelType);
}