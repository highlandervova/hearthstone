package service;


import dao.CardTypeDao;
import data.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Service
public class CardTypeService {


    private UserService userService;
    private UserOnlineService userOnlineService;
    private CardTypeDao cardTypeDao;

    @Autowired
    public CardTypeService(
            final CardTypeDao cardTypeDao,
            final UserService userService,
            UserOnlineService userOnlineService
    ) {
        this.cardTypeDao = cardTypeDao;
        this.userService = userService;
        this.userOnlineService = userOnlineService;
    }

    public int getByTypeCard(int cardId) {
        int cardType = cardTypeDao.getByType(cardId);
        return cardType;
    }


    public int getBySubTypeCard(int cardId) {
        int cardSubType = cardTypeDao.getBySubType(cardId);
        return cardSubType;
    }

    public int getByCardDamage(int cardId){
        return cardTypeDao.getCardDamage(cardId);
    }


    public CardType getByCardType (int cardId) {
        CardType ct = cardTypeDao.getByCardType(cardId);
        return  ct;
    }



}