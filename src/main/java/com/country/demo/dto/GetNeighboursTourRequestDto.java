package com.country.demo.dto;

import com.country.demo.domain.enums.AvailableCurrency;

import java.math.BigDecimal;
import java.util.Objects;

public class GetNeighboursTourRequestDto {

    private String startingCountry;
    private BigDecimal budgetPerCountry;
    private BigDecimal totalBudget;
    private AvailableCurrency currency;

    public GetNeighboursTourRequestDto(String startingCountry, BigDecimal budgetPerCountry, BigDecimal totalBudget, AvailableCurrency currency) {
        this.startingCountry = startingCountry;
        this.budgetPerCountry = budgetPerCountry;
        this.totalBudget = totalBudget;
        this.currency = currency;
    }

    public String getStartingCountry() {
        return startingCountry;
    }

    public void setStartingCountry(String startingCountry) {
        this.startingCountry = startingCountry;
    }

    public BigDecimal getBudgetPerCountry() {
        return budgetPerCountry;
    }

    public void setBudgetPerCountry(BigDecimal budgetPerCountry) {
        this.budgetPerCountry = budgetPerCountry;
    }

    public BigDecimal getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(BigDecimal totalBudget) {
        this.totalBudget = totalBudget;
    }

    public AvailableCurrency getCurrency() {
        return currency;
    }

    public void setCurrency(AvailableCurrency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetNeighboursTourRequestDto that = (GetNeighboursTourRequestDto) o;
        return Objects.equals(startingCountry, that.startingCountry) &&
                Objects.equals(budgetPerCountry, that.budgetPerCountry) &&
                Objects.equals(totalBudget, that.totalBudget) &&
                currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startingCountry, budgetPerCountry, totalBudget, currency);
    }

    @Override
    public String toString() {
        return "GetNeighboursTourRequestDto{" +
                "startingCountry='" + startingCountry + '\'' +
                ", budgetPerCountry=" + budgetPerCountry +
                ", totalBudget=" + totalBudget +
                ", currency=" + currency +
                '}';
    }
}
