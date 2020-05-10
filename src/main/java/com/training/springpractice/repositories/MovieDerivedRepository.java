package com.training.springpractice.repositories;

import com.training.springpractice.models.MovieDerived;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDerivedRepository extends JpaRepository<MovieDerived, Long> {
    List<MovieDerived> findByDeleted(Boolean deleted);
}
