package org.example.saveuserinfoservice.controller;
import lombok.RequiredArgsConstructor;
import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.service.UserService;
import org.example.saveuserinfoservice.utils.UserValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController // Аннотация для Spring, чтобы сделать этот класс REST контроллером
@RequestMapping("/users") // Базовый путь для всех запросов в этом контроллере
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserValidator userValidator;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @GetMapping("/search")
    public ResponseEntity<?> getUserByParam(@ModelAttribute UserDTO user,
                                            BindingResult bindingResult) {
        userValidator.validate(user,bindingResult);
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().getFirst().getDefaultMessage();
            return ResponseEntity.ok().body(Map.of("errorMsg", errorMsg));
        }
        return ResponseEntity.ok().body(userService.getUsersByParam(user));
    }
}
