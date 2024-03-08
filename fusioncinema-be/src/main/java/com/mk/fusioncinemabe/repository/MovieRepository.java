package com.mk.fusioncinemabe.repository;

import com.mk.fusioncinemabe.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    /*
     * Consulta con JPQL:
     * Este método utiliza la sintaxis de Java Persistence Query Language (JPQL)
     *   para definir la consulta.
     * El nombre del método incluye 'WithJPQL' para indicar que se utiliza JPQL.
     * La consulta en sí se define en la anotación @Query.
     * En este caso, la consulta selecciona una película (m) donde el título de
     *   la película coincide con el parámetro proporcionado (:title).
     */
    @Query("SELECT m FROM Movie m WHERE m.title = :title")
    Optional<Movie> findMovieByTitleWithJPQL(String title);

    /*
     * Consulta con Inversión de Control:
     * Estos métodos utilizan el mecanismo de Inversión de Control de Spring Data JPA
     *   para generar automáticamente las consultas.
     * El nombre del método describe la consulta que se va a realizar.
     * Por ejemplo, 'findByTitle' generará una consulta que selecciona una película donde
     *   el título coincide con el parámetro proporcionado.
     * 'findByTitleIgnoreCase' hace lo mismo, pero ignora las diferencias de mayúsculas y
     *   minúsculas.
     */
    Optional<Movie> findByTitle(String title);

    Optional<Movie> findByTitleIgnoreCase(String title);

}
