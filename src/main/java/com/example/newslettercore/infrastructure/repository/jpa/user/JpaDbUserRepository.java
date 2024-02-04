package com.example.newslettercore.infrastructure.repository.jpa.user;

import com.example.newslettercore.domain.user.model.UserCreateDTO;
import com.example.newslettercore.domain.user.model.UserDTO;
import com.example.newslettercore.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaDbUserRepository implements UserRepository {

    private final SpringDataJpaUserRepository userRepository;

    @Autowired
    public JpaDbUserRepository(SpringDataJpaUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDTO save(UserCreateDTO userCreateDTO) {

        UserEntity userEntity = UserMapper.getMapper.mapToUserEntity(userCreateDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.getMapper.mapToUserDTO(savedUser);
    }

    @Override
    public UserDTO save(UserDTO userDTO) {

        UserEntity userEntity = UserMapper.getMapper.mapToUserEntity(userDTO);
        UserEntity savedUser = userRepository.save(userEntity);
        return UserMapper.getMapper.mapToUserDTO(savedUser);
    }

    @Override
    public Optional<UserDTO> findById(String userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            UserDTO userDTO = UserMapper.getMapper.mapToUserDTO(userOptional.get());
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDTO> findByEmail(String email) {

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            UserDTO userDTO = UserMapper.getMapper.mapToUserDTO(userOptional.get());
            return Optional.of(userDTO);
        } else {
            return Optional.empty();
        }
    }
}
