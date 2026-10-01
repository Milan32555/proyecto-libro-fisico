package com.libreria.servidor.excepciones;


public class LibroException extends RuntimeException {
    public LibroException(String mensaje) {
        super(mensaje);
    }
}
