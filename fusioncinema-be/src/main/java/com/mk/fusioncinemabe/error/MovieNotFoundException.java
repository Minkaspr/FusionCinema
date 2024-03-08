package com.mk.fusioncinemabe.error;

// MovieNotFoundException es una excepción personalizada que se
// lanza cuando no se puede encontrar una película.
public class MovieNotFoundException extends Exception{
    public MovieNotFoundException(String message){
        super(message);
    }
}
