package com.project.bookstore.idp;

import org.springframework.stereotype.Service;

@Service
public class IdpService {
    public User createUser(User user) {
        return new User("id", "username", "name", "phno");
    }
}
