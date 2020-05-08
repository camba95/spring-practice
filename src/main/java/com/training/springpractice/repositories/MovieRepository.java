package com.training.springpractice.repositories;

import com.training.springpractice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

//    @Query(value = "SELECT m.* FROM movies m WHERE m.deleted = :status", nativeQuery = true)
    List<Movie> findByDeleted(Boolean deleted);
}
