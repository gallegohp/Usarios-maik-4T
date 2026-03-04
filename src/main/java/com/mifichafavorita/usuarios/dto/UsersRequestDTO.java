package com.mifichafavorita.usuarios.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsersRequestDTO {

    // @NotBlank = no puede ser null, vacío ("") ni solo espacios ("   ")
    // message = el mensaje que verá el cliente si falla
    @NotBlank(message = "El nombre es obligatorio")
    private String name;

    // @Email = verifica que tenga formato válido: algo@algo.com
    // @NotBlank = además de ser email, no puede estar vacío
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no tiene un formato válido")
    private String email;

    // @NotNull = no puede llegar como null (pero sí puede ser 0)
    // @Min = valor mínimo permitido
    // @Max = valor máximo permitido
    @NotNull(message = "La edad es obligatoria")
    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad no puede ser mayor a 120")
    private Integer age;
}