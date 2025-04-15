package org.example.saveuserinfoservice.mapper;

import org.example.saveuserinfoservice.dto.UserDTO;
import org.example.saveuserinfoservice.model.UserModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(UserModel user);
    UserModel toEntity(UserDTO dto);
}
