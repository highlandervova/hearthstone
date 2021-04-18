package service;

import data.Battle;
import data.User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsWaitBattService {


    //private static Set<User> usWaitBattle = new LinkedHashSet<>();

    private Map<String, User> waitBattle = new HashMap<>();

    private static List<Battle> usBattle = new ArrayList<>();

    public boolean findUser(String id) {
        for (Map.Entry<String, User> item : waitBattle.entrySet()) {
            if (item.getKey() == id) return false;
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


    public boolean waitBattleEmpty() {
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
                0, 0, 30, 30, 2,
                user1.getPoints(), user1.getMoney(), user1.getGold(), null, user1.getDeck(), null,
                user2.getId(), user2.getLogin(), user2.getName(), user2.getRaceid(),
                0, 0, 30, 30, 2,
                user2.getPoints(), user2.getMoney(), user2.getGold(), null, user2.getDeck(), null);
        usBattle.add(battle);
        removeWaitBattle(user1.getId());
        removeWaitBattle(user2.getId());
        return idBattle;
    }


}


