package com.mk.fusioncinemabe.repository;

import com.mk.fusioncinemabe.entity.Movie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MovieRepositoryTest {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @BeforeEach
    void setUp() {
        // Creación de una nueva película usando el patrón Builder
        Movie movie = Movie.builder()
                .title("Soul")
                .genre("Animation, Adventure, Comedy")
                .releaseYear(LocalDate.of(2020, 12, 25))
                .rating(4.0)
                .synopsis("Joe es un profesor de música de secundaria cuya vida no ha salido como esperaba. Su verdadera pasión es el jazz. Pero cuando viaja a otro reino para ayudar a alguien a encontrar su pasión, pronto descubre lo que significa tener alma.")
                .director("Pete Docter, Kemp Powers")
                .minimumAge(7) // Edad mínima recomendada (PG)
                .build();

        // Persistencia de la película en la base de datos en memoria
        testEntityManager.persist(movie);
    }

    @Test
    public void findByTitleIgnoreCaseTestFound(){
        // Búsqueda de la película "Soul" en la base de datos en memoria
        Optional<Movie> movie = movieRepository.findByTitleIgnoreCase("Soul");
        // Verificación de que el título de la película obtenida es "Soul"
        assertEquals(movie.get().getTitle(), "Soul");
        // Impresión de la película obtenida
        System.out.println("movie.get(): "+movie.get());
    }

    @Test
    public void findByTitleIgnoreCaseTestNotFound(){
        // Búsqueda de la película "Ramona" en la base de datos en memoria
        Optional<Movie> movie = movieRepository.findByTitleIgnoreCase("Ramona");
        // Verificación de que no se encontró ninguna película con el título "Ramona"
        assertEquals(movie, Optional.empty());
    }
}