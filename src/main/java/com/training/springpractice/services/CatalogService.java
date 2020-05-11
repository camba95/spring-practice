package com.training.springpractice.services;

import com.training.springpractice.models.Actor;
import com.training.springpractice.models.Catalog;
import com.training.springpractice.models.Movie;
import com.training.springpractice.models.MovieDerived;
import com.training.springpractice.repositories.ActorRepository;
import com.training.springpractice.repositories.CatalogRepository;
import com.training.springpractice.repositories.MovieDerivedRepository;
import com.training.springpractice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("catalogService")
public class CatalogService {

    @Autowired
    private CatalogRepository repository;

    @Autowired
    private MovieRepository movieRepository;

    public CatalogService() {}

    public Catalog create (Catalog catalog) throws Exception {
        Movie movie = movieRepository.getOne(catalog.getMovie().getId());
        if (movie == null) {
            return repository.saveAndFlush(catalog);
        }
        throw new Exception("Movie already exists");
    }
}
