package service;

import dao.UserDao;
import data.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private final UserDao userDao;


    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }

    public void addNewUser(User u) {
        u.setId(UUID.randomUUID().toString());
        userDao.add(u);
    }


    public User getByLogin(String login) {
        User u = userDao.getByLogin(login);
        return u;
    }
}