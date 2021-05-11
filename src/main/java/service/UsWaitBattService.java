package service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import data.Battle;
import data.Card;
import data.CardType;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsWaitBattService {

    private UserService userService;
    private UserOnlineService userOnlineService;


    @Autowired
    public UsWaitBattService(
            //final BattleService battleService,
            final UserService userService,
            UserOnlineService userOnlineService
    ) {
        // this.battleService = battleService;
        this.userService = userService;
        this.userOnlineService = userOnlineService;
    }


    private final static Map<String, User> waitBattle = new HashMap<>();

    private static List<Battle> usBattle = new ArrayList<>();

    public boolean findUser(String id) {
        for (Map.Entry<String, User> item : waitBattle.entrySet()) {
            if (item.getKey().equals(id)) return false;
        }
        return true;
    }

    public User getUserFromMapAsPlayer2() {
        User user;
        Set<String> keys = waitBattle.keySet();

        for (String id : keys) {
            user = waitBattle.get(id);
            return user;
        }
        return null;
    }


    public void removeWaitBattle(String id, User user) {
       waitBattle.size();
        waitBattle.remove(id,user);
        waitBattle.size();
    }



    public boolean waitBattleEmpty() {
        waitBattle.size();
        return waitBattle.isEmpty();
    }

    public void addWaitBattle(String id, User user) {
        waitBattle.put(id, user);
    }


    public void removeWaitBattle(String id) {
        waitBattle.remove(id);
    }


    public String addPrepareForBattle(User user1, User user2) {
        String idBattle = UUID.randomUUID().toString();
        Battle battle = new Battle(idBattle,
                user1.getId(), user1.getLogin(), user1.getName(), user1.getRaceid(),
                1, 1, 30, 2, 2, //2 ->30
                user1.getPoints(), user1.getMoney(), user1.getGold(), null, user1.getDeck(), null,
                user2.getId(), user2.getLogin(), user2.getName(), user2.getRaceid(),
                1, 1, 30, 2, 2, //2->30
                user2.getPoints(), user2.getMoney(), user2.getGold(), null, user2.getDeck(), null);
        battle.setDeckCollectionHero1(createDeckHeroBattle(user1.getDeck()));
        battle.setDeckCollectionHero2(createDeckHeroBattle(user2.getDeck()));
        battle.setHandCollectionHero1(new ArrayList<Integer>());
        battle.setHandCollectionHero2(new ArrayList<Integer>());
        battle.setTableCollectionHero1(new ArrayList<CardType>());
        battle.setTableCollectionHero2(new ArrayList<CardType>());
        battle.setFalseActiveHero1(false);
        battle.setFalseActiveHero2(false);
        battle.setWin(0);
        usBattle.add(battle);
        removeWaitBattle(user1.getId());
        removeWaitBattle(user2.getId());

        return idBattle;
    }


    public void removeUsBattle(String id) {
        Battle b = null;
        for (Battle battle : usBattle) {
            if ((battle.getIdBattle()).equals(id)) {
                b = battle;
            }
        }
        usBattle.remove(b);
    }

    public List<Integer> createDeckHeroBattle(String deck) {
        JsonObject jobj = new Gson().fromJson(deck, JsonObject.class);
        String result = jobj.get("id").toString();
//            System.out.println(result2);
        result = result.substring(1, result.length() - 1);
//            System.out.println(result2);
        int[] numArr = Arrays.stream(result.split(",")).mapToInt(Integer::parseInt).toArray();
        List<Integer> res = Arrays.asList(Arrays.stream(numArr).boxed().toArray(Integer[]::new));
        Collections.shuffle(res);
        List<Integer> usHeroDeck = new ArrayList<>();
        for (Integer in : res) {
            usHeroDeck.add(in);
        }
        return usHeroDeck;
    }


    public Battle getBattleId(String id) {

        Battle b = null;
        for (Battle battle : usBattle) {
            if ((battle.getIdBattle()).equals(id)) {
                b = battle;
            }
        }
        return b;
    }

    public String getBattleIdForUser(String idUser) {

        String idB = null;
        for (Battle battle : usBattle) {
            if ((battle.getIdUserHero1()).equals(idUser) || (battle.getIdUserHero2()).equals(idUser)) {
                idB = battle.getIdBattle();
            }
        }
        return idB;
    }


}


