package com.johnlewis.converter;

import com.johnlewis.domain.*;
import com.johnlewis.model.ProductModel;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class ProductToProductModelConverterTest {
	
	private static final String PRODUCT_ID = "123456";
    private static final String TITLE = "title";
	

	ProductToProductModelConverter converter;

    @Before
    public void setUp() throws Exception {
    	
        converter = new ProductToProductModelConverter();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null, null));
    }
	
    @Test
	public void GIVEN_Product_AND_labelTypeIsShowPercDscount_WHEN_convertProductToProductModel_THEN_returnCorrectProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypes> labelType = Optional.of(LabelTypes.ShowPercDscount);
    	
    	Price price = new Price();
    	price.setCurrency(CurrencySymbol.GBP);
    	price.setNow("5.00");
    	price.setWas("10");
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);

    	//then
    	assertEquals(PRODUCT_ID, pm.getProductId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75”. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00” or “£10”.
    	//assertEquals("£5", pm.getNowPrice());
    	
    	//ShowPercDscount - in which case return “x% off - now £y.yy”.
    	assertEquals("50% off - now £5.00", pm.getPriceLabel());
		
	}
    
    @Test
	public void GIVEN_Product_AND_labelTypeIsShowWasNow_WHEN_convertProductToProductModel_THEN_returnCorrectProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypes> labelType = Optional.of(LabelTypes.ShowWasNow);
    	
    	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", "£");
    	price.setCurrency(CurrencySymbol.GBP);
    	price.setNow("10.00");
    	price.setWas("20.00");
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getProductId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75”. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00” or “£10”.
    	assertEquals("£10", pm.getNowPrice());
    	
    	// ShowWasNow - in which case return a string saying “Was £x.xx, now £y.yyy”.
    	assertEquals("Was £20, now £10", pm.getPriceLabel());
		
	}
    
    @Test
   	public void GIVEN_product_WITH_Null_LabelType_WHEN_convertProductToProductModel_THEN_returnProductModel() throws Exception {
       	
       	//given
       
       	Optional<LabelTypes> labelType = Optional.empty();
       	
       	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", "£");
       	price.setCurrency(CurrencySymbol.GBP);
       	price.setNow("10.00");
       	price.setWas("20.00");
       	
       	ColorSwatch colorSwatch = new ColorSwatch();
       	
       	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
       	
       	//when
       	ProductModel pm = converter.convert(product, labelType);
       	
       	
       	
       	//then
       	assertEquals(PRODUCT_ID, pm.getProductId());
       	assertEquals(TITLE, pm.getTitle());
       	
       	//which is the price.now represented as a string, including the currency, e.g. “£1.75”. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00” or “£10”.
       	assertEquals("£10", pm.getNowPrice());
       	
       	// ShowWasNow - in which case return a string saying “Was £x.xx, now £y.yyy”.
       	assertEquals("Was £20, now £10", pm.getPriceLabel());
   		
   	}
    
    
    @Test
	public void GIVEN_Product_AND_labelTypeIsShowWasThenNow_and_THEN1IsNotNUll_and_than2IsNull_WHEN_convertProductToProductModel_THEN_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypes> labelType = Optional.of(LabelTypes.ShowWasThenNow);
    	
    	Price price = new Price(); // new Price(Optional.ofNullable(9.12f), Optional.ofNullable(123.12f), Optional.ofNullable(123.12f), "123.54", "£");
    	price.setCurrency(CurrencySymbol.GBP);
    	price.setNow("5.00");
    	price.setThen1("6.00");
    	price.setWas("10.00");
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getProductId());
    	assertEquals(TITLE, pm.getTitle());

    	assertEquals("£5.00", pm.getNowPrice());
    	
    	// ShowWasThenNow - in which case return a string saying “Was £x.xx, then £y.yy, now
    	// £z.zzz”. If the original price.then2 is not empty use that for the “then” price otherwise use
    	// the then1 price. If the then1 price is also empty then don’t show the “then” price.
    	assertEquals("Was £10, then £6.00, now £5.00", pm.getPriceLabel());
		
	}
    
    @Test
	public void GIVEN_product_AND_labelTypeIsShowWasThenNow_and_THEN1NotNUll_and_than2NotNull_WHEN_convertProductToProductModel_THEN_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypes> labelType = Optional.of(LabelTypes.ShowWasThenNow);
    	
    	Price price = new Price(); // new Price("9.12", "123.12", "123.12", "123.54", "£");
    	price.setCurrency(CurrencySymbol.GBP);
    	price.setNow("3");
    	price.setThen1("5.00");
    	price.setThen2("7.00");
    	price.setWas("10.00");
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getProductId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75”. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00” or “£10”.
    	//assertEquals("£10", pm.getNowPrice());
    	
    	// ShowWasThenNow - in which case return a string saying “Was £x.xx, then £y.yy, now
    	// £z.zzz”. If the original price.then2 is not empty use that for the “then” price otherwise use
    	// the then1 price. If the then1 price is also empty then don’t show the “then” price.
    	assertEquals("Was £10, then £5.00, now £3.00", pm.getPriceLabel());
		
	}
    
    
    @Test
	public void GIVEN_product_AND_nowPriceIsObject_WHEN_convertProductToProductModel_THEN_returnProductModel() throws Exception {
    	
    	//given
    
    	Optional<LabelTypes> labelType = Optional.of(LabelTypes.ShowWasNow);
    	
    	Price price = new Price(); // new Price("9.12", "123.12", "123.12", "123.54", "£");
    	price.setCurrency(CurrencySymbol.GBP);
    	//price.setNow("{from:\"12.36\", to: \"13:26\"}");
		price.setNow("10.00");
    	price.setThen1("6.12");
    	price.setThen2("7.12");
    	price.setWas("20.00");
    	
    	ColorSwatch colorSwatch = new ColorSwatch();
    	
    	Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));
    	
    	//when
    	ProductModel pm = converter.convert(product, labelType);
    	
    	
    	
    	//then
    	assertEquals(PRODUCT_ID, pm.getProductId());
    	assertEquals(TITLE, pm.getTitle());
    	
    	//which is the price.now represented as a string, including the currency, e.g. “£1.75”. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00” or “£10”.
    	assertEquals("£10", pm.getNowPrice());
    	
    	// ShowWasThenNow - in which case return a string saying “Was £x.xx, then £y.yy, now
    	// £z.zzz”. If the original price.then2 is not empty use that for the “then” price otherwise use
    	// the then1 price. If the then1 price is also empty then don’t show the “then” price.
    	assertEquals("Was £20, now £10", pm.getPriceLabel());
		
	}


	@Test
	public void GIVEN_product_AND_labelTypeIsShowWasThenNow_AND_colorSwatchModel_WHEN_convertProductToProductModel_THEN_returnProductModel() throws Exception {

		//given

		Optional<LabelTypes> labelType = Optional.of(LabelTypes.ShowWasThenNow);

		Price price = new Price();
		price.setCurrency(CurrencySymbol.GBP);
		price.setNow("3.00");
		price.setThen1("5.00");
		price.setThen2("7.00");
		price.setWas("10.00");

		ColorSwatch colorSwatch = new ColorSwatch();
		colorSwatch.setColor("Wine");
		colorSwatch.setBasicColor("Red");
		colorSwatch.setSkuId("237494589");

		Product product = new Product(PRODUCT_ID, TITLE, price, Arrays.asList(colorSwatch));

		//when
		ProductModel pm = converter.convert(product, labelType);



		//then
		assertEquals(PRODUCT_ID, pm.getProductId());
		assertEquals(TITLE, pm.getTitle());

		//which is the price.now represented as a string, including the currency, e.g. “£1.75”. For values that are integer, if they are less £10 return a decimal price, otherwise show an integer price, e.g. “£2.00” or “£10”.
		assertEquals("£3.00", pm.getNowPrice());

		// ShowWasThenNow - in which case return a string saying “Was £x.xx, then £y.yy, now
		// £z.zzz”. If the original price.then2 is not empty use that for the “then” price otherwise use
		// the then1 price. If the then1 price is also empty then don’t show the “then” price.
		assertEquals("Was £10, then £5.00, now £3.00", pm.getPriceLabel());

	}


}
