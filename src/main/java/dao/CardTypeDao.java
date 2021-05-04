package dao;

import data.CardType;

import java.util.Collection;

public interface CardTypeDao {


    int getByType(int id);

    int getBySubType(int id);

    Collection<CardType> get();

    CardType getByCardType(int id);
    int getCardDamage(int id);
    int getCardHp(int id);

}
