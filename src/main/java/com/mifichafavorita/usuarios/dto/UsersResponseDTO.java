package com.mifichafavorita.usuarios.dto;

import lombok.Data;

@Data
public class UsersResponseDTO {
    private Integer id;

    private String name;

    private String email;

    private Integer age;
}
