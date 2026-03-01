package com.mifichafavorita.usuarios.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifichafavorita.usuarios.dto.UsersRequestDTO;
import com.mifichafavorita.usuarios.dto.UsersResponseDTO;
import com.mifichafavorita.usuarios.services.UsersService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;


@RestController //controlador de tipo rest
@RequiredArgsConstructor //Reemplaza el constructor para todas las importaciones
@RequestMapping("/users") //ruta a la que lleva el controlador (contextPath)

public class UsersController {
    private final UsersService usersService; //bean de service, inyectando dependencias
    

    @PostMapping()//peticion del POST
    //ResponseEntity (codigo personalizable)
    public ResponseEntity<UsersResponseDTO> createUser( @RequestBody UsersRequestDTO usersRequestDTO){ 
        try {
            //se resive y especifica que es por el cuerpo (la anotacion)
            UsersResponseDTO responseDTO = usersService.createUser(usersRequestDTO); //envia a service el usuario
    
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
            
        } catch (Exception e) {
    
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id){
        try {
            usersService.deleteUser(id); //envia a service el usuario, para ver 
    
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> updateUser (@PathVariable Integer id, @RequestBody UsersRequestDTO requestDTO ){
        try {
            
            UsersResponseDTO usersResponseDTO = usersService.updateUser(id,requestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(usersResponseDTO);

        } catch (Exception e) {
            UsersResponseDTO usersResponseDTO = usersService.updateUser(id, requestDTO);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usersResponseDTO);
        }
    }

    @GetMapping
    public ResponseEntity<List<UsersResponseDTO>> getUser(){
        try {
            List<UsersResponseDTO> response = usersService.getUsers();
    
            return ResponseEntity.status(HttpStatus.FOUND).body(response);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersResponseDTO> getUserId (@PathVariable Integer id) {
        try {
            
            UsersResponseDTO responseDTO = usersService.getUserId(id);

            return ResponseEntity.status(HttpStatus.FOUND).body(responseDTO);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
    
    @PatchMapping("/update-user/{id}")
    public ResponseEntity<UsersResponseDTO> patchUser (@PathVariable Integer id, @RequestBody UsersRequestDTO usersRequestDTO){
        try {
            UsersResponseDTO responseDTO = usersService.patchUser(id, usersRequestDTO);

            return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
