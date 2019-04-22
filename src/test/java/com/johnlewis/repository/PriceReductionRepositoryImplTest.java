package com.johnlewis.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

import static org.junit.Assert.assertTrue;



@RunWith(SpringRunner.class)
@RestClientTest(PriceReductionRepositoryImpl.class)
public class PriceReductionRepositoryImplTest {


    @Test
    public void when_callingapi_thenClientMakesCorrectCall() throws Exception {

    	//
    	RestTemplate restTemplate = new RestTemplate();

    	final String baseUrl = "https://jl-nonprod-syst.apigee.net/v1/categories/600001506/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma";
	    URI uri = new URI(baseUrl);

	    ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);

	    //Verify request succeed
	    assertTrue(200 == result.getStatusCodeValue());
	    assertTrue(result.getBody().contains("products"));
    }
	
	
	

}
