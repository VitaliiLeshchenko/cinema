package cinema.service.impl;

import cinema.exception.AuthenticationException;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder encoder) {
        this.userService = userService;
        passwordEncoder = encoder;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (passwordEncoder.encode(password).equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect EMAIL or PASSWORD");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(password));
        user.setEmail(email);
        user = userService.add(user);
        return user;
    }
}
