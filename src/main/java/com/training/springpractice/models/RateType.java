package com.training.springpractice.models;

public enum RateType {
    G("G"),
    PG("PG"),
    PG13("PG-13"),
    R("R"),
    NC17("NC-17");

    private final String text;

    RateType(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
