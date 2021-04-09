package dao;

import data.Race;

import java.util.Collection;

public interface RaceDao {

        Race getById(int id);
        Collection<Race> get();

    }


