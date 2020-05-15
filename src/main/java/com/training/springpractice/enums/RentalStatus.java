package com.training.springpractice.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;

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

    public String getText() {
        return text;
    }

    public static RentalStatus getByText(String text) {
        return Arrays.stream(RentalStatus.values())
                .filter(current -> current.getText().equals(text))
                .findFirst()
                .orElse(null);
    }
}
