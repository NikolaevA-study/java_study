package com.example.send_user_info_service.model;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UserModel {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private List<UserContactsModel> contacts;
}
