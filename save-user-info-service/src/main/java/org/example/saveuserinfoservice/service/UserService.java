package org.example.saveuserinfoservice.service;

import lombok.RequiredArgsConstructor;
import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.mapper.UserMapper;
import org.example.saveuserinfoservice.model.UserModel;
import org.example.saveuserinfoservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public List<UserDTO> getAllUsers() {
        return convertToDTO(userRepository.findAll());
    }

    public List<UserDTO> getUsersByParam(UserDTO param) {
        String name = param.getName();
        String surname = param.getSurname();
        if (name != null && surname != null) {
            return getUsersByNameAndSurname(name, surname);
        } else if (name != null) {
            return getUsersByName(name);
        } else if (surname != null) {
            return getUsersBySurname(surname);
        }
        return getAllUsers();
    }

    public List<UserDTO> getUsersByName(String name) {
        return convertToDTO(userRepository.findByName(name));
    }

    public List<UserDTO> getUsersBySurname(String surname) {
        return convertToDTO(userRepository.findBySurname(surname));
    }

    public List<UserDTO> getUsersByNameAndSurname(String name,String surname) {
        return convertToDTO(userRepository.findByNameAndSurname(name,surname));
    }

    private List<UserDTO> convertToDTO(List<UserModel> users) {
        return users.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }
}
