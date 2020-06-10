package dao;

import cinema.dao.UserDao;
import cinema.exception.DataProcessingException;
import cinema.model.User;
import config.AppConfigTest2;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {

    static AnnotationConfigApplicationContext context
            = new AnnotationConfigApplicationContext(AppConfigTest2.class);
    @Test
    public void getUserDaoImplFromContext(){
        context.getBean(UserDao.class);
    }

    @Test
    public void addAndFindUser(){
        UserDao userDao = context.getBean(UserDao.class);
        User userAdded = new User("Email", "Password");
        userAdded = userDao.add(userAdded);
        User userFromDB = userDao.findByEmail("Email");
        Assert.assertEquals(userAdded, userFromDB);
    }

    @Test(expected = DataProcessingException.class)
    public void expectedEx() {
        User user1 = new User("123", "123");
        User user2 = new User("123", "123");
        UserDao userDao = context.getBean(UserDao.class);
        userDao.add(user1);
        userDao.add(user2);
    }
}
