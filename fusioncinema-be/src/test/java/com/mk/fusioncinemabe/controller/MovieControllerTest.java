package com.mk.fusioncinemabe.controller;

import com.mk.fusioncinemabe.entity.Movie;
import com.mk.fusioncinemabe.service.MovieService;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovieService movieService;

    private Movie movie;

    @BeforeEach
    void setUp() {
        movie = Movie.builder()
                .title("Godzilla x Kong: The New Empire")
                .genre("Action, Sci-Fi")
                .releaseYear(LocalDate.of(2024, 4, 5)) // Fecha de lanzamiento: 5 de abril de 2024
                .rating(5.0)
                .synopsis("Esta última entrada en la franquicia Monsterverse sigue la explosiva confrontación de Godzilla vs. Kong con una nueva aventura cinematográfica...")
                .director("Adam Wingard")
                .minimumAge(13) // Edad mínima recomendada (PG-13)
                .build();
    }

    @Test
    @DisplayName("Test Save Movie - Success")
    public void saveMovie() throws Exception{
        // Creación de una nueva película para simular la solicitud POST
        Movie postMovie = Movie.builder()
                .title("Godzilla x Kong: The New Empire")
                .genre("Action, Sci-Fi")
                .releaseYear(LocalDate.of(2024, 4, 5)) // Fecha de lanzamiento: 5 de abril de 2024
                .rating(5.0)
                .synopsis("Esta última entrada en la franquicia Monsterverse sigue la explosiva confrontación de Godzilla vs.")
                .director("Adam Wingard")
                .minimumAge(13) // Edad mínima recomendada (PG-13)
                .build();

        // Configuración del comportamiento del servicio de películas para devolver la película cuando se llame al método createMovie
        Mockito.when(movieService.createMovie(postMovie)).thenReturn(movie);
        // Simulación de una solicitud POST a "/saveMovie" y verificación de que la respuesta tiene un estado HTTP 200 (OK)
        mockMvc.perform(post("/saveMovie").contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "   \"title\":\"Godzilla x Kong: The New Empire\",\n" +
                        "   \"genre\":\"Action, Sci-Fi\",\n" +
                        "   \"releaseYear\":\"2024-04-05\",\n" +
                        "   \"rating\":5.0,\n" +
                        "   \"synopsis\":\"Esta última entrada en la franquicia Monsterverse sigue la explosiva confrontación de Godzilla vs.\",\n" +
                        "   \"director\":\"Adam Wingard\",\n" +
                        "   \"minimumAge\":13\n" +
                        "}\n"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test Find Movie By ID - Found")
    public void findMovieById() throws Exception{
        // Configuración del comportamiento del servicio de películas para devolver la película cuando se llame al método getMovie
        Mockito.when(movieService.getMovie(1L)).thenReturn(movie);
        // Simulación de una solicitud GET a "/getMovie/1" y verificación de que la respuesta tiene un estado HTTP 200 (OK) y
        // que el título de la película en la respuesta coincide con el título de la película esperada
        mockMvc.perform(get("/getMovie/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(movie.getTitle()));
    }
}