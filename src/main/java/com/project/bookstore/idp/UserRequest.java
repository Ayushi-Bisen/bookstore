package com.project.bookstore.idp;


import jakarta.validation.constraints.NotNull;

public class UserRequest {

    @NotNull(message = "The username is required.")
    String username;

    @NotNull(message = "The name is required.")
    String name;

    @NotNull(message = "The phone number is required.")
    String phNo;

    @NotNull(message = "The phone number is required.")
    String password;

    public UserRequest(String username, String name, String phNo, String password) {
        this.username = username;
        this.name = name;
        this.phNo = phNo;
        this.password = password;
    }
}
