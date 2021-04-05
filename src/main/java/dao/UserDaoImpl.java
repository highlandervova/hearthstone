package dao;

import data.User;
import hibernate.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class UserDaoImpl implements UserDao{


 @Override
    public Collection<User> get() {
            Session s = HibernateUtil.getSession();
            Collection<User> out = s.createQuery("FROM User").list();
            s.close();
            return out;
        }


    @Override
    public void edit(User user) {
            Session s = HibernateUtil.getSession();
            s.beginTransaction();
            s.update(user);
            s.getTransaction().commit();
            s.close();

    }

    @Override
    public void remove(User u) {
        Session s = HibernateUtil.getSession();
        s.beginTransaction();
        s.delete(u);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public User getByLogin(String login) {
        Session s = HibernateUtil.getSession();
        User u = (User) s.createQuery("FROM User WHERE login=\'" + login + "\'").uniqueResult();
        s.close();
        return u;
    }


    @Override
    public boolean add(User u) {
        Session s = HibernateUtil.getSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
        return true;
    }

    @Override
    public void updateUser(User u){
        Session s = HibernateUtil.getSession();
        s.beginTransaction();
        s.update(u);
        s.getTransaction().commit();
        s.close();
    }

    @Override
    public void updateUserPassword(String login, String pass) {
            User u = getByLogin(login);
            Session s = HibernateUtil.getSession();
            s.beginTransaction();
            u.setPass(pass);
            s.update(u);
            s.getTransaction().commit();
            s.close();
    }


}
