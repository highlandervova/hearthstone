package service;

import dao.CardDao;
import dao.UserDao;
import data.Card;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;


@Service
public class CardService {


    private UserService userService;
    private UserOnlineService userOnlineService;
    private CardDao cardDao;

    @Autowired
    public CardService(
            final CardDao cardDao,
            final UserService userService,
            UserOnlineService userOnlineService
    ) {
        this.cardDao = cardDao;
        this.userService = userService;
        this.userOnlineService = userOnlineService;
    }

    public Collection<Card> get() {
        Collection<Card> out = cardDao.get();
        return out;
    }

    public Card getById(int cardId) {
        Card c = cardDao.getById(cardId);
        return c;
    }


}