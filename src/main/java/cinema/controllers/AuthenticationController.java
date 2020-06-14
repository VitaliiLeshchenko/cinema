package cinema.controllers;

import cinema.model.User;
import cinema.model.dto.UserRequestDto;
import cinema.service.AuthenticationService;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping
    public void registerUser(@RequestBody UserRequestDto dto) {
        User user = authenticationService.register(dto.getEmail(), dto.getPassword());
        shoppingCartService.registerNewShoppingCart(user);
    }
}
