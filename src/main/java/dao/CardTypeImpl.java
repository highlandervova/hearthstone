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

    public int getBySubType(int id) {
        Session s = HibernateUtil.getSession();
        CardType out = (CardType) s.createQuery(String.format("FROM CardType WHERE id='%d'", id)).uniqueResult();
        int cardSubType = out.getSubtype();
        s.close();
        return cardSubType;
    }

//    @Override
//    public boolean getActiveCard(int id) {
//        Session s = HibernateUtil.getSession();
//        CardType out = (CardType) s.createQuery(String.format("FROM CardType WHERE id='%d'", id)).uniqueResult();
//        boolean active = out.isActive();
//        s.close();
//        return active;
//    }


    @Override
    public Collection<CardType> get() { //

        Session s = HibernateUtil.getSession();
        Collection<CardType> out = s.createQuery("FROM CardType").list();
        s.close();
        return out;


    }

    @Override
    public CardType getByCardType(int id) {
        Session s = HibernateUtil.getSession();
        CardType out = (CardType) s.createQuery(String.format("FROM CardType WHERE id='%d'", id)).uniqueResult();
        s.close();
        return out;
    }
}
