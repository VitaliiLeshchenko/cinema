package cinema.service;

import cinema.model.User;

public interface UserService extends GenericService<User> {
    User add(User user);

    User findByEmail(String email);
}
