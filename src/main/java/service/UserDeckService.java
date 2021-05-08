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
    private UsWaitBattService usWaitBattService;

    @Autowired
    public UserDeckService(
            final UserService userService,
            final CardService cardService,
            final UsWaitBattService usWaitBattService) {

        this.userService = userService;
        this.cardService = cardService;
        this.usWaitBattService = usWaitBattService;
    }


    private final Set<Card> usMainDeck = new LinkedHashSet<>();
    private Set<Card> usOwnDeck = new LinkedHashSet<>(); //idUser ???
    private Set<Card> userDeck = new LinkedHashSet<>();  // from user.getDeck

    public int getUsMainDeckCount() {
        return usMainDeck.size();
    }

    public int getUsOwnDeckCount() {
        return usOwnDeck.size();
    }

    public int getUserDeckCount() {
        return userDeck.size();
    }

    public Set<Card> createUsMainDeck() {
        Collection<Card> out = cardService.get();
        for (Card card : out) {
            if (card.getId() != 0 && card.getId() != -1 && card.getId() != -2 && card.getId() != 21) {  // remove from deck shirt &mana& shirtDeck&boar
                usMainDeck.add(card);
            }
        }
        return usMainDeck;
    }

    public Set<Card> getUsMainDeck() {
        return usMainDeck;
    }

    public Set<Card> getUsOwnDeck() {
        return usOwnDeck;
    }

    public Set<Card> getUsDeck(User user) {
        if (user.getDeck() !=null) {
            userDeck.clear();
            List<Integer> usDeck = usWaitBattService.createDeckHeroBattle(user.getDeck());
            if (usDeck.size() > 0) {
                Collection<Card> out = cardService.get();
                for (int i = 0; i < 10; i++) {
                    for (Card card : out) {
                        if (usDeck.get(i) == card.getId())
                            userDeck.add(card);
                    }
                }
            }

        }
            return userDeck;


    }



    public void createUsOwnDeckFromUserDeck () {
       for (Card cardUs:userDeck)
           usOwnDeck.add(cardUs);

        for (Card cardUs:usOwnDeck){
            usMainDeck.remove(cardUs);

    }
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
        String s = "{'id':'";
        int i = 0;
        Collection<Card> in = getUsOwnDeck();
        if (getUsOwnDeckCount() == 10) {
            for (Card card : in) {
                if (i < 9) {
                    s = s + card.getId() + ",";
                } else {
                    s = s + card.getId() + "'}";
                }
                i++;
            }

            user.setDeck(s);
            user.setDeckdate(new Date());
            userService.update(user);
        }
    }


}
