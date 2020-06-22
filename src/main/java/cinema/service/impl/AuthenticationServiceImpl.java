package cinema.service.impl;

import cinema.exception.AuthenticationException;
import cinema.model.Role;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Set;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService,
                                     PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

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
        Role userRole = roleService.getRoleByName("USER");
        user.setRoles(Set.of(userRole));
        user = userService.add(user);
        return user;
    }
}
