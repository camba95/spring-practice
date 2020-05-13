package com.training.springpractice.repositories;

import com.training.springpractice.models.Catalog;
import com.training.springpractice.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CatalogRepository extends JpaRepository<Catalog, Long> {
    @Modifying
    @Query("update movie_catalog mc set mc.copies = mc.copies - 1 where mc.movie.id = :movieId")
    void decreaseCopies(@Param("movieId") Long movieId);

    Catalog findByMovie(Movie movie);
}
