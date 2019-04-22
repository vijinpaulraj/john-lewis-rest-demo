package com.johnlewis.converter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import com.johnlewis.domain.ColorSwatch;
import com.johnlewis.model.ColorSwatchModel;

public class ColorSwatchToColorSwatchModelConverterTest {

//	private static final String BASIC_COLOR = "Red";
    private static final String SKU_ID = "237494589";
    private static final String COLOR = "Wine";
    

    ColorSwatchToColorSwatchModelConverter converter;

    @Before
    public void setUp() throws Exception {
        converter = new ColorSwatchToColorSwatchModelConverter();
    }

    @Test
    public void testNullObject() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() throws Exception {
        assertNotNull(converter.convert(new ColorSwatch()));
    }

    @Test
    public void GIVEN_colorSwatch_AND_basicColorIsRed_WHEN_convertColorSwatchToColorSwatchModel_THEN_returnColorSwatchModel() throws Exception {
        
    	//given
    	ColorSwatch colorSwatch = new ColorSwatch("Red", SKU_ID, COLOR);

        //when
    	ColorSwatchModel colorSwatchModel = converter.convert(colorSwatch);

        //then
    	
        assertEquals("FF0000", colorSwatchModel.getRgbColor());
        assertEquals(SKU_ID, colorSwatchModel.getSkuId());
        assertEquals(COLOR, colorSwatchModel.getColor());
    }
    
    @Test
    public void GIVEN_colorSwatch_AND_basicColorIsGreen_WHEN_convertColorSwatchToColorSwatchModel_THEN_returnColorSwatchModel() throws Exception {
        
    	//given
    	ColorSwatch colorSwatch = new ColorSwatch("Green", SKU_ID, COLOR);

        //when
    	ColorSwatchModel colorSwatchModel = converter.convert(colorSwatch);

        //then
    	
        assertEquals("00FF00", colorSwatchModel.getRgbColor());
        assertEquals(SKU_ID, colorSwatchModel.getSkuId());
        assertEquals(COLOR, colorSwatchModel.getColor());
    }
    
    @Test
    public void GIVEN_colorSwatch_AND_basicColorIsBlue_WHEN_convertColorSwatchToColorSwatchModel_THEN_returnColorSwatchModel() throws Exception {
        
    	//given
    	ColorSwatch colorSwatch = new ColorSwatch("Blue", SKU_ID, COLOR);

        //when
    	ColorSwatchModel colorSwatchModel = converter.convert(colorSwatch);

        //then
    	
        assertEquals("0000FF", colorSwatchModel.getRgbColor());
        assertEquals(SKU_ID, colorSwatchModel.getSkuId());
        assertEquals(COLOR, colorSwatchModel.getColor());
    }
    
    @Test
    public void GIVEN_colorSwatch_AND_basicColorIsWhite_WHEN_convertColorSwatchToColorSwatchModel_THEN_returnColorSwatchModel() throws Exception {
        
    	//given
    	ColorSwatch colorSwatch = new ColorSwatch("White", SKU_ID, COLOR);

        //when
    	ColorSwatchModel colorSwatchModel = converter.convert(colorSwatch);

        //then
    	
        assertEquals("FFFFFF", colorSwatchModel.getRgbColor());
        assertEquals(SKU_ID, colorSwatchModel.getSkuId());
        assertEquals(COLOR, colorSwatchModel.getColor());
    }
    @Test
    public void GIVEN_colorSwatch_AND_basicColorIsBlack_WHEN_convertColorSwatchToColorSwatchModel_THEN_returnColorSwatchModel() throws Exception {
        
    	//given
    	ColorSwatch colorSwatch = new ColorSwatch("Black", SKU_ID, COLOR);

        //when
    	ColorSwatchModel colorSwatchModel = converter.convert(colorSwatch);

        //then
    	
        assertEquals("000000", colorSwatchModel.getRgbColor());
        assertEquals(SKU_ID, colorSwatchModel.getSkuId());
        assertEquals(COLOR, colorSwatchModel.getColor());
    }
}
