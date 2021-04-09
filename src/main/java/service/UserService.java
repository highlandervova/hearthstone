package service;

import dao.UserDao;
import data.User;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
public class UserService {


    @Autowired
    private UserDao userDao;




    public  String md5Apache(String pass){
        String md5Hex = DigestUtils.md5DigestAsHex(pass.getBytes(StandardCharsets.UTF_8));
        return md5Hex;}

    public void addNewUser(String login, String pass, String name,String lastname, int raceId, String email) {
        User u = new User( );
        u.setId(UUID.randomUUID().toString());
        u.setLogin(login);
        u.setPass(md5Apache(pass));
        u.setName(name);
        u.setLastname(lastname);
        u.setRaceid(raceId);
        u.setEmail(email);
        u.setLvl(1);
        u.setPoints(0);
        u.setMoney(0);
        u.setGold(0);
        u.setCreationdate(new Date());
        u.setDeckdate(new Date());
        userDao.add(u);
    }


    public User getByLogin(String login) {
           User u = userDao.getByLogin(login);
        return  u;
    }

    public String getByLoginStr(String login) {
         String   u = userDao.getByLoginString(login);

        return  u;
    }

    public int getByDeck (User u){
        if (u.getDeck() !=null){
            return 1;
        }
        return 0;
    }


    public User getById(String id) {
        User u = userDao.getById(id);
        return u;
    }

    public boolean checkUserPassword(User u, String pass) {
        return u != null && u.getPass().equals(md5Apache(pass));
    }

    public boolean updateUserWithPassword(User user){
        if (user!=null){
            userDao.updateUser(user);
            return true;
        }
        return false;
    }
    public boolean updateUserWithoutPassword(User user){
        if (user!=null){
            userDao.updateUser(user);
            return true;
        }
        return false;
    }


}