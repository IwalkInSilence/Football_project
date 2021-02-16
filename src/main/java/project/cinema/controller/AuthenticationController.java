package project.cinema.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.cinema.model.dto.UserRequestDto;
import project.cinema.security.AuthenticationService;

@RestController
public class AuthenticationController {
    private AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRequestDto dto) {
        authenticationService.register(dto.getEmail(), dto.getPassword());
    }
}
