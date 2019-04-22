package com.johnlewis.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.johnlewis.domain.Product;
import com.johnlewis.model.ProductModel;
import com.johnlewis.repository.PriceReductionRepository;



@RunWith(SpringRunner.class)
public class PriceReductionServiceImplTest {
	
	private static final Integer CATEGORY_ID = 123;
	
    @TestConfiguration
    static class DiscauntServicesImplTestContextConfiguration {

        @Bean
        public PriceReductionService discauntService() {
            return new PriceReductionServiceImpl();
        }
    }

    @Autowired
    private PriceReductionService discauntService;

    @MockBean
    private PriceReductionRepository discountRepository;

    @Before
    public void setUp() throws Exception {

        Mockito.when(discountRepository.getProducts(CATEGORY_ID))
                .thenReturn(new ArrayList<Product>());
    }

    @Test
    public void get_productModels() {
        List<ProductModel> pms = discauntService.getProducts(CATEGORY_ID, Optional.empty());
        assertTrue(pms.size()==0);
    }

	

}
