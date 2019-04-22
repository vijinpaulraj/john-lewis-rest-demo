package com.johnlewis.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.johnlewis.domain.LabelTypes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.johnlewis.model.ProductModel;
import com.johnlewis.services.PriceReductionService;

@RunWith(SpringRunner.class)
@WebMvcTest(PriceReductionController.class)
public class PriceReductionControllerTest {
	
	private static final Integer CATEGORY_ID = 600001506;
	private static final Optional<LabelTypes> LABEL_TYPE = Optional.ofNullable(LabelTypes.ShowWasNow);
	private static final Optional<LabelTypes> EMPTY_LABEL_TYPE = Optional.empty();
	
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private PriceReductionService service;

//	@Before
//	public void setUp() throws Exception {
//	}

	@Test
	public void given_categoryId_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
		
		List<ProductModel> response = Arrays.asList(new ProductModel());
		
		given(service.getProducts(CATEGORY_ID, EMPTY_LABEL_TYPE))
			.willReturn(response);
		
		mockMvc.perform(get("/priceReduction/productsByCategoryId/{id}", CATEGORY_ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
	}
	
	
	@Test
    public void given_wrongTypeCategoryId_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
        mockMvc.perform(get("/priceReduction/productsByCategoryId/{id}", "str")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
	
	@Test
    public void given_categoryId_and_priceLabelType_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
        mockMvc.perform(get("/priceReduction/productsByCategoryId/{id}?priceLabelType={labelType}", CATEGORY_ID, LABEL_TYPE.get())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
    }
	
	@Test
    public void given_categoryId_and_wrongPriceLabelType_when_callGetDiscountedProductsByCAtegoryId_then_returnSuccess() throws Exception {
        mockMvc.perform(get("/priceReduction/productsByCategoryId/{id}?priceLabelType={labelType}", CATEGORY_ID, "wrongwrong")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
	
	

}
