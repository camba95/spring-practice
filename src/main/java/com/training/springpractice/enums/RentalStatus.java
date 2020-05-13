package com.training.springpractice.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum RentalStatus {
    @JsonProperty("Rented")
    RENTED("Rented"),
    @JsonProperty("Returned")
    RETURNED("Returned");

    private final String text;

    RentalStatus(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
