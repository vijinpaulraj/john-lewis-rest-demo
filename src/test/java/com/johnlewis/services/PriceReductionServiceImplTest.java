package com.johnlewis.services;

import com.johnlewis.domain.Product;
import com.johnlewis.model.ProductModel;
import com.johnlewis.repository.PriceReductionRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;



@RunWith(SpringRunner.class)
public class PriceReductionServiceImplTest {
	
	private static final Integer CATEGORY_ID = 123;
	
    @TestConfiguration
    static class PriceReductionServicesImplTestContextConfiguration {

        @Bean
        public PriceReductionService priceReductionService() {
            return new PriceReductionServiceImpl();
        }
    }

    @Autowired
    private PriceReductionService priceReductionService;

    @MockBean
    private PriceReductionRepository priceReductionRepository;

    @Before
    public void setUp() throws Exception {

        Mockito.when(priceReductionRepository.getProducts(CATEGORY_ID))
                .thenReturn(new ArrayList<Product>());
    }

    @Test
    public void get_productModels() {
        List<ProductModel> pms = priceReductionService.getProducts(CATEGORY_ID, Optional.empty());
        assertTrue(pms.size()==0);
    }

	

}
