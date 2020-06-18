package cinema.controllers;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.AuthenticationService;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping
    public String injectData() {

        User userUser = new User();
        userUser.setPassword("password");
        userUser.setEmail("User@email.com");
        Role roleUser = roleService.add(Role.of("USER"));
        userUser.setRoles(Collections.singleton(roleUser));
        userService.add(userUser);
        User userAdmin = new User();
        userAdmin.setEmail("Admin@email.com");
        userAdmin.setPassword("password");
        Role roleAdmin = roleService.add(Role.of("ADMIN"));
        userAdmin.setRoles(Collections.singleton(roleAdmin));
        userService.add(userAdmin);
        return "Done";
    }
}
