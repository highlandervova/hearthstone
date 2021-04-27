package dao;

import data.CardType;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CardTypeImpl implements CardTypeDao {

    @Override
    public int getByType(int id) {
        Session s = HibernateUtil.getSession();
        CardType out = (CardType) s.createQuery(String.format("FROM CardType WHERE id='%d'", id)).uniqueResult();
        int cardType = out.getTypecard();
        s.close();
        return cardType;
    }

    @Override
    public Collection<CardTypeDao> get() {

        Session s = HibernateUtil.getSession();
        Collection<CardTypeDao> out = s.createQuery("FROM Card").list();
        s.close();
        return out;


    }
}
