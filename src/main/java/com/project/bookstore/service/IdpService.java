package com.project.bookstore.service;

import com.project.bookstore.dto.User;
import com.project.bookstore.entity.UserEntity;
import com.project.bookstore.exceptions.UserNameAlreadyTaken;
import com.project.bookstore.repository.UserRepository;
import com.project.bookstore.response.UserResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class IdpService {

    private final UserRepository userRepository;

    public IdpService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponse createUser(User user) throws UserNameAlreadyTaken {
        if (userRepository.existsByUsername(user.username())) {
            throw new UserNameAlreadyTaken(user.username());
        }

        UserEntity entity = new UserEntity(UUID.randomUUID().toString(), user.username(), user.name(), user.phNo(), user.password());
        UserEntity repoResponse = userRepository.save(entity);
        return new UserResponse(repoResponse.userid, repoResponse.username, repoResponse.name, repoResponse.phNo);
    }

    public UserResponse getUserByUsername(String username) throws EntityNotFoundException {
        UserEntity entity = userRepository.getByUsername(username);
        return new UserResponse(entity.userid, entity.username, entity.name, entity.phNo);
    }
}
