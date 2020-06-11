package com.country.demo.dto;

import com.country.demo.domain.enums.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public class ExchangeDto {

    private String country;
    private BigDecimal amount;
    private Currency currency;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeDto that = (ExchangeDto) o;
        return Objects.equals(country, that.country) &&
                Objects.equals(amount, that.amount) &&
                currency == that.currency;
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, amount, currency);
    }

    @Override
    public String toString() {
        return "ExchangeDto{" +
                "country='" + country + '\'' +
                ", amount=" + amount +
                ", currency=" + currency +
                '}';
    }
}
