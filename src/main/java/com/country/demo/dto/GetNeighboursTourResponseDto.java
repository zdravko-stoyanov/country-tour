package com.country.demo.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class GetNeighboursTourResponseDto {

    private int numberOfCountries;
    private int numberOfTravels;
    private BigDecimal leftover;
    private String message;
    private List<ExchangeDto> exchanges;

    public int getNumberOfCountries() {
        return numberOfCountries;
    }

    public void setNumberOfCountries(int numberOfCountries) {
        this.numberOfCountries = numberOfCountries;
    }

    public int getNumberOfTravels() {
        return numberOfTravels;
    }

    public void setNumberOfTravels(int numberOfTravels) {
        this.numberOfTravels = numberOfTravels;
    }

    public BigDecimal getLeftover() {
        return leftover;
    }

    public void setLeftover(BigDecimal leftover) {
        this.leftover = leftover;
    }

    public List<ExchangeDto> getExchanges() {
        return exchanges;
    }

    public void setExchanges(List<ExchangeDto> exchanges) {
        this.exchanges = exchanges;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetNeighboursTourResponseDto that = (GetNeighboursTourResponseDto) o;
        return numberOfCountries == that.numberOfCountries &&
                numberOfTravels == that.numberOfTravels &&
                Objects.equals(leftover, that.leftover) &&
                Objects.equals(exchanges, that.exchanges) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numberOfCountries, numberOfTravels, leftover, exchanges, message);
    }

    @Override
    public String toString() {
        return "GetNeighboursTourResponseDto{" +
                "numberOfCountries=" + numberOfCountries +
                ", numberOfTravels=" + numberOfTravels +
                ", leftover=" + leftover +
                ", exchanges=" + exchanges +
                ", message='" + message + '\'' +
                '}';
    }
}
