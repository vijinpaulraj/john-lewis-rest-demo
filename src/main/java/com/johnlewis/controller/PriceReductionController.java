package com.johnlewis.controller;

import com.johnlewis.domain.LabelTypes;
import com.johnlewis.model.ProductModel;
import com.johnlewis.services.PriceReductionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/priceReduction")
@Api(value="/priceReduction", description="Price Reduction")
public class PriceReductionController {

	@Autowired
	protected PriceReductionService priceReductionService;
	
	@ApiOperation(
			value = "Get price reduction by categoryId.",
			notes = "Returns products with price reduction with highest price reduction first. Price reduction is calculated using price.was - price.now. ",
			response = ProductModel.class,
			responseContainer = "List"
	)

	@ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 404, message = "The resource is not found")
    })

    @GetMapping(value = "/productsByCategoryId/{categoryId}")
	public List<ProductModel> getProductsByCategoryId(@PathVariable(required = true) Integer categoryId, @RequestParam(required = false) LabelTypes priceLabelType){
		
		Optional<LabelTypes> labelType = Optional.ofNullable(priceLabelType);
		
		return priceReductionService.getProducts(categoryId, labelType);
	}

}
