package com.mk.fusioncinemabe.service;

import com.mk.fusioncinemabe.entity.Movie;
import com.mk.fusioncinemabe.error.MovieNotFoundException;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie getMovie(Long id) throws MovieNotFoundException;
    List<Movie> getAll();
    Movie createMovie(Movie movie);
    Movie updateMovie(Long id, Movie movie) throws MovieNotFoundException;
    void deleteMovie(Long id) throws MovieNotFoundException;
    Optional<Movie> findMovieByTitleWithJPQL(String title);
    Optional<Movie> findByTitle(String title);
    Optional<Movie> findByTitleIgnoreCase(String title);
}
