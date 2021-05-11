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


    private static Map<String, ArrayList> mainDeck = new HashMap<>();   // Set<Card> usMainDeck;// = new LinkedHashSet<>();
    private static Map<String, ArrayList> ownDeck = new HashMap<>();//idUser ???
    private Map<String, ArrayList> userDeck = new HashMap<>();  // from user.getDeck


    public int getUsOwnDeckCount(String userId) {

        ArrayList usOwnDeck = new ArrayList();
        Set <String> keys = ownDeck.keySet();

        for (String id : keys) {
            if (id == userId)
                usOwnDeck = ownDeck.get(id);

        }
       return usOwnDeck.size();
          }


    public void createUsMainDeck(String userId) {
        Collection<Card> out = cardService.get();
        ArrayList in = new ArrayList();
        for (Card card : out) {
            if (card.getId() != 0 && card.getId() != -1 && card.getId() != -2 && card.getId() != 21) {  // remove from deck shirt &mana& shirtDeck&boar
                in.add(card);
            }
            mainDeck.put(userId, in);
        }


    }

    public ArrayList getUsMainDeck(String userId) {
        ArrayList usMainDeck = new ArrayList();
        Set <String> keys = mainDeck.keySet();

        for (String id : keys) {
            if (id == userId)
            usMainDeck = mainDeck.get(id);

        }

        return usMainDeck;
    }



    public ArrayList getUsOwnDeck(String userId) {
        ArrayList usOwnDeck = new ArrayList();

        Set <String> keys = ownDeck.keySet();

        for (String id : keys) {
            if (id == userId)
                usOwnDeck = ownDeck.get(id);

        }
        return usOwnDeck;
    }



    public void createOwnDeckFromUserDeck(User user) {
        if (user.getDeck() != null) {
            ArrayList inOwn = new ArrayList();
            ArrayList inMain = getUsMainDeck(user.getId());

            List<Integer> usDeck = usWaitBattService.createDeckHeroBattle(user.getDeck());
            if (usDeck.size() > 0) {
                Collection<Card> out = cardService.get();
                for (int i = 0; i < 10; i++) {
                    for (Card card : out) {
                        if (usDeck.get(i) == card.getId()) {
                            inOwn.add(card);
                            inMain.remove(card);
                        }
                    }
                }

            }

            ownDeck.put(user.getId(), inOwn);
            mainDeck.put(user.getId(), inMain);
        }
    }


    public static void  removeListDeck(String userId) {
        ownDeck.remove(userId);
        mainDeck.remove(userId);
    }


    public void addForUsMainDeck(Card card, String userId) {
        ArrayList inOwn = getUsOwnDeck(userId);
        ArrayList inMain = getUsMainDeck(userId);
        inOwn.remove(card);
        inMain.add(card);
        ownDeck.put(userId, inOwn);
        mainDeck.put(userId, inMain);
    }

    public void addForUsOwnDeck(Card card, String userId) {
        ArrayList inOwn = getUsOwnDeck(userId);
        ArrayList inMain = getUsMainDeck(userId);
        if (inOwn.size()<10){
        inOwn.add(card);
        inMain.remove(card);
        }
        ownDeck.put(userId, inOwn);
        mainDeck.put(userId, inMain);
    }


    public void setUsOwnDeck(User user) {
        String s = "{'id':'";
        int i = 0;
        Collection<Card> in = getUsOwnDeck(user.getId());
        if (getUsOwnDeckCount(user.getId()) == 10) {
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
