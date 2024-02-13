package com.example.newslettercore.infrastructure.repository.jpa.user;

import com.example.newslettercore.domain.user.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface JpaUserMapper {

    JpaUserMapper getMapper = Mappers.getMapper(JpaUserMapper.class);

    UserEntity mapToUserEntity(User user);

    default User mapToUser(UserEntity userEntity) {

        return new User(userEntity.getId(), userEntity.getName(), userEntity.getPassword(), userEntity.getEmail());
    }
}