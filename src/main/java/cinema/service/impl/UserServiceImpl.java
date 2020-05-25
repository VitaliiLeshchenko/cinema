package cinema.service.impl;

import cinema.dao.UserDao;
import cinema.model.User;
import cinema.service.Service;
import cinema.service.UserService;
import cinema.util.Inject;

@Service
public class UserServiceImpl implements UserService {
    @Inject
    private UserDao userDao;

    @Override
    public User add(User user) {
        return userDao.add(user);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email);
    }
}
