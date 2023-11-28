package com.project.bookstore.idp;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
public class IdpController {

    private IdpService idpService;

    public IdpController(final IdpService idpService) {
        this.idpService = idpService;
    }

    @PostMapping("idp/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User response = idpService.createUser(user);
        return ResponseEntity.ok(response);
    }
}
