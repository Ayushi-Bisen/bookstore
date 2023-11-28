package com.project.bookstore.idp;

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
}
