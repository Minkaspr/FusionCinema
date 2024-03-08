package com.mk.fusioncinemabe.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

// La clase ErrorMessage es un DTO que se utiliza para estandarizar
// los mensajes de error.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorMessage {
    private HttpStatus status;  // El código de estado HTTP del error.
    private String message;     // Un mensaje descriptivo sobre el error.
}
