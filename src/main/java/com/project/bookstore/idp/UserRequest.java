package com.project.bookstore.idp;


import jakarta.validation.constraints.NotNull;

public class UserRequest {

    @NotNull(message = "The username is required.")
    String username;

    @NotNull(message = "The name is required.")
    String name;

    @NotNull(message = "The phone number is required.")
    String phNo;

    public UserRequest(String username, String name, String phNo) {
        this.username = username;
        this.name = name;
        this.phNo = phNo;
    }
}
