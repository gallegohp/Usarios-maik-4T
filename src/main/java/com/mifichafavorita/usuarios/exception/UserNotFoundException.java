package com.mifichafavorita.usuarios.exception;

// Extiende RuntimeException para que Spring pueda interceptarla
// "Runtime" significa que no obliga al programador a hacer try/catch explícito
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        // Le pasamos el mensaje al padre (RuntimeException)
        super("Usuario no encontrado con id: " + id);
    }
}