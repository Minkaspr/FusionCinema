package com.mk.fusioncinemabe.service;

import com.mk.fusioncinemabe.entity.Movie;
import com.mk.fusioncinemabe.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @BeforeEach
    void setUp() {
        Movie movie = Movie.builder()
                .title("Soul")
                .genre("Animation, Adventure, Comedy")
                .releaseYear(LocalDate.of(2020, 12, 25))
                .rating(4.0)
                .synopsis("Joe es un profesor de música de secundaria cuya vida no ha salido como esperaba. Su verdadera pasión es el jazz. Pero cuando viaja a otro reino para ayudar a alguien a encontrar su pasión, pronto descubre lo que significa tener alma.")
                .director("Pete Docter, Kemp Powers")
                .minimumAge(7) // Edad mínima recomendada (PG)
                .build();

        // Configuración del comportamiento del repositorio de películas para devolver la película "Soul" cuando se busque por título
        Mockito.when(movieRepository.findByTitleIgnoreCase("Soul")).thenReturn(Optional.of(movie));
    }

    @Test
    @DisplayName("Test Find Movie By Title - Found")
    public void findByTitleIgnoreCaseTestFound(){
        // Búsqueda de la película "Soul" usando el servicio de películas
        String movieTitle = "Soul";
        Movie movie = movieService.findByTitleIgnoreCase(movieTitle).get();
        // Verificación de que el título de la película obtenida es "Soul"
        assertEquals(movieTitle, movie.getTitle());
        // Impresión de la película obtenida
        System.out.println("movie = " + movie);
    }
}