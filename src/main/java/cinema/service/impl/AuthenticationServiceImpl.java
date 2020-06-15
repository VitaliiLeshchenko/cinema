package cinema.service.impl;

import cinema.exception.AuthenticationException;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.UserService;
import cinema.util.HashUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final HashUtil hashUtil;

    public AuthenticationServiceImpl(UserService userService, HashUtil hashUtil) {
        this.userService = userService;
        this.hashUtil = hashUtil;
    }

    @Override
    public User login(String email, String password) throws AuthenticationException {
        User userFromDb = userService.findByEmail(email);
        if (hashUtil.hashPassword(password, userFromDb.getSalt())
                .equals(userFromDb.getPassword())) {
            return userFromDb;
        }
        throw new AuthenticationException("Incorrect EMAIL or PASSWORD");
    }

    @Override
    public User register(String email, String password) {
        User user = new User();
        byte[] salt = hashUtil.getSalt();
        user.setSalt(salt);
        user.setPassword(hashUtil.hashPassword(password, salt));
        user.setEmail(email);
        user = userService.add(user);
        return user;
    }
}
