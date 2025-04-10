package org.example.saveuserinfoservice.service;

import lombok.RequiredArgsConstructor;
import org.example.saveuserinfoservice.message.UserMessage;
import org.example.saveuserinfoservice.model.UserModel;
import org.example.saveuserinfoservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<UserModel> getAllUsers() {
        return userRepository.findAll();
    }

    public List<UserModel> getUsersByParam(UserMessage param) {
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

    public List<UserModel> getUsersByName(String name) {
        return userRepository.findUsersModelByName(name);
    }

    public List<UserModel> getUsersBySurname(String surname) {
        return userRepository.findUsersModelBySurname(surname);
    }

    public List<UserModel> getUsersByNameAndSurname(String name,String surname) {
        return userRepository.findUsersModelByNameAndSurname(name,surname);
    }
}
