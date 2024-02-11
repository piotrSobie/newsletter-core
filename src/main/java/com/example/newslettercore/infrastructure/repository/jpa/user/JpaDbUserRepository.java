package com.example.newslettercore.infrastructure.repository.jpa.user;

import com.example.newslettercore.domain.user.model.User;
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
    public User save(User user) {

        UserEntity userEntity = JpaDbUserMapper.getMapper.mapToUserEntity(user);
        UserEntity savedUser = userRepository.save(userEntity);
        return JpaDbUserMapper.getMapper.mapToUser(savedUser);
    }

    @Override
    public Optional<User> findById(String userId) {

        Optional<UserEntity> userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            User user = JpaDbUserMapper.getMapper.mapToUser(userOptional.get());
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {

        Optional<UserEntity> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = JpaDbUserMapper.getMapper.mapToUser(userOptional.get());
            return Optional.of(user);
        } else {
            return Optional.empty();
        }
    }
}
