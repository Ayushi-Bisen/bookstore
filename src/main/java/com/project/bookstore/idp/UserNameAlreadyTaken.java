package com.project.bookstore.idp;

public class UserNameAlreadyTaken extends Exception {

    public UserNameAlreadyTaken(String username) {
        super("Username is already taken" + username);
    }
}
