package com.example.newslettercore.infrastructure.repository.jpa.user;

import com.example.newslettercore.domain.user.model.UserCreateDTO;
import com.example.newslettercore.domain.user.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper getMapper = Mappers.getMapper(UserMapper.class);

    UserEntity mapToUserEntity(UserCreateDTO userCreateDTO);

    UserEntity mapToUserEntity(UserDTO userDTO);

    UserDTO mapToUserDTO(UserEntity userEntity);
}
