package com.johnlewis.model;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;

@ApiModel

public class ProductModel {
	
	private String productId;
	private String title;
	private String nowPrice;
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

	public String getNowPrice() {
		return nowPrice;
	}

	public void setNowPrice(String nowPrice) {
		this.nowPrice = nowPrice;
	}

	public String getPriceLabel() {
		return priceLabel;
	}

	public void setPriceLabel(String priceLabel) {
		this.priceLabel = priceLabel;
	}

	public List<ColorSwatchModel> getColorSwatches() {
		return colorSwatches;
	}

	public void setColorSwatches(List<ColorSwatchModel> colorSwatches) {
		this.colorSwatches = colorSwatches;
	}
}
