package dao;

import cinema.dao.UserDao;
import cinema.exception.DataProcessingException;
import cinema.model.User;
import config.AppConfigTest2;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoTest {

    private static final AnnotationConfigApplicationContext CONTEXT
            = new AnnotationConfigApplicationContext(AppConfigTest2.class);
    private static final UserDao USER_DAO = CONTEXT.getBean(UserDao.class);

    @Test
    public void getUserDaoImplFromContext(){
        CONTEXT.getBean(UserDao.class);
    }

    @Test
    public void addAndFindUser(){
        User userAdded = new User("Email", "Password");
        userAdded = USER_DAO.add(userAdded);
        User userFromDB = USER_DAO.findByEmail("Email");
        Assert.assertEquals(userAdded, userFromDB);
    }

    @Test(expected = DataProcessingException.class)
    public void addSameUser() {
        User user1 = new User("123", "123");
        User user2 = new User("123", "123");
        USER_DAO.add(user1);
        USER_DAO.add(user2);
    }

    @Test
    public void adEmptyUser() {
        User user = new User();
        USER_DAO.add(user);
    }
}
