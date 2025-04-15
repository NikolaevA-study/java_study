package org.example.saveuserinfoservice.mapper;

import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = { UserContactsMapper.class })
public interface UserMapper {
    @Mapping(source = "userContactsModel", target = "contacts")
    UserDTO toDTO(UserModel user);
    @Mapping(source = "contacts", target = "userContactsModel")
    UserModel toEntity(UserDTO dto);
}
