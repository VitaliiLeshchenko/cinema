package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final PasswordEncoder encoder;
    private final ShoppingCartService shoppingCartService;

    public UserServiceImpl(UserDao userDao, PasswordEncoder encoder,
                           ShoppingCartService shoppingCartService) {
        this.userDao = userDao;
        this.encoder = encoder;
        this.shoppingCartService = shoppingCartService;
    }

    @Override
    public User add(User user) {
        String password = encoder.encode(user.getPassword());
        user.setPassword(password);
        user = userDao.add(user);
        shoppingCartService.registerNewShoppingCart(user);
        return user;
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }

    @Override
    public User getById(Long id) {
        return userDao.getById(id);
    }
}
