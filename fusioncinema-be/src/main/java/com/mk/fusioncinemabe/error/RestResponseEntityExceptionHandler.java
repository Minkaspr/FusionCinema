package com.mk.fusioncinemabe.error;

import com.mk.fusioncinemabe.error.dto.ApiResponse;
import com.mk.fusioncinemabe.error.dto.ErrorMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

// RestResponseEntityExceptionHandler es un controlador de excepciones
// que maneja las excepciones lanzadas en los controladores.
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    // Este método maneja las excepciones MovieNotFoundException y
    // devuelve una respuesta con un mensaje de error.
    @ExceptionHandler(MovieNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMessage> localNotFoundException(MovieNotFoundException exception){
        ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    // Este método maneja las excepciones MethodArgumentNotValidException,
    // que se lanzan cuando falla la validación de un argumento de método.
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        ApiResponse response = new ApiResponse(HttpStatus.BAD_REQUEST.value(), "Error de validación", null, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Este método maneja las excepciones DataIntegrityViolationException,
    // que se lanzan cuando se viola una restricción de integridad de la base de datos.
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorMessage> handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        String message = "El campo debe ser único. Por favor, intenta con un valor diferente.";
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.BAD_REQUEST, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }
}
