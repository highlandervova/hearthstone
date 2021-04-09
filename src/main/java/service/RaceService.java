package service;

import dao.RaceDao;
import data.Race;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
public class RaceService {



        private final RaceDao raceDao;
        public RaceService(RaceDao raceDao) {
            this.raceDao = raceDao;
        }

    public Collection<Race> getRaces() {
        Collection<Race> out = raceDao.get();
        return out;
    }



    public Race getById(int raceId) {
        Race r = raceDao.getById(raceId);
        return r;
    }

    public Collection<Race> getOtherRaces(int id){
        Collection<Race> out1 = raceDao.get();
        Collection<Race> out2= new ArrayList();
        out2.add(getById(id));
        out1.removeAll(out2);
        return out1;
    }

    }


