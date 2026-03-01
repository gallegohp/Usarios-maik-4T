package com.mifichafavorita.usuarios.dto;

import lombok.Data;
import lombok.NonNull;

@Data //crea los setters y getters
public class UsersRequestDTO {
    @NonNull 
    private String name;

    private String email;
    
    private Integer age;
}
