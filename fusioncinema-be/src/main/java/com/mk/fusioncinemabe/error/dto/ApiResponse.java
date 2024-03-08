package com.mk.fusioncinemabe.error.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// La clase ApiResponse es un objeto de transferencia de datos (DTO) que
// se utiliza para estandarizar las respuestas de la API.
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private int status;     // El código de estado HTTP de la respuesta.
    private String message; // Un mensaje descriptivo sobre la respuesta.
    private Object data;    // Los datos que se devuelven en la respuesta, si los hay.
    private Object errors;  // Cualquier error que ocurrió durante el procesamiento de la solicitud.
}
