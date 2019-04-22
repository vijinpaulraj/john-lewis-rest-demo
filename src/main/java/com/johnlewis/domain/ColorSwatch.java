package com.johnlewis.domain;

public class ColorSwatch {
	private String basicColor;
	private String skuId;
	private String color;

	public ColorSwatch() { }

	public void setBasicColor(String basicColor) {
		this.basicColor = basicColor;
	}

	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ColorSwatch(String basicColor, String skuId, String color) {
		this.basicColor = basicColor;
		this.skuId = skuId;
		this.color = color;
	}

	public String getBasicColor() {
		return basicColor;
	}

	public String getSkuId() {
		return skuId;
	}

	public String getColor() {
		return color;
	}
}
