package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.Battle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BattleService {


    private UserService userService;
    private UserOnlineService userOnlineService;
    private UsWaitBattService usWaitBattService;
//    private final List<Integer> deckHeroForBattle = new ArrayList<>();
//    private final List<Integer> handHeroForBattle = new ArrayList<>();
//    private final List<Integer> tableHeroForBattle = new ArrayList<>();


    @Autowired
    public BattleService(
            final UsWaitBattService usWaitBattService,
            final UserService userService,
            UserOnlineService userOnlineService
    ) {
        this.usWaitBattService = usWaitBattService;
        this.userService = userService;
        this.userOnlineService = userOnlineService;
    }


    public int whoTurnFirst () {
       int i= (int) (Math.floor(2 * Math.random()) + 1);
       return i;
    }

    public int deckHeroForBattleCount(List<Integer> deckHeroForBattle) {
        return deckHeroForBattle.size();
    }

    public List<Integer> getDeckHeroForBattle(List<Integer> deckHeroForBattle) { //?
        return deckHeroForBattle;
    }


    public void deckHeroFromDeckToHand(List<Integer> deckHeroForBattle, List<Integer> handHeroForBattle, String idUser, int heroN) {
        Battle b = usWaitBattService.getBattleId(usWaitBattService.getBattleIdForUser(idUser));


        if (handHeroForBattle.size() < 10) {
            handHeroForBattle.add(deckHeroForBattle.get(0));
            if (heroN == 1) {
                b.setHandCollectionHero1(handHeroForBattle);
            } else {
                b.setHandCollectionHero2(handHeroForBattle);
            }

        }
        if (deckHeroForBattle.size() > 0) {
            deckHeroForBattle.remove(0);
        }
    }

    public void fromHandToTable(List<Integer> handHeroForBattle, List<Integer> tableHeroForBattle, int idCard, String idUser, int heroN) {
        int i = 0;
        int b = -1;
        for (int id : handHeroForBattle) {
            if ( id == idCard){ b = i;}
            i++;
        }

        if (b > -1) {
            Battle battle = usWaitBattService.getBattleId(usWaitBattService.getBattleIdForUser(idUser));
            if (tableHeroForBattle.size() < 10) {
                tableHeroForBattle.add(handHeroForBattle.get(b));

                if (heroN == 1) {
                    battle.setTableCollectionHero1(tableHeroForBattle);
                } else {
                    battle.setTableCollectionHero2(tableHeroForBattle);
                }


            }
        }


        if (handHeroForBattle.size() > 0) {
            handHeroForBattle.remove(b);
        }
    }


}


