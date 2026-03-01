package com.mifichafavorita.usuarios.services;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mifichafavorita.usuarios.dto.UsersRequestDTO;
import com.mifichafavorita.usuarios.dto.UsersResponseDTO;
import com.mifichafavorita.usuarios.entity.Users;
import com.mifichafavorita.usuarios.repository.UsersRepository;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Service //automatico crea un bean
public class UsersService {
    private final UsersRepository usersRepository;
    
    public UsersResponseDTO createUser(UsersRequestDTO usersRequestDTO){
        Users user = new Users();
        user.setName(usersRequestDTO.getName()); //metodo del request
        user.setEmail(usersRequestDTO.getEmail());
        user.setAge(usersRequestDTO.getAge());

        usersRepository.save(user); //metodo del repositorio
        
        UsersResponseDTO usersResponseDTO = new UsersResponseDTO();
        usersResponseDTO.setId(user.getId()); //metodo del response
        usersResponseDTO.setName(user.getName());
        usersResponseDTO.setEmail(user.getEmail());
        usersResponseDTO.setAge(user.getAge());

        return usersResponseDTO;

    }

    public void deleteUser(Integer id) {
    // Si no lo encuentra, lanza la excepción de una vez
        Optional<Users> userOptional = usersRepository.findById(id);
        if (!userOptional.isPresent()) {
            System.out.println("Usuario no encontrado");
        }else{
            usersRepository.deleteById(id); //metodo del repositorio
        }
    }

    public UsersResponseDTO updateUser(Integer id, UsersRequestDTO usersRequestDTO){
        Users user = usersRepository.getReferenceById(id);

        user.setName(usersRequestDTO.getName()); //metodo del request
        user.setEmail(usersRequestDTO.getEmail());
        user.setAge(usersRequestDTO.getAge());

        usersRepository.save(user); //metodo del repositorio
        UsersResponseDTO usersResponseDTO = new UsersResponseDTO();

        usersResponseDTO.setId(user.getId()); //metodo del response
        usersResponseDTO.setName(user.getName());
        usersResponseDTO.setEmail(user.getEmail());
        usersResponseDTO.setAge(user.getAge());

        return usersResponseDTO;
    }
    
    public List<UsersResponseDTO> getUsers(){
        List<Users> users = usersRepository.findAll();
        List<UsersResponseDTO> listUsers = new ArrayList<>();

        for (Users user: users){
            UsersResponseDTO usersResponseDTO = new UsersResponseDTO();
            usersResponseDTO.setId(user.getId());
            usersResponseDTO.setName(user.getName());
            usersResponseDTO.setEmail(user.getEmail());
            usersResponseDTO.setAge(user.getAge());
            listUsers.add(usersResponseDTO);
        }
        return listUsers;
    }

    public UsersResponseDTO getUserId(Integer id){
        Optional<Users> userOptional = usersRepository.findById(id);

        if (userOptional.isPresent()) {
            Users user = userOptional.get();

            UsersResponseDTO responseDTO = new UsersResponseDTO();
            responseDTO.setId(user.getId());
            responseDTO.setName(user.getName());
            responseDTO.setEmail(user.getEmail());
            responseDTO.setAge(user.getAge());

            return responseDTO;
        } else {
            throw new RuntimeException("User not found");
        }

    }

    public UsersResponseDTO patchUser(Integer id, UsersRequestDTO usersRequestDTO){
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("User not found"));
            
        if (usersRequestDTO.getName() != null) {
            user.setName(usersRequestDTO.getName());
        }
        if (usersRequestDTO.getEmail() != null) {
            user.setEmail(usersRequestDTO.getEmail());
        }
        if (usersRequestDTO.getAge() != null) {
            user.setAge(usersRequestDTO.getAge());
        }

        usersRepository.save(user);

        UsersResponseDTO responseDTO = new UsersResponseDTO();
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setAge(user.getAge());

        return responseDTO;
    }
}
