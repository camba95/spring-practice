package com.training.springpractice.services;

import com.training.springpractice.models.Actor;
import com.training.springpractice.models.Movie;
import com.training.springpractice.models.MovieDerived;
import com.training.springpractice.repositories.ActorRepository;
import com.training.springpractice.repositories.MovieDerivedRepository;
import com.training.springpractice.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("movieService")
public class MovieService {

    @Autowired
    private MovieDerivedRepository movieDerivedRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private MovieRepository movieRepository;

    public MovieService() {}

    public List<MovieDerived> findAll() {
        return movieDerivedRepository.findByDeleted(false);
    }

    public Movie create (Movie movie) {
        List<Actor> actors = movie.getActors();
        if (actors != null) {
            List<Long> ids = actors.stream().map((actor) -> actor.getId()).collect(Collectors.toList());
            List<Actor> foundActors = actorRepository.findByIdIn(ids);
            movie.setActors(foundActors);
        }
        return movieRepository.saveAndFlush(movie);
    }
}
