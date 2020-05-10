package com.training.springpractice.services;

import com.training.springpractice.models.MovieDerived;
import com.training.springpractice.repositories.MovieDerivedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("movieService")
public class MovieService {

    @Autowired
    private MovieDerivedRepository repository;

    public MovieService() {}

    public List<MovieDerived> findAll() {
        return repository.findByDeleted(false);
    }
}
