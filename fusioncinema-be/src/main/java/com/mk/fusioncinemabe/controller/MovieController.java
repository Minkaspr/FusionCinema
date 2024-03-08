package com.mk.fusioncinemabe.controller;

import com.mk.fusioncinemabe.entity.Movie;
import com.mk.fusioncinemabe.error.MovieNotFoundException;
import com.mk.fusioncinemabe.error.dto.ApiResponse;
import com.mk.fusioncinemabe.service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/Movie")
public class MovieController {
    @Autowired
    MovieService movieService;

    @GetMapping("/getMovie/{id}")
    public ResponseEntity<ApiResponse> getMovie(@PathVariable Long id){
        try {
            Movie movie = movieService.getMovie(id);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Operación exitosa", movie, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al obtener la película", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getAllMovies")
    public ResponseEntity<ApiResponse> getAllMovies(){
        try {
            List<Movie> movies = movieService.getAll();
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Operación exitosa", movies, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al obtener las películas", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/saveMovie")
    public ResponseEntity<ApiResponse> saveMovie(@Valid @RequestBody Movie movie){
        try {
            Movie savedMovie = movieService.createMovie(movie);
            ApiResponse response = new ApiResponse(HttpStatus.CREATED.value(), "Pelicula creada con éxito", savedMovie, null);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            String message = "El título '" + movie.getTitle() + "' ya existe. Por favor, intenta con un valor diferente.";
            ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Error al crear la película", null, message);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al crear la película", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateMovie/{id}")
    public ResponseEntity<ApiResponse> updateMovie(@PathVariable Long id, @Valid @RequestBody Movie movie){
        try {
            Movie updatedMovie = movieService.updateMovie(id, movie);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Pelicula actualizada con éxito", updatedMovie, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al actualizar la película", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteMovie/{id}")
    public ResponseEntity<ApiResponse> deleteMovie(@PathVariable Long id){
        try {
            movieService.deleteMovie(id);
            ApiResponse response = new ApiResponse(HttpStatus.OK.value(), "Pelicula eliminada con éxito", null, null);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            ApiResponse response = new ApiResponse(HttpStatus.NOT_FOUND.value(), e.getMessage(), null, null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            ApiResponse response = new ApiResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al eliminar la película", null, e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMovieByTitle/{title}")
    public Optional<Movie> getMovieByTitle(@PathVariable String title){
        return movieService.findMovieByTitleWithJPQL(title);
    }

    @GetMapping("/getByTitle/{title}")
    public ResponseEntity<Movie> getByTitle(@PathVariable String title){
        Optional<Movie> movie = movieService.findByTitle(title);
        if (movie.isPresent()) {
            return new ResponseEntity<>(movie.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/getByTitleIc/{title}")
    public Optional<Movie> getByTitleIc(@PathVariable String title){
        return movieService.findByTitleIgnoreCase(title);
    }
}
/**
 *     CRUD Sencillo
 *
 *     @GetMapping("/getMovie/{id}")
 *     public Movie getMovie(@PathVariable Long id) throws MovieNotFoundException {
 *         return  movieService.getMovie(id);
 *     }
 *     @GetMapping("/getAllMovies")
 *     public List<Movie> getAllMovies(){
 *         return  movieService.getAll();
 *     }
 *
 *     @PostMapping("/saveMovie")
 *     public Movie saveMovie(@Valid @RequestBody Movie movie){
 *         return movieService.createMovie(movie);
 *     }
 *
 *     @PutMapping("/updateMovie/{id}")
 *     public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movie){
 *         return movieService.updateMovie(id, movie);
 *     }
 *
 *     @DeleteMapping("/deleteMovie/{id}")
 *     public String deleteMovie(@PathVariable Long id){
 *         movieService.deleteMovie(id);
 *         return "Pelicula eliminada con exito";
 *     }
 */
