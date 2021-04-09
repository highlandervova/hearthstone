package dao;

import data.Race;
import hibernate.HibernateUtil;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RaceDaoImpl  implements RaceDao{

    @Override
    public Collection<Race> get() {
        Session s = HibernateUtil.getSession();
        Collection<Race> out = s.createQuery("FROM Race").list();
        s.close();
        return out;

    }

    @Override
    public Race getById(int id) {
        Session s = HibernateUtil.getSession();
               Race out =(Race) s.createQuery(String.format("FROM Race WHERE id='%d'", id)).uniqueResult();
        s.close();
        return out;

    }


}
