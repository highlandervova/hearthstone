package service;


import dao.CardTypeDao;
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



}