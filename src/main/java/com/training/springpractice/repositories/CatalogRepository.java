package com.training.springpractice.repositories;

import com.training.springpractice.models.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
}
