package cinema.service.impl;

import cinema.exception.AuthenticationException;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (passwordEncoder.matches(password, userFromDb.getPassword())) {
            return userFromDb;
        } else {
            throw new AuthenticationException("Wrong email or password");
        }
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        user.setPassword(password);
        user.setEmail(email);
        user = userService.add(user);
        return user;
    }
}
