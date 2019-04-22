package com.johnlewis.domain;

public enum CurrencySymbol {
    GBP("£");

    private String currencySymbol;

    CurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    public String getCurrencySymbol() {
        return this.currencySymbol;
    }
}