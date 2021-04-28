package service;

import data.Battle;

import data.CardType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BattleService {


    private UserService userService;
    private UserOnlineService userOnlineService;
    private UsWaitBattService usWaitBattService;
    private CardTypeService cardTypeService;
//    private final List<Integer> deckHeroForBattle = new ArrayList<>();
//    private final List<Integer> handHeroForBattle = new ArrayList<>();
//    private final List<Integer> tableHeroForBattle = new ArrayList<>();


    @Autowired
    public BattleService(
            final UsWaitBattService usWaitBattService,
            final UserService userService,
            UserOnlineService userOnlineService,
            CardTypeService cardTypeService
    ) {
        this.usWaitBattService = usWaitBattService;
        this.userService = userService;
        this.userOnlineService = userOnlineService;
        this.cardTypeService = cardTypeService;
    }


    public int whoTurnFirst() {
        int i = (int) (Math.floor(2 * Math.random()) + 1);
        return i;
    }


    public int prepeareTable(int whoTurn, String idBattle) {
        int i = 0;
        Battle b = usWaitBattService.getBattleId(idBattle);
        b.setTableCollectionHero1(cleanTable(b.getTableCollectionHero1()));
        b.setTableCollectionHero2(cleanTable(b.getTableCollectionHero2()));
        if (b.getHpHero1() < 1) {
            i = 1; //endOfGame Win Hero1
        }
        if (b.getHpHero2() < 1) {
            i = 2; //endOfGame Win Hero2
        }
        if (whoTurn == 1) {
            b.setTableCollectionHero1(activeMinions(b.getTableCollectionHero1()));
        } else {
            b.setTableCollectionHero2(activeMinions(b.getTableCollectionHero2()));
        }
        return i;
    }


    private ArrayList<CardType> cleanTable(ArrayList<CardType> arrayTable) { //cleanMinions
        Iterator<CardType> cardTypeIterator = arrayTable.iterator();
        while (cardTypeIterator.hasNext()) {//до тех пор, пока в списке есть элементы
            CardType nextCardType = cardTypeIterator.next();
            if (nextCardType.getHp() < 1) {
                cardTypeIterator.remove();//удаляем
            }
        }
        return arrayTable;
    }


    private ArrayList<CardType> activeMinions(ArrayList<CardType> arrayTable) { //cleanMinions
        if (arrayTable.size() > 0) {
            for (CardType ct : arrayTable) {
                ct.setActive(true);
            }
        }
        return arrayTable;
    }

    public boolean getByActiveCard(String idBattle, int cardId, int whoTurn) {
        Battle b = usWaitBattService.getBattleId(idBattle);
        ArrayList<CardType> array = new ArrayList<>();
        boolean activeCard = false;

        if (whoTurn == 1) {
            array = b.getTableCollectionHero1();
        } else {
            array = b.getTableCollectionHero2();
        }
        for (CardType ct : array) {
            if (ct.getId() == cardId) activeCard = ct.isActive();
        }
        return activeCard;
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

    public void fromHandToTable(List<Integer> handHeroForBattle, List<CardType> tableHeroForBattle, int idCard, String idUser, int heroN) {
        int i = 0;
        int b = -1;
        for (int id : handHeroForBattle) {
            if (id == idCard) {
                b = i;
            }
            i++;
        }

        if (b > -1) { // idCard
            Battle battle = usWaitBattService.getBattleId(usWaitBattService.getBattleIdForUser(idUser));
            if (tableHeroForBattle.size() < 10) {
                CardType ct = cardTypeService.getByCardType(idCard);
                ct.setActive(false);

                tableHeroForBattle.add((CardType) ct);

                if (heroN == 1) {
                    battle.setTableCollectionHero1((ArrayList<CardType>) tableHeroForBattle);
                } else {
                    battle.setTableCollectionHero2((ArrayList<CardType>) tableHeroForBattle);
                }


            }
        }


        if (handHeroForBattle.size() > 0) {
            handHeroForBattle.remove(b);
        }
    }


    public void perfom(int cardOneId, int cardTargetId, int whoTurn, String battleId) {
        int subTypeCase = 0;
        if (cardOneId == -1) {
            subTypeCase = 100; // Hero attack force minion
            if (cardTargetId == -2) {
                subTypeCase = 101;
            } // Hero attack force Hero
            if (cardTargetId == -1) {
                subTypeCase = 102;
            } // Hero cures yourself
        } else {
            if (cardTargetId == -1) {
                subTypeCase = 103;
            } //minion cures Hero
            if (cardTargetId == -2) {
                subTypeCase = 104;
            }  // minion attacks force to Hero

            if (cardOneId > -1) subTypeCase = cardTypeService.getBySubTypeCard(cardOneId);
        }
        //false for minion and hero

        int minionAttack = 0;
        int minionHp = 0;
        int targetMinionAttack = 0;
        int targetMinionHp = 0;


        switch (subTypeCase) {
            case 0:
                break;  // nothing
            case 1:

                if (whoTurn == 1) {
                    minionAttack = minionAttack(cardOneId, battleId, 1);
                    minionHp = minionHp(cardOneId, battleId, 1);
                    targetMinionAttack = minionAttack(cardTargetId, battleId, 2);
                    targetMinionHp = minionHp(cardTargetId, battleId, 2);

                } else {
                    minionAttack = minionAttack(cardOneId, battleId, 2);
                    minionHp = minionHp(cardOneId, battleId, 2);
                    targetMinionAttack = minionAttack(cardTargetId, battleId, 1);
                    targetMinionHp = minionHp(cardTargetId, battleId, 1);
                }

                targetMinionHp = targetMinionHp - minionAttack;
                minionHp = minionHp - targetMinionAttack;

                if (whoTurn == 1) {
                    settingMinionHp( minionHp, cardOneId, battleId, 1);
                    settingMinionHp(targetMinionHp,cardTargetId, battleId, 2);
                } else {
                    settingMinionHp( minionHp, cardOneId, battleId, 2);
                    settingMinionHp(targetMinionHp,cardTargetId, battleId, 1);
                }


        }

    }


    private int minionHp(int cardId, String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        int hp = 0;
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }

        if (array.size() > 0) {
            for (CardType ct : array) {
                if (ct.getId() == cardId) hp = ct.getHp();
            }
        }
        return hp;
    }

    private int minionAttack(int cardId, String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        int attack = 0;
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }

        if (array.size() > 0) {
            for (CardType ct : array) {
                if (ct.getId() == cardId) attack = ct.getDamage();
            }
        }
        return attack;
    }


    private void settingMinionHp(int minionHp, int cardId, String battleId, int numberOfHero) { //hp<0 delete minion, hp>0 setHp minion
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }
        if (minionHp < 1) {

            Iterator<CardType> cardTypeIterator = array.iterator();
            while (cardTypeIterator.hasNext()) {//до тех пор, пока в списке есть элементы
                CardType nextCardType = cardTypeIterator.next();
                if (nextCardType.getId() == cardId) {
                    cardTypeIterator.remove();//удаляем
                }
            }

            if (numberOfHero == 1) {
                battle.setTableCollectionHero1(array);
            } else {
                battle.setTableCollectionHero2(array);
            }

        }

        if (minionHp > 0) {
            int i = 0;
            for (CardType ct : array) {
                if (ct.getId() == cardId) {
                    ct.setHp(minionHp);
                }
                i++;
            }
        }
    }


}


