package com.mk.fusioncinemabe.service.impl;

import com.mk.fusioncinemabe.entity.Movie;
import com.mk.fusioncinemabe.error.MovieNotFoundException;
import com.mk.fusioncinemabe.repository.MovieRepository;
import com.mk.fusioncinemabe.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieRepository movieRepository;

    @Override
    public Movie getMovie(Long id) throws MovieNotFoundException {
        Optional<Movie> movie = movieRepository.findById(id);
        if (!movie.isPresent()){
            throw new MovieNotFoundException("La pelicula no exite");
        }
        return movie.get();
    }

    @Override
    public List<Movie> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public Page<Movie> getAllMovies(Pageable pageable) {
        return movieRepository.findAll(pageable);
    }

    @Override
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public Movie updateMovie(Long id, Movie movie) throws MovieNotFoundException {
        Movie movieDb = movieRepository.findById(id).orElse(null);
        if (Objects.nonNull(movieDb)){
            movieDb.setTitle(movie.getTitle());
            movieDb.setGenre(movie.getGenre());
            movieDb.setReleaseYear(movie.getReleaseYear());
            movieDb.setRating(movie.getRating());
            movieDb.setSynopsis(movie.getSynopsis());
            movieDb.setDirector(movie.getDirector());
            movieDb.setMinimumAge(movie.getMinimumAge());
            return movieRepository.save(movieDb);
        }
        return null;
    }

    @Override
    public void deleteMovie(Long id) throws MovieNotFoundException {
        movieRepository.deleteById(id);
    }

    @Override
    public Optional<Movie> findMovieByTitleWithJPQL(String title) {
        return movieRepository.findMovieByTitleWithJPQL(title);
    }

    @Override
    public Optional<Movie> findByTitle(String title) {
        return movieRepository.findByTitle(title);
    }

    @Override
    public Optional<Movie> findByTitleIgnoreCase(String title) {
        return movieRepository.findByTitleIgnoreCase(title);
    }
}
