package dao;

import data.Card;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CardDaoImpl implements CardDao{
    @Override
    public Card getById(int id) {
        Session s = HibernateUtil.getSession();
        Card out =(Card) s.createQuery(String.format("FROM Card WHERE id='%d'", id)).uniqueResult();
        s.close();
        return out;
    }

    @Override
    public Collection<Card> get() {
        Session s = HibernateUtil.getSession();
        Collection<Card> out = s.createQuery("FROM Card").list();
        s.close();
        return out;

    }

}
