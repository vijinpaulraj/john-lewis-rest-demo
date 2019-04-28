package com.johnlewis.model;

import io.swagger.annotations.ApiModel;

import java.util.ArrayList;
import java.util.List;

@ApiModel

public class ProductModel {
	
	private String productId;
	private String title;
	private String priceNow;
	private String priceLabel;
	private List<ColorSwatchModel> colorSwatches = new ArrayList<>();

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(String priceLabel) {
		this.priceLabel = priceLabel;
	}

	public String getPriceNow() {
		return priceNow;
	}

	public void setPriceNow(String priceNow) {
		this.priceNow = priceNow;
	}

	public List<ColorSwatchModel> getColorSwatches() {
		return colorSwatches;
	}

	public void setColorSwatches(List<ColorSwatchModel> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}
}
