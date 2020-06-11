package com.country.demo.domain;

import com.country.demo.domain.enums.Currency;

import java.math.BigDecimal;
import java.util.Objects;

public class Country {

    private String country;
    private String code;
    private BigDecimal exchangeRate;
    private Currency currency;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
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
        Country country1 = (Country) o;
        return Objects.equals(country, country1.country) &&
                Objects.equals(code, country1.code) &&
                Objects.equals(exchangeRate, country1.exchangeRate) &&
                Objects.equals(currency, country1.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, code, exchangeRate, currency);
    }

    @Override
    public String toString() {
        return "Country{" +
                "country='" + country + '\'' +
                ", code='" + code + '\'' +
                ", exchangeRate=" + exchangeRate +
                ", currency='" + currency + '\'' +
                '}';
    }
}
