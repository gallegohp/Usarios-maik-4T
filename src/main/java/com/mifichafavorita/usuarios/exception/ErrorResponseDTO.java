package com.mifichafavorita.usuarios.exception;

import java.time.LocalDateTime;

import lombok.Data;

// Este objeto es lo que se serializa a JSON cuando hay un error
// El cliente siempre va a recibir la misma estructura clara
@Data
public class ErrorResponseDTO {

    private int status;           // Código HTTP: 404, 400, 500...
    private String message;       // Mensaje legible del error
    private LocalDateTime timestamp; // Cuándo ocurrió el error

    public ErrorResponseDTO(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now(); // Se llena automáticamente
    }
}