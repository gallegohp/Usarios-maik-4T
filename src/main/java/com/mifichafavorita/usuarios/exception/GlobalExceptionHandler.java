package com.mifichafavorita.usuarios.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

// @ControllerAdvice = "escucha" todos los controllers de la aplicación
// Cuando cualquier controller lanza una excepción, Spring la redirige aquí
@ControllerAdvice
public class GlobalExceptionHandler {

    // @ExceptionHandler le dice: "cuando se lance UserNotFoundException, ejecuta este método"
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFound(UserNotFoundException ex) {

        // Construimos el JSON de error con el mensaje de la excepción
        ErrorResponseDTO error = new ErrorResponseDTO(
            HttpStatus.NOT_FOUND.value(), // 404
            ex.getMessage()              // "Usuario no encontrado con id: 5"
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // Captura cualquier otro error inesperado que no hayamos previsto
    // Es como una red de seguridad final
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex) {

        ErrorResponseDTO error = new ErrorResponseDTO(
            HttpStatus.INTERNAL_SERVER_ERROR.value(), // 500
            "Ocurrió un error interno en el servidor"
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}