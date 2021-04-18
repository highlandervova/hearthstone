package service;


import data.Card;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDeckService {

    private UserService userService;
    private CardService cardService;

    @Autowired
    public UserDeckService(
            final UserService userService,
            final CardService cardService) {

        this.userService = userService;
        this.cardService = cardService;
    }


    private final Set<Card> usMainDeck = new LinkedHashSet<>();
    private final Set<Card> usOwnDeck = new LinkedHashSet<>();


    public int getUsMainDeckCount() {
        return usMainDeck.size();
    }

    public int getUsOwnDeckCount() {
        return usOwnDeck.size();
    }

    public Set<Card> createUsMainDeck() {
        Collection<Card> out = cardService.get();
        for (Card card : out) usMainDeck.add(card);
        return usMainDeck;
    }

    public Set<Card> getUsMainDeck() {
        usMainDeck.size();
        return usMainDeck;
    }

    public Set<Card> getUsOwnDeck() {
        return usOwnDeck;
    }

    public Set<Card> clearUsOwnDeck() {
        usOwnDeck.clear();
        return usOwnDeck;
    }


    public void addForUsMainDeck(Card card) {
        usMainDeck.add(card);
        usOwnDeck.remove(card);
           }

    public void addForUsOwnDeck(Card card) {
        usOwnDeck.add(card);
        usMainDeck.remove(card);
           }


    public void setUsOwnDeck(User user) {
        String s = "[{";
        int i = 0;
        Collection<Card> in = getUsOwnDeck();
        if (getUsOwnDeckCount() == 10) {
            for (Card card : in) {
                if (i < 9) {
                    s = s + card.getId() + ",";
                } else {
                    s = s + card.getId() + "}]";
                }
                i++;
            }

            user.setDeck(s);
            user.setDeckdate(new Date());
            userService.update(user);
        }
    }


}
