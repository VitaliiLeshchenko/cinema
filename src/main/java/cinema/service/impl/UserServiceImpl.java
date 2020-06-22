package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.service.ShoppingCartService;
import cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private ShoppingCartService shoppingCartService;

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
