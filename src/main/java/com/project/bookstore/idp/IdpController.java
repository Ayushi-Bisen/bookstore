package com.project.bookstore.idp;

import jakarta.validation.Valid;
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
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest user) {
        User response = idpService.createUser(new User("", user.username, user.name, user.phNo, "pwd"));
        return ResponseEntity.ok(new UserResponse(response.userid(), response.username(), response.name(), response.phNo()));
    }
}
