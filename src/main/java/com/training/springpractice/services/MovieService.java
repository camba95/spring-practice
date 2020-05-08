package com.training.springpractice.services;

import com.training.springpractice.models.Movie;
import com.training.springpractice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
public class MovieService {

    @Autowired
    private MovieRepository repository;

    public MovieService() {}

    public List<Movie> findAll() {
        return repository.findByDeleted(false);
    }
}
