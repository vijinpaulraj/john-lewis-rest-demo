package com.johnlewis.converter;

import com.johnlewis.domain.ColorSwatch;
import com.johnlewis.model.ColorSwatchModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.awt.*;


@Component
public class ColorSwatchToColorSwatchModelConverter implements Converter<ColorSwatch, ColorSwatchModel> {

	@Override
	public ColorSwatchModel convert(ColorSwatch source) {
		
		if(source==null)
			return null;
		
		ColorSwatchModel target = new ColorSwatchModel();
		
		target.setColor(source.getColor());
		target.setSkuId(source.getSkuId());
		target.setRgbColor(retrieveRgbColor(source.getBasicColor()));
		
		return target;
	}

	private String retrieveRgbColor (String basicColor) {
		Color color;
		try {
			java.lang.reflect.Field field = Class.forName("java.awt.Color").getField(basicColor.toLowerCase());
			color = (Color)field.get(null);
		} catch (Exception e) {
			color = null; // Not defined
		}
		return (color != null) ? String.format("%06x", color.getRGB() & 0x00FFFFFF).toUpperCase() : "";
	}

}
