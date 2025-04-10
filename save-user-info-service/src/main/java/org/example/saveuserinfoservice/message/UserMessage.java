package org.example.saveuserinfoservice.message;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserMessage {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private List<UserContactsMessage> contacts;
}
