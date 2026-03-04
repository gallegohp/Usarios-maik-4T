package com.mifichafavorita.usuarios.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mifichafavorita.usuarios.dto.UsersRequestDTO;
import com.mifichafavorita.usuarios.dto.UsersResponseDTO;
import com.mifichafavorita.usuarios.services.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    // Ya no hay try/catch — si algo falla, el GlobalExceptionHandler lo captura
    @PostMapping
    public ResponseEntity<UsersResponseDTO> createUser(@RequestBody UsersRequestDTO usersRequestDTO) {
        UsersResponseDTO responseDTO = usersService.createUser(usersRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build(); // 204
    }

    


    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateUser(@PathVariable Integer id, @RequestBody UsersRequestDTO requestDTO) {
        UsersResponseDTO responseDTO = usersService.updateUser(id, requestDTO);
        return ResponseEntity.ok(responseDTO); // 200
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDTO>> getUsers() {
        return ResponseEntity.ok(usersService.getUsers()); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> getUserId(@PathVariable Integer id) {
        return ResponseEntity.ok(usersService.getUserId(id)); // 200
    }

    @PatchMapping("/update-user/{id}")
    public ResponseEntity<UsersResponseDTO> patchUser(@PathVariable Integer id,
                                                    @RequestBody UsersRequestDTO usersRequestDTO) {
        UsersResponseDTO responseDTO = usersService.patchUser(id, usersRequestDTO);
        return ResponseEntity.ok(responseDTO); // 200
    }
}