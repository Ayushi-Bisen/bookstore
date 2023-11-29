package com.project.bookstore.idp;


import jakarta.validation.constraints.*;

public class UserRequest {

    @Email
    @NotBlank(message = "The username is required.")
    String username;

    @NotBlank(message = "The name is required.")
    String name;

    @NotBlank(message = "The phone number is required.")
    @Pattern(regexp = "^[0-9]{10}$", message="Phone number is not valid")
    String phNo;

//    @NotBlank(message = "The password is required.")
    String password;

    public UserRequest(String username, String name, String phNo, String password) {
        this.username = username;
        this.name = name;
        this.phNo = phNo;
        this.password = password;
    }
}
