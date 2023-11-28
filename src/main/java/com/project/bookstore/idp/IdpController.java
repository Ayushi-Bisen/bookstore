package com.project.bookstore.idp;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class IdpController {

    private IdpService idpService;

    public IdpController(final IdpService idpService) {
        this.idpService = idpService;
    }

    @PostMapping("idp/create-user")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest user) {
        try {
            UserResponse response = idpService.createUser(new User("", user.username, user.name, user.phNo, "pwd"));
            return ResponseEntity.ok(response);
        } catch (UserNameAlreadyTaken e) {
            String body =  "{\"errCode\": \"USERNAME_ALREADY_TAKEN\", \"message\":\"" + e.getMessage() + "\"}";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
        }
    }
}
