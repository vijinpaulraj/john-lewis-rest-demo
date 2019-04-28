package com.johnlewis.converter;

import com.google.common.base.Strings;
import com.johnlewis.domain.LabelTypes;
import com.johnlewis.domain.Price;
import com.johnlewis.domain.Product;
import com.johnlewis.model.ColorSwatchModel;
import com.johnlewis.model.ProductModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class ProductToProductModelConverter {

	@Autowired
	private ColorSwatchToColorSwatchModelConverter colorSwatchConverter = new ColorSwatchToColorSwatchModelConverter();

	final static Logger log = LogManager.getLogger(ProductToProductModelConverter.class);

	public ProductModel convert(Product source, Optional<LabelTypes> labelType) {
		
		if(source==null)
			return null;

		String priceLabel = fetchPriceLabel(labelType, source.getPrice());

        List<ColorSwatchModel> colorSwatch = fetchColorSwatch(source);

		ProductModel target = new ProductModel();
		
		target.setProductId(source.getProductId());
		target.setTitle(source.getTitle());
		target.setPriceLabel(priceLabel);
		target.setPriceNow(nowPrice(source.getPrice()));
		target.setColorSwatches(colorSwatch);
		
		return target;
		
	}

	private List<ColorSwatchModel> fetchColorSwatch (Product product) {

        List<ColorSwatchModel> response = product.getColorSwatches().stream().map(x-> {
           return colorSwatchConverter.convert(x);
        }).collect(Collectors.toList());

        return response;
    }
	
	/**
	 * Return a decimal value if the price is less than £10, or return integer.
	 * @param price Price.class
	 * @return String
	 */
	private String nowPrice(Price price) {
		Float nowPrice;
		try {
			nowPrice = Float.parseFloat(price.getNow());
		}catch (Exception e) {
			nowPrice = 1.23f;
		}
		return nowPrice >= 10 ? price.getCurrency().getCurrencySymbol()+Math.round(nowPrice) : price.getCurrency().getCurrencySymbol()+ String.format("%.2f", nowPrice) ;
	}

	private Object convertPrice (String priceStr) {
		Float price = Float.parseFloat(priceStr);
		return price >= 10 ? "" + Math.round(price) : String.format("%.2f", price);
	}
	
	/**
	 * print the output labels
	 * @param labelType Optional<LabelTypes>
	 * @param price Price
	 * @return String
	 */

	private String fetchPriceLabel(Optional<LabelTypes> labelType, Price price) {
		
		String response="";
		
		LabelTypes priceLabel = labelType.map(x -> {
									return x;
								}).orElse(LabelTypes.ShowWasNow);

		if(LabelTypes.ShowWasNow.equals(priceLabel)) {
			if (!Strings.isNullOrEmpty(price.getWas())) {
				response = "Was " + price.getCurrency().getCurrencySymbol() + convertPrice(price.getWas());
			}
			if (!Strings.isNullOrEmpty(nowPrice(price))) {
				if (!Strings.isNullOrEmpty(response)) {
					response += ", now "+nowPrice(price);
				} else {
					response = "now "+nowPrice(price);
				}
			}

		} else if(LabelTypes.ShowWasThenNow.equals(priceLabel)) {
			if (!Strings.isNullOrEmpty(price.getWas())) {
				response = "Was " + price.getCurrency().getCurrencySymbol() + convertPrice(price.getWas());
			}

			if (!Strings.isNullOrEmpty(price.getThen1())) {
				response += ", then " + price.getCurrency().getCurrencySymbol() + convertPrice(price.getThen1());
			} else if (!Strings.isNullOrEmpty(price.getThen2())) {
				response += ", then " + price.getCurrency().getCurrencySymbol() + convertPrice(price.getThen2());
			}

			if (!Strings.isNullOrEmpty(response)) {
				response += ", now " + nowPrice(price);
			} else {
				response = "now " + nowPrice(price);
			}

		} else if(LabelTypes.ShowPercDscount.equals(priceLabel)) {
			int discountPercentage = getDiscountPercentage(price);
			if (discountPercentage != 0) {
				response = discountPercentage+ "% off - now " + nowPrice(price);
			} else {
				response = "No off - now " + nowPrice(price);
			}
		}
		
		return response;
	}

	private int getDiscountPercentage (Price price) {
		String initialPrice = "";
		Float finalPrice;
		if (!Strings.isNullOrEmpty(price.getThen2())) {
			initialPrice = price.getThen2();
		} else if (!Strings.isNullOrEmpty(price.getThen1())) {
			initialPrice = price.getThen1();
		} else if (!Strings.isNullOrEmpty(price.getWas())) {
			initialPrice = price.getWas();
		}
		finalPrice = Float.parseFloat(price.getNow());

		if (!Strings.isNullOrEmpty(initialPrice) && finalPrice != null) {
			return Math.round((finalPrice / Float.parseFloat(initialPrice)) * 100);
		}
		return 0;
	}
}
