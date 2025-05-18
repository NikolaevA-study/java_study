package org.example.saveuserinfoservice.utils;

import org.example.saveuserinfoservice.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator
{
    @Override
    public boolean supports(Class<?> clazz) {
        return UserDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserDTO user = (UserDTO) target;
        String name = user.getName();
        String surname = user.getSurname();
        boolean isEmptyNameAndSurname = name == null && surname == null;
        if (isEmptyNameAndSurname)
            errors.reject("","Either name or surname must be provided");
    }
}
