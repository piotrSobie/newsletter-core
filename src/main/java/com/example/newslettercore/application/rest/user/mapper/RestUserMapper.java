package com.example.newslettercore.application.rest.user.mapper;

import com.example.newslettercore.application.rest.user.model.UserResponse;
import com.example.newslettercore.domain.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestUserMapper {

    RestUserMapper getMapper = Mappers.getMapper(RestUserMapper.class);

    UserResponse mapToUserDTO(User user);
}
