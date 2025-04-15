package org.example.saveuserinfoservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Аннотация для Spring, чтобы сделать этот класс REST контроллером
@RequestMapping("/users") // Базовый путь для всех запросов в этом контроллере
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/search")
    public List<UserDTO> getUserByParam(@RequestBody UserDTO request) {
        return userService.getUsersByParam(request);
    }
}
