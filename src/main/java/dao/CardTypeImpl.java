package dao;

import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CardTypeImpl implements CardType{
    @Override
    public CardType getById(int id) {
       Session s = HibernateUtil.getSession();
        CardType out =(CardType) s.createQuery(String.format("FROM CardType WHERE id='%d'", id)).uniqueResult();
        s.close();
        return out;
    }

    @Override
    public Collection<CardType> get() {

        Session s = HibernateUtil.getSession();
        Collection<CardType> out = s.createQuery("FROM Card").list();
        s.close();
        return out;


    }
}
