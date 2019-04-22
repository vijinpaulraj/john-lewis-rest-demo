package com.johnlewis.domain;

import java.util.ArrayList;
import java.util.List;

public class Product {

	public Product() { }

	private String productId;
	private String title;
	private Price price;
	private List<ColorSwatch> colorSwatches = new ArrayList<>();

	public Product(String productId, String title, Price price, List<ColorSwatch> colorSwatches) {
		this.productId = productId;
		this.title = title;
		this.price = price;
		this.colorSwatches = colorSwatches;
	}

	public String getProductId() {
		return productId;
	}

	public String getTitle() {
		return title;
	}

	public Price getPrice() {
		return price;
	}

	public List<ColorSwatch> getColorSwatches() {
		return colorSwatches;
	}
}
