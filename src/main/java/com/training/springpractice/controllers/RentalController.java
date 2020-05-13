package com.training.springpractice.controllers;

import com.training.springpractice.errors.NotFoundException;
import com.training.springpractice.models.RentalBody;
import com.training.springpractice.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/rental")
public class RentalController {

    @Autowired
    private RentalService service;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody RentalBody body) {
        try {
            RentalBody createdRental = service.create(body);
            return ResponseEntity.ok(createdRental);
        } catch (NotFoundException e) {
            throw e;
        }
    }
}
