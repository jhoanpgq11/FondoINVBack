package com.api.ejemplo.demo.exepciones;

import org.springframework.web.bind.annotation.ResponseStatus;



public class ModelNotFoundException extends RuntimeException {
    public ModelNotFoundException(String mensaje) {
        super(mensaje);
    }
}