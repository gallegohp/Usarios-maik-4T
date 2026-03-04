package com.mifichafavorita.usuarios.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.mifichafavorita.usuarios.dto.UsersRequestDTO;
import com.mifichafavorita.usuarios.dto.UsersResponseDTO;
import com.mifichafavorita.usuarios.entity.Users;
import com.mifichafavorita.usuarios.exception.UserNotFoundException;
import com.mifichafavorita.usuarios.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;

    public UsersResponseDTO createUser(UsersRequestDTO usersRequestDTO) {
        Users user = new Users();
        user.setName(usersRequestDTO.getName());
        user.setEmail(usersRequestDTO.getEmail());
        user.setAge(usersRequestDTO.getAge());

        usersRepository.save(user);

        UsersResponseDTO usersResponseDTO = new UsersResponseDTO();
        usersResponseDTO.setId(user.getId());
        usersResponseDTO.setName(user.getName());
        usersResponseDTO.setEmail(user.getEmail());
        usersResponseDTO.setAge(user.getAge());

        return usersResponseDTO;
    }

    public void deleteUser(Integer id) {
        // Ahora lanzamos nuestra excepción personalizada
        if (!usersRepository.existsById(id)) {
            throw new UserNotFoundException(id); //metodo que maneja la exception, "NO ENCONTRADO", el cual es global, y manda la excepcion
        }
        usersRepository.deleteById(id);
    }

    public UsersResponseDTO updateUser(Integer id, UsersRequestDTO usersRequestDTO) {
        // orElseThrow: si no lo encuentra, lanza la excepción directamente
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

        user.setName(usersRequestDTO.getName());
        user.setEmail(usersRequestDTO.getEmail());
        user.setAge(usersRequestDTO.getAge());

        usersRepository.save(user);

        UsersResponseDTO usersResponseDTO = new UsersResponseDTO();
        usersResponseDTO.setId(user.getId());
        usersResponseDTO.setName(user.getName());
        usersResponseDTO.setEmail(user.getEmail());
        usersResponseDTO.setAge(user.getAge());

        return usersResponseDTO;
    }

    public List<UsersResponseDTO> getUsers() {
        // Usamos stream en vez del for manual — más moderno y limpio
        return usersRepository.findAll()
            .stream()
            .map(user -> {
                UsersResponseDTO dto = new UsersResponseDTO();
                dto.setId(user.getId());
                dto.setName(user.getName());
                dto.setEmail(user.getEmail());
                dto.setAge(user.getAge());
                return dto;
            })
            .collect(Collectors.toList());
    }

    public UsersResponseDTO getUserId(Integer id) {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

        UsersResponseDTO responseDTO = new UsersResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setAge(user.getAge());

        return responseDTO;
    }

    public UsersResponseDTO patchUser(Integer id, UsersRequestDTO usersRequestDTO) {
        Users user = usersRepository.findById(id)
            .orElseThrow(() -> new UserNotFoundException(id));

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
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setAge(user.getAge());

        return responseDTO;
    }
}