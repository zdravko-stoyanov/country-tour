package com.country.demo.domain.enums;

public enum Currency {

    TRY("TRY"),
    EUR("EUR"),
    MKD("MKD"),
    RSD("RSD"),
    RON("RON");

    private final String currency;

    Currency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return currency;
    }
}
