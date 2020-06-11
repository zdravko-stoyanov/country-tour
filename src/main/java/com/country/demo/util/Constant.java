package com.country.demo.util;

import java.math.BigDecimal;

public interface Constant {

    BigDecimal MAXIMUM_BUDGET_PER_COUNTRY = new BigDecimal("5000");
    BigDecimal MINIMUM_BUDGE_PER_COUNTRY = new BigDecimal("1");
    String BUDGET_PER_COUNTRY_KEY = "budgetPerCountry";
    int NUMBER_OF_COUNTRIES = 5;
    String TOTAL_BUDGET_KEY = "totalBudget";
    String STARTING_COUNTRY_KEY = "startingCountry";
    String STARTING_COUNTRY = "Bulgaria";
    String CURRENCY_KEY = "currency";
    String BULGARIAN_NEIGHBOURS_JSON = "bulgarianNeighbours.json";
}
