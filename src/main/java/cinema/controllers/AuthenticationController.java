package cinema.controllers;

import cinema.model.dto.UserRegistrationDto;
import cinema.service.AuthenticationService;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public void registerUser(@RequestBody @Valid UserRegistrationDto dto) {
        authenticationService.register(dto.getEmail(), dto.getPassword());
    }
}
