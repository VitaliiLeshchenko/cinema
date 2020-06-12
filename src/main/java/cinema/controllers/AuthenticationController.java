package cinema.controllers;

import cinema.model.dto.UserRequestDto;
import cinema.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public void registerUser(@RequestBody UserRequestDto dto) {
        authenticationService.register(dto.getEmail(), dto.getPassword());
    }
}
