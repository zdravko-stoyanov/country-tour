package com.country.demo.service;

import com.country.demo.domain.ExchangeRate;
import com.country.demo.dto.ExchangeDto;
import com.country.demo.dto.GetNeighboursTourRequestDto;
import com.country.demo.dto.GetNeighboursTourResponseDto;
import com.country.demo.exception.ObjectMappingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.country.demo.util.Constant.BULGARIAN_NEIGHBOURS_JSON;
import static com.country.demo.util.Constant.NUMBER_OF_COUNTRIES;

@Service
public class TourService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TourService.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public TourService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Calculates information about tour around Bulgaria's neighbour countries
     */
    public GetNeighboursTourResponseDto getTourCalculation(GetNeighboursTourRequestDto request) {
        List<ExchangeRate> exchangeRates = getExchangeRates();

        String currency = request.getCurrency().toString();
        BigDecimal totalBudget = request.getTotalBudget();
        BigDecimal budgetPerCountry = request.getBudgetPerCountry();

        ExchangeRate exchangeRate = exchangeRates.stream().filter(e -> e.getFrom().equals(currency)).findFirst().orElse(null);

        GetNeighboursTourResponseDto response = new GetNeighboursTourResponseDto();

        Set<String> countryCodes = new HashSet<>();

        if (exchangeRate != null) {
            int numberOfTravels = totalBudget.divide(budgetPerCountry, 5, RoundingMode.CEILING)
                    .divide(BigDecimal.valueOf(NUMBER_OF_COUNTRIES), 5, RoundingMode.CEILING).intValue();
            BigDecimal leftover = totalBudget.subtract(budgetPerCountry.multiply(BigDecimal.valueOf(numberOfTravels)).multiply(BigDecimal.valueOf(NUMBER_OF_COUNTRIES)));
            response.setLeftover(leftover);
            response.setNumberOfCountries(NUMBER_OF_COUNTRIES);
            response.setNumberOfTravels(numberOfTravels);
            List<ExchangeDto> exchanges = mapExchangeForCountry(budgetPerCountry, exchangeRate, numberOfTravels, countryCodes);
            response.setExchanges(exchanges);
            StringBuilder sb = new StringBuilder(request.getStartingCountry());
            sb.append(" has ").append(NUMBER_OF_COUNTRIES).append(" neighbour countries ").append(countryCodes)
                    .append(" and Angel can travel around them ").append(numberOfTravels).append(" times.")
                    .append(" He will have ").append(leftover).append(" ").append(exchangeRate.getFrom()).append(" leftover.");
            exchanges.forEach(e -> {
                if (e.getCurrency().toString().equals(currency)) {
                    sb.append(" For ").append(e.getCountry()).append(" he won't have to buy any currency as he already has EUR.");
                } else {
                    sb.append(" For ").append(e.getCountry()).append(" he will need to buy ").append(e.getAmount())
                            .append(" ").append(e.getCurrency()).append(".");
                }
            });
            response.setMessage(sb.toString());
        }

        return response;
    }

    /**
     * Iterates over all countries and creates {@link ExchangeDto}
     *
     * @param budgetPerCountry - used for multiplier for the amount
     * @param exchangeRate     - used for multiplier for the amount
     * @param numberOfTravels  - used for multiplier for the amount
     * @return List of all available exchanges
     */
    private List<ExchangeDto> mapExchangeForCountry(BigDecimal budgetPerCountry, ExchangeRate exchangeRate, int numberOfTravels, Set<String> countryCodes) {
        List<ExchangeDto> exchanges = new ArrayList<>();
        exchangeRate.getTo().forEach(country -> {
            ExchangeDto exchange = new ExchangeDto();
            exchange.setAmount(BigDecimal.valueOf(numberOfTravels)
                    .multiply(budgetPerCountry)
                    .multiply(country.getExchangeRate()));
            exchange.setCountry(country.getCountry());
            exchange.setCurrency(country.getCurrency());
            exchanges.add(exchange);
            countryCodes.add(country.getCode());
        });
        return exchanges;
    }

    /**
     * Helper method which returns java {@link Object} converted from json file
     *
     * @return - java object converted from json
     */
    private List<ExchangeRate> getExchangeRates() {
        InputStream bgNeighboursJson = this.getClass().getClassLoader().getResourceAsStream(BULGARIAN_NEIGHBOURS_JSON);

        try {
            return objectMapper.readValue(bgNeighboursJson, new TypeReference<>() {
            });
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);

            throw new ObjectMappingException(e.getMessage());
        }
    }
}
