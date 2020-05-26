package com.training.springpractice.repositories;

import com.training.springpractice.models.Rental;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental, Long> {
}
