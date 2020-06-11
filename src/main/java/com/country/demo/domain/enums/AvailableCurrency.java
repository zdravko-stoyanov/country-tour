package com.country.demo.domain.enums;

import java.util.Arrays;

public enum AvailableCurrency {

    EUR("EUR"),
    USD("USD");

    private final String currency;

    AvailableCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return currency;
    }

    /**
     * Helper method which iterates over all available currencies and checks if the passed one exists
     *
     * @param currency - which to be checked
     * @return - true in cases the passed currency is not found in the collection of available currencies
     */
    public static boolean isInvalidCurrency(String currency){
        return Arrays.stream(AvailableCurrency.values()).noneMatch(c -> c.currency.equals(currency));
    }
}
