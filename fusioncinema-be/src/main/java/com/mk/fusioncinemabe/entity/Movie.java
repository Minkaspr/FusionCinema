package com.mk.fusioncinemabe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Entity
@Table(name = "movie")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Ingrese el titulo")
    @Length(min = 4, max = 255)
    @Column(nullable = false, unique = true)
    private String title;

    @NotBlank(message = "Ingrese el genero")
    @Column(nullable = false)
    private String genre;

    @NotNull(message = "Ingrese el año de lanzamiento")
    @Past(message = "El año de lanzamiento excede al presente")
    @Column(nullable = false)
    private LocalDate releaseYear;

    @NotNull(message = "Ingrese la calificación")
    @Min(value = 0, message = "La calificación debe ser al menos 0")
    @Max(value = 5, message = "La calificación debe ser como máximo 5")
    @Column(nullable = false)
    private Double rating;

    @NotBlank(message = "Ingrese la sinopsis")
    @Column(nullable = false, columnDefinition = "TEXT")
    private String synopsis;

    @NotBlank(message = "Ingrese el director")
    @Column(nullable = false)
    private String director;

    @NotNull(message = "Ingrese la edad mínima recomendada")
    @Min(value = 0, message = "La edad mínima recomendada debe ser al menos 0")
    @Column(nullable = false)
    private Integer minimumAge; // Edad mínima recomendada
}
