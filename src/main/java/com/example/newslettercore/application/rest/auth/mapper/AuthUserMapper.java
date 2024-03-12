package com.example.newslettercore.application.rest.auth.mapper;

import com.example.newslettercore.application.rest.auth.model.AuthUser;
import com.example.newslettercore.domain.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthUserMapper {

    AuthUserMapper getMapper = Mappers.getMapper(AuthUserMapper.class);

    AuthUser userToAuthUser(User user);
}
