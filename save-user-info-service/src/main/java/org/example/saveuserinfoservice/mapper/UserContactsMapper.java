package org.example.saveuserinfoservice.mapper;

import org.example.saveuserinfoservice.dto.UserContactsDTO;
import org.example.saveuserinfoservice.model.UserContactsModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserContactsMapper {
    UserContactsDTO toDTO(UserContactsModel user);
    UserContactsModel toEntity(UserContactsDTO dto);
}
