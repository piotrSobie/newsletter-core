package com.example.newslettercore.infrastructure.repository.jpa.user;

import com.example.newslettercore.domain.user.model.User;
import com.example.newslettercore.domain.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class JpaUserRepository implements UserRepository {

    private final SpringDataJpaUserRepository userRepository;

    @Autowired
    public JpaUserRepository(SpringDataJpaUserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        UserEntity userEntity = JpaUserMapper.getMapper.mapToUserEntity(user);
        UserEntity savedUser = userRepository.save(userEntity);
        return JpaUserMapper.getMapper.mapToUser(savedUser);
    }

    @Override
    public Optional<User> findById(String userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);
        return userOptional.map(JpaUserMapper.getMapper::mapToUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        return userOptional.map(JpaUserMapper.getMapper::mapToUser);
    }
}
