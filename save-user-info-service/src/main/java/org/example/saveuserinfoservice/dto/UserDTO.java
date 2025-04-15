package org.example.saveuserinfoservice.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private List<UserContactsDTO> contacts;
}
