package com.country.demo.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExchangeRate {

    private String from;
    private List<Country> to;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public List<Country> getTo() {
        return to == null ? null : new ArrayList<>(to);
    }

    public void setTo(List<Country> to) {
        this.to = to == null ? null : new ArrayList<>(to);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExchangeRate that = (ExchangeRate) o;
        return Objects.equals(from, that.from) &&
                Objects.equals(to, that.to);
    }

    @Override
    public int hashCode() {
        return Objects.hash(from, to);
    }

    @Override
    public String toString() {
        return "ExchangeRate{" +
                "from='" + from + '\'' +
                ", to=" + to +
                '}';
    }
}
