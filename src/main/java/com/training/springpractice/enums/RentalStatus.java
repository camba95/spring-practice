package com.training.springpractice.enums;

public enum RentalStatus {
    RENTED("Rented"),
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
