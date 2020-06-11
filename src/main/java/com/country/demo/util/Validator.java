package com.country.demo.util;

import com.country.demo.domain.enums.AvailableCurrency;
import com.country.demo.dto.GetNeighboursTourRequestDto;
import com.country.demo.exception.BadRequestException;
import com.country.demo.service.TourService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.country.demo.util.Constant.*;

public interface Validator {

    Logger LOGGER = LoggerFactory.getLogger(TourService.class);

    /**
     * Validates {@link GetNeighboursTourRequestDto}
     *
     * @param requestDto - which to be validated
     * @throws BadRequestException in case some of the input parameters is invalid
     */
    static void validateGetNeighboursTourRequestDto(GetNeighboursTourRequestDto requestDto) {
        BigDecimal budgetPerCountry = requestDto.getBudgetPerCountry();
        BigDecimal totalBudget = requestDto.getTotalBudget();
        String startingCountry = requestDto.getStartingCountry();
        AvailableCurrency currency = requestDto.getCurrency();

        Map<String, String> invalidParameters = new HashMap<>();

        if (budgetPerCountry.compareTo(MINIMUM_BUDGE_PER_COUNTRY) < 0 || budgetPerCountry.compareTo(MAXIMUM_BUDGET_PER_COUNTRY) > 0) {
            invalidParameters.put(BUDGET_PER_COUNTRY_KEY,
                    String.format("Invalid budget per country - %s. The range is between 1 and 5000", budgetPerCountry));
        }

        if (totalBudget.compareTo(MINIMUM_BUDGE_PER_COUNTRY.multiply(BigDecimal.valueOf(NUMBER_OF_COUNTRIES))) < 0//if the total budget is less than 5
                || totalBudget.compareTo(budgetPerCountry.multiply(BigDecimal.valueOf(NUMBER_OF_COUNTRIES))) < 0 //if the total budget is less than budgetPerCountry x5
                || totalBudget.compareTo(MAXIMUM_BUDGET_PER_COUNTRY.multiply(BigDecimal.valueOf(NUMBER_OF_COUNTRIES))) > 0){ //if the total budget is more than 25 000
            invalidParameters.put(TOTAL_BUDGET_KEY,
                    String.format("Invalid total budget - %s. It must be in range 5 - 25 000 and it must be 5 times higher than budget per country",
                            totalBudget));
        }

        if (!STARTING_COUNTRY.equalsIgnoreCase(startingCountry)){
            invalidParameters.put(STARTING_COUNTRY_KEY, "Invalid starting country. The only possible option is Bulgaria");
        }

        if (AvailableCurrency.isInvalidCurrency(currency.toString())){
            invalidParameters.put(CURRENCY_KEY, String.format("Invalid currency. The available options are: %s", AvailableCurrency.values()));
        }

        if (!invalidParameters.isEmpty()) {
            LOGGER.error(String.format("The request is invalid. Invalid parameters: %s", invalidParameters));

            throw new BadRequestException(String.format("Validation failed, Invalid request parameters: %s", invalidParameters));
        }

    }
}
