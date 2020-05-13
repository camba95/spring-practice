package com.training.springpractice.models;

import com.training.springpractice.enums.RentalStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

public class RentalOnlyStatus {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RentalStatus status;

    public RentalOnlyStatus() {}

    public RentalStatus getStatus() {
        return status;
    }

    public void setStatus(RentalStatus status) {
        this.status = status;
    }
}
