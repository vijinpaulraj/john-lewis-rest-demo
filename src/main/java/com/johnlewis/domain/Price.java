package com.johnlewis.domain;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;

public class Price {
	private String was;
	private String then1;
	private String then2;
	private String now;
	private CurrencySymbol currency;

	public Price () {}

	public String getWas() {
		return was;
	}

	public void setWas(String was) {
		this.was = was;
	}

	public String getThen1() {
		return then1;
	}

	public void setThen1(String then1) {
		this.then1 = then1;
	}

	public String getThen2() {
		return then2;
	}

	public void setThen2(String then2) {
		this.then2 = then2;
	}

	public String getNow() {
		return now;
	}

	/**
	 * @set the "now" value. If the now is a JSON object, for example {"from":"50.0", "to":"60.0"}
	 * then return the string value of "to" i.e "60.0", else return the actual value as String.
	 * @param now
	 */
	@JsonSetter("now")
	public void setNow(JsonNode now) {
		if (now.get("now") != null) { // validate now is in root or a child
			now = now.get("now");
		}
		if (now.isObject()) {
			this.now = now.get("to").asText();
		} else if (now.isTextual()) {
			this.now = now.asText();
		}
	}

	public CurrencySymbol getCurrency() {
		return currency;
	}

	public void setCurrency(CurrencySymbol currency) {
		this.currency = currency;
	}
}