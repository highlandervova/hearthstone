package dao;

import data.User;

import java.util.Collection;




public interface UserDao {


    Collection<User> get();
    void edit(User user);
    void remove(User user);
    User getByLogin(String login) ;
    boolean add(User u);
    User getById(String id);
    void updateUser(User u );
     String getByLoginString(String login) ;
    void updateUserPassword(String login, String pass); //updates all fields (login,pass,city,phone,email) except ads


}
