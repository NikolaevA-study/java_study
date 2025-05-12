package org.example.saveuserinfoservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Аннотация для Spring, чтобы сделать этот класс REST контроллером
@RequestMapping("/users") // Базовый путь для всех запросов в этом контроллере
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<List<UserDTO>> getUserByParam(@RequestBody UserDTO request) {
        return ResponseEntity.ok().body(userService.getUsersByParam(request));
    }
}
