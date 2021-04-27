package dao;



import data.Card;

import java.util.Collection;

public interface CardDao {


        Card getById(int id);
        Collection<Card> get();
        int getMana(int id);



}
