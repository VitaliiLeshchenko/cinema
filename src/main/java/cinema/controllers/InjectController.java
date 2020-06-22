package cinema.controllers;

import cinema.model.Role;
import cinema.model.User;
import cinema.service.RoleService;
import cinema.service.UserService;
import java.util.Collections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inject")
public class InjectController {
    private final RoleService roleService;
    private final UserService userService;

    public InjectController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping
    public String injectData() {
        User userAdmin = new User();
        userAdmin.setEmail("Admin@email.com");
        userAdmin.setPassword("password");
        Role roleAdmin = roleService.add(Role.of("ADMIN"));
        userAdmin.setRoles(Collections.singleton(roleAdmin));
        userService.add(userAdmin);
        return "Done, admin was added to DB.\n email = Admin@email.com\n password = password";
    }
}
