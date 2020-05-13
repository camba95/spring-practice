package com.training.springpractice.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RentalStatusConverter implements AttributeConverter<RentalStatus, String> {
    @Override
    public String convertToDatabaseColumn(RentalStatus status) {
        return status.getText();
    }

    @Override
    public RentalStatus convertToEntityAttribute(String status) {
        RentalStatus result = RentalStatus.getByText(status);
        return result;
    }
}
