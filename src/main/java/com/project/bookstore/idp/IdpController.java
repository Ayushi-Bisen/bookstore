package com.project.bookstore.idp;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest user) {
        User response = idpService.createUser(new User("", user.username, user.name, user.phNo));
        return ResponseEntity.ok(response);
    }
}
