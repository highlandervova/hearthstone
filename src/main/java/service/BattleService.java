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

        if (cardOneId == -3) {
            subTypeCase = 100;
        } //Hero cures ownMinions
        if (cardOneId == -1) {  //Hero - main!
            switch (cardTargetId) {
                case -2:
                    subTypeCase = 101; //Hero attacks force targetHero
                    break;
                case -1:
                    subTypeCase = 102; //Hero cures yourself;
                    break;
                default:
                    subTypeCase = 105; // Hero attacks force targetMinion
            }

        } else {
            switch (cardTargetId) {  //  cardOneId (Number >-1)(Minion) is main!
                case -1:
                    subTypeCase = 103;//ownMinion cures ownHero
                    break;
                case -2:
                    subTypeCase = 104; //minion attacks force to targetHero
                    break;
            }

            if (subTypeCase == 0) { // if no spec type
            if (cardOneId > -1) subTypeCase = cardTypeService.getBySubTypeCard(cardOneId);  // ability target
                 }
        }




        /*
       case perform:
       subtype :
        0 - false for minion and hero
          1: minion attack target only force ;
          2: minion attack target+ near minions;
          3: minion attacks all targetMinions ;
          4: minion attacks target force target + draw card in hand (if hand-empty);
          5:  minion attack target + ownMinion in table +1Hp +1Attack;
          8: minion cures ownMinion;
          9: minion cures all ownMinions;

           handFromTypeofAttack:
          100: Hero cures ownMinions
          101: Hero attacks force targetHero
          102: Hero cures yourself;
          103: ownMinion cures ownHero
          104: minion attacks force to targetHero
          105: Hero attacks force targetMinion

         */

        int minionsAttack = 0;
        int minionsHp = 0;
        int targetMinionAttack = 0;
        int targetMinionHp = 0;
        int heroHp = 0;
        int heroAttack = 0;
        int targetHeroHp = 0;
        int targetHeroAttack = 0;


        switch (subTypeCase) {
            case 0:
                break;  // nothing
            case 1:
                if (whoTurn == 1) {
                    minionsAttack = minionAttack(cardOneId, battleId, 1);
                    minionsHp = minionHp(cardOneId, battleId, 1);
                    targetMinionAttack = minionAttack(cardTargetId, battleId, 2);
                    targetMinionHp = minionHp(cardTargetId, battleId, 2);

                } else {
                    minionsAttack = minionAttack(cardOneId, battleId, 2);
                    minionsHp = minionHp(cardOneId, battleId, 2);
                    targetMinionAttack = minionAttack(cardTargetId, battleId, 1);
                    targetMinionHp = minionHp(cardTargetId, battleId, 1);
                }

                targetMinionHp = targetMinionHp - minionsAttack;
                minionsHp = minionsHp - targetMinionAttack;

                if (whoTurn == 1) {
                    settingMinionHp(minionsHp, cardOneId, battleId, 1);
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 2);
                } else {
                    settingMinionHp(minionsHp, cardOneId, battleId, 2);
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                }
                break;
            case 101: // Hero attacks to targetHero
                if (whoTurn == 1) {
                    heroHp = heroesHp(battleId, 1);
                    heroAttack = heroesAttack(battleId, 1);
                    targetHeroHp = heroesHp(battleId, 2);
                    targetHeroAttack = heroesAttack(battleId, 2);

                } else {
                    heroHp = heroesHp(battleId, 2);
                    heroAttack = heroesAttack(battleId, 2);
                    targetHeroHp = heroesHp(battleId, 1);
                    targetHeroAttack = heroesAttack(battleId, 1);
                }
                heroHp = heroHp - targetHeroAttack;
                targetHeroHp = targetHeroHp - heroAttack;

                if (whoTurn == 1) {
                    setHeroHp(heroHp, battleId, 1);
                    setHeroHp(targetHeroHp, battleId, 2);
                    setActiveFalseHero(battleId, 1);

                } else {
                    setHeroHp(heroHp, battleId, 2);
                    setHeroHp(targetHeroHp, battleId, 1);
                    setActiveFalseHero(battleId, 2);

                }


                break;
            case 104: //  minion attacks force to targetHero, minion not is decrementHp
                if (whoTurn == 1) {
                    minionsAttack = minionAttack(cardOneId, battleId, 1);
                    targetHeroHp = heroesHp(battleId, 2);

                } else {
                    minionsAttack = minionAttack(cardOneId, battleId, 2);
                    targetHeroHp = heroesHp(battleId, 1);
                }

                targetHeroHp = targetHeroHp - minionsAttack;


                if (whoTurn == 1) {  //minion to targetHero
                    //set Hp Hero  HeroHp-attack, minion , minion is false
                    setHeroHp(targetHeroHp, battleId, 2);
                    setActiveMinions(cardOneId, battleId, 1);
                } else {
                    setHeroHp(targetHeroHp, battleId, 1);
                    setActiveMinions(cardOneId, battleId, 2);
                }

                break;

            case 105: // Hero attacks force targetMinion
                if (whoTurn == 1) {
                    targetMinionAttack = minionAttack(cardTargetId, battleId, 2); //card
                    targetMinionHp = minionHp(cardTargetId, battleId, 2);//card
                    heroHp = heroesHp(battleId, 1);
                    heroAttack = heroesAttack(battleId, 1);
                } else {
                    targetMinionAttack = minionAttack(cardTargetId, battleId, 1);
                    targetMinionHp = minionHp(cardTargetId, battleId, 1);//card
                    heroHp = heroesHp(battleId, 2);
                    heroAttack = heroesAttack(battleId, 2);
                }

                heroHp = heroHp - targetMinionAttack;
                targetMinionHp = targetMinionHp - heroAttack;
                if (whoTurn == 1) {
                    setHeroHp(heroHp, battleId, 1);
                    setActiveFalseHero(battleId, 1);
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 2);
                } else {
                    setHeroHp(heroHp, battleId, 2);
                    setActiveFalseHero(battleId, 2);
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                }
                break;
        }

    }

    private int heroesHp(String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        int hpHero = 0;
        if (numberOfHero == 1) {
            hpHero = battle.getHpHero1();
        } else {
            hpHero = battle.getHpHero2();
        }
        return hpHero;
    }

    private int heroesAttack(String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        int attackHero = 0;
        if (numberOfHero == 1) {
            attackHero = battle.getAttackHero1();
        } else {
            attackHero = battle.getAttackHero2();
        }
        return attackHero;
    }


    private void setHeroHp(int heroHp, String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        if (heroHp > 0) {

            if (numberOfHero == 1) {

                battle.setHpHero1(heroHp);
            } else {
                battle.setHpHero2(heroHp);
            }

        } else {
            // perfom EndOfGames
        }

    }

    private void setActiveMinions(int cardId, String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }

        if (array.size() > 0) {
            for (CardType ct : array) {
                if (ct.getId() == cardId) ct.setActive(false);
            }
        }

        if (numberOfHero == 1) {
            battle.setTableCollectionHero1(array);
        } else {
            battle.setTableCollectionHero2(array);
        }

    }


    private void setActiveFalseHero(String battleId, int numberOfHero) {

        Battle battle = usWaitBattService.getBattleId(battleId);


        if (numberOfHero == 1) {

            battle.setActiveHero1(false);
        } else {
            battle.setActiveHero2(false);
        }


    }

    private void settingHeroHp(int cardId, String battleId, int numberOfAttackHero, int hpTargetHero) { //hp+minion false
        Battle battle = usWaitBattService.getBattleId(battleId);
        if (hpTargetHero > 0) {
            ArrayList<CardType> array = new ArrayList<>();
            if (numberOfAttackHero == 1) {
                array = battle.getTableCollectionHero1();
                battle.setHpHero2(hpTargetHero);
            } else {
                array = battle.getTableCollectionHero2();
                battle.setHpHero1(hpTargetHero);
            }

            if (array.size() > 0) {
                for (CardType ct : array) {
                    if (ct.getId() == cardId) ct.setActive(false);
                }
            }

            if (numberOfAttackHero == 1) {
                battle.setTableCollectionHero1(array);
            } else {
                battle.setTableCollectionHero2(array);
            }

        } else {
            // perfom EndOfGames
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
        Iterator<CardType> cardTypeIterator = array.iterator();
        while (cardTypeIterator.hasNext()) {//до тех пор, пока в списке есть элементы
            CardType nextCardType = cardTypeIterator.next();
            if (nextCardType.getId() == cardId) {
                //  nextCardType.setActive(false); //only 1 time for turn
                if (minionHp < 1) cardTypeIterator.remove();//удаляем
            }
            if (minionHp > 0) {
                for (CardType ct : array) {
                    if (ct.getId() == cardId) {
                        ct.setHp(minionHp);
                    }
                }
            }

            if (numberOfHero == 1) {
                battle.setTableCollectionHero1(array);
            } else {
                battle.setTableCollectionHero2(array);
            }

        }


    }


}


