package service;

import data.Battle;

import data.CardType;
import data.ListFinalBattle;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.lang.Math.abs;

@Service
public class BattleService {

    private static List<ListFinalBattle> finalBattle = new ArrayList<>();

    private UserService userService;
    private UserOnlineService userOnlineService;
    private UsWaitBattService usWaitBattService;
    private CardTypeService cardTypeService;
    //  final EndOfBattleServiceOld endOfBattleService;


    @Autowired
    public BattleService(
            final UsWaitBattService usWaitBattService,
            final UserService userService,
            UserOnlineService userOnlineService,
            CardTypeService cardTypeService
            //    EndOfBattleServiceOld endOfBattleService
    ) {
        this.usWaitBattService = usWaitBattService;
        this.userService = userService;
        this.userOnlineService = userOnlineService;
        this.cardTypeService = cardTypeService;
        // this.endOfBattleService = endOfBattleService;
    }


    public void addListFinalBattle(
            String idBattle,
            String idUserHero1,
            String loginHero1,
            String nameHero1,
            Integer raceidHero1,
            Integer pointsHero1,
            Integer goldHero1,
            String idUserHero2,
            String loginHero2,
            String nameHero2,
            Integer raceIdHero2,
            Integer pointsHero2,
            Integer goldHero2,
            int Win) {
        ListFinalBattle finalList = new ListFinalBattle(
                idBattle,
                idUserHero1,
                loginHero1,
                nameHero1,
                raceidHero1,
                pointsHero1,
                goldHero1,
                idUserHero2,
                loginHero2,
                nameHero2,
                raceIdHero2,
                pointsHero2,
                goldHero2,
                Win);

        finalBattle.add(finalList);

    }

    public ListFinalBattle getIdFinalBattle(String id) {

        ListFinalBattle b = null;
        for (ListFinalBattle listFinal : finalBattle) {
            if ((listFinal.getIdBattle()).equals(id)) {
                b = listFinal;
            }

        }
        return b;
    }

    public int getNumOfHeroBattl(String idBattle, String idUser) {
        int numOfHero = 0;
        ListFinalBattle b = getIdFinalBattle(idBattle);
        for (ListFinalBattle listFinal : finalBattle) {
            if ((listFinal.getIdUserHero1()).equals(idUser)) {
                numOfHero = 1;
            } else {
                numOfHero = 2;
            }
        }
        return numOfHero;
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
            b.setWin(1);
            b.setPointsHero1(abs(b.getHpHero1()));//endOfGame Win Hero1

        }
        if (b.getHpHero2() < 1) {
            b.setWin(2);
            b.setPointsHero2(abs(b.getHpHero2()));//endOfGame Win Hero2
        }
        if (whoTurn == 1) {
            //b.setTableCollectionHero1(activeMinions(b.getTableCollectionHero1()));
            setActiveMinionsForReturn(idBattle, 1);
        } else {
            //  b.setTableCollectionHero2(activeMinions(b.getTableCollectionHero2()));
            setActiveMinionsForReturn(idBattle, 2);
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

    public void deckHeroFromDeckToHand(String battleId, int heroN) {
        Battle b = usWaitBattService.getBattleId(battleId);

        List<Integer> deckHeroForBattle = new ArrayList<>();
        List<Integer> handHeroForBattle = new ArrayList<>();

        if (heroN == 1) {
            deckHeroForBattle = b.getDeckCollectionHero1();
            handHeroForBattle = b.getHandCollectionHero1();
        } else {
            deckHeroForBattle = b.getDeckCollectionHero2();
            handHeroForBattle = b.getHandCollectionHero2();
        }

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
            if (heroN == 1) {
                b.setDeckCollectionHero1(deckHeroForBattle);
            } else {
                b.setDeckCollectionHero2(deckHeroForBattle);
            }
        }
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


    private void removeHandCard(int idCard, String battleId, int numberOfHero) {

        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<Integer> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = (ArrayList<Integer>) battle.getHandCollectionHero1();
        } else {
            array = (ArrayList<Integer>) battle.getHandCollectionHero2();
        }

        Iterator<Integer> integerIterator = array.iterator();
        while (integerIterator.hasNext()) {//до тех пор, пока в списке есть элементы
            Integer nextInteger = integerIterator.next();
            if (nextInteger.intValue() == idCard) {
                //  nextCardType.setActive(false);
                integerIterator.remove();//удаляем
            }
        }
        if (numberOfHero == 1) {
            battle.setHandCollectionHero1(array);
        } else {
            battle.setHandCollectionHero2(array);
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
                ct.setActivate(1);

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


    public int subTypeCaseToPerfom(String fromCard, int cardOneId) {


        /*

       case perform:
       subtype :
        0 - false for minion and hero
          +1: minion attack target only force ;
          2: minion attack target+ near minions;(not yet)
          3: minion attacks all targetMinions ;
          4: minion attacks target force target + draw card in hand (if hand-empty);(not yet)
          5:
          8: minion cures ownMinion; (not yet)
          9: minion cures all ownMinions;(not yet)
          +311: minion add Extra attack(+/-)/add Hp from hand to table
          +312: spell(attack) to target
          +313: spell set Attack, set Hp to target
          +314:  spell(attack) to target + card to hand (if hand is emty)
          +315: spell(attack) to target+ false for next turn
          +316: spell falseTarget next turn
          +317: from hand cardMinion + Extra minion to table
          +318: falseTarget next turn , when Minion attack



           handFromTypeofAttack:
          100: Hero cures ownMinions (not yet)
          +101: Hero attacks force targetHero
          102: Hero cures yourself; (not yet)
          103: ownMinion cures ownHero (not yet)
          +104: minion attacks force to targetHero
          +105: Hero attacks force targetMinion
          +112: spell(attack) to targetHero
          +114:  spell(attack) to targetHero + card to hand (if hand is emty)
          +115: spell(attck) to targetHero + false Hero for next turn

          +117: spellExtra(attack) to targetHero
         */
        int subTypeCase = 0;
        if (fromCard.equals("hand")) {
            switch (cardTypeService.getBySubTypeCard(cardOneId)) {
                case 1:  // to move to the table
                    subTypeCase = 1;
                    break;
                case 311: // to attack /cure  all minions
                    subTypeCase = 311;
                    break;
                case 312: // to wait target
                    subTypeCase = 312;
                    break;
                case 314: // to wait target
                    subTypeCase = 314;
                    break;
                case 315: // to wait target
                    subTypeCase = 315;
                    break;
                case 316: // to wait target
                    subTypeCase = 316;
                    break;
                case 317: // to wait target
                    subTypeCase = 317;
                    break;

            }

        }
        //subTypeCase = cardTypeService.getBySubTypeCard(cardOneId);

        if (fromCard.equals("table")) {
            //   cardOneId = -1 hero or : minion
            //   cardTypeService.getBySubTypeCard(cardOneId) : cure? subcase== 8,9
            int subCase = 10;
            switch (subCase) {
                case 10:  // to move to the table
                    subTypeCase = 10;
                    break;
            }
        }

        if (fromCard.equals("target")) {
            int subCase = 0;
            //Who?
            if (cardOneId == -1) {
                subTypeCase = 105;
            } else {
                if (cardTypeService.getByTypeCard(cardOneId) == 2) //minion

                {
                    int subCaseCard = cardTypeService.getBySubTypeCard(cardOneId);
                    switch (subCaseCard) {
                        case 318:
                            subTypeCase = cardTypeService.getBySubTypeCard(cardOneId);//damage and activate=false next turn
                            break;
                        default:
                            subTypeCase = 1; // for evrething subtype minion
                            break;
                    }

                }
                if (cardTypeService.getByTypeCard(cardOneId) == 1) { //spell
                    subTypeCase = cardTypeService.getBySubTypeCard(cardOneId);
                }
            }

        }


        if (fromCard.equals("tgHero")) {
            int subCase = 0;
            //Who?
            if (cardOneId == -1) {
                subTypeCase = 101; //hero to minion
            } else {
                if (cardTypeService.getByTypeCard(cardOneId) == 2) //minion
                {
                    subTypeCase = 104; //minion to hero
                }
                if (cardTypeService.getByTypeCard(cardOneId) == 1) { //spell
                    int subCaseCard = cardTypeService.getBySubTypeCard(cardOneId);
                    switch (subCaseCard) {
                        case 312:
                            subTypeCase = 112;//spell to targetHero
                            break;
                        case 314:
                            subTypeCase = 114; //spell to targetHero
                            break;
                        case 315:
                            subTypeCase = 115; //spell to targetHero
                            break;
                        case 316:
                            subTypeCase = 116; //spell to targetHero
                            break;
                        default:
                            subTypeCase = cardTypeService.getBySubTypeCard(cardOneId);
                            break;
                    }
                }
            }

        }

        return subTypeCase;
    }


    public void perfom(int cardOneId, int cardTargetId, int whoTurn, String battleId, int subTypeCase) {

        int minionsAttack = 0;
        int minionsHp = 0;
        int targetMinionAttack = 0;
        int targetMinionHp = 0;
        int heroHp = 0;
        int heroAttack = 0;
        int targetHeroHp = 0;
        int targetHeroAttack = 0;
        int spellAttack = 0;

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
                    setActiveMinions(cardOneId, battleId, 1);
                } else {
                    settingMinionHp(minionsHp, cardOneId, battleId, 2);
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                    setActiveMinions(cardOneId, battleId, 2);
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

                if (heroHp < 0 && targetHeroHp < 0) {
                    perfomDeadHeat(battleId, heroHp);
                } else {

                    if (whoTurn == 1) {
                        setHeroHp(heroHp, battleId, 1);
                        setHeroHp(targetHeroHp, battleId, 2);
                        setActiveFalseHero(battleId, 1);

                    } else {
                        setHeroHp(heroHp, battleId, 2);
                        setHeroHp(targetHeroHp, battleId, 1);
                        setActiveFalseHero(battleId, 2);

                    }
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
            case 112: //spell damage to targetHero
                if (whoTurn == 1) {
                    targetHeroHp = heroesHp(battleId, 2);
                } else {
                    targetHeroHp = heroesHp(battleId, 1);
                }
                spellAttack = spellsAttack(cardOneId);
                targetHeroHp = targetHeroHp - spellAttack;


                if (whoTurn == 1) {
                    setHeroHp(targetHeroHp, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);

                } else {
                    setHeroHp(targetHeroHp, battleId, 1);
                    removeHandCard(cardOneId, battleId, 2);
                }
                break;
            case 114: //spell damage + drow card toHand
                if (whoTurn == 1) {
                    targetHeroHp = heroesHp(battleId, 2);
                } else {
                    targetHeroHp = heroesHp(battleId, 1);
                }
                spellAttack = spellsAttack(cardOneId);
                targetHeroHp = targetHeroHp - spellAttack;


                if (whoTurn == 1) {
                    setHeroHp(targetHeroHp, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);
                    if (sizeHand(battleId, 1) < 1) {
                        if (sizeDeck(battleId, 1) > 0) {
                            deckHeroFromDeckToHand(battleId, 1);
                        }
                    }


                } else {
                    setHeroHp(targetHeroHp, battleId, 1);
                    removeHandCard(cardOneId, battleId, 2);
                    if (sizeHand(battleId, 2) < 1) {
                        if (sizeDeck(battleId, 2) > 0) {
                            deckHeroFromDeckToHand(battleId, 2);
                        }
                    }
                }
                break;
            case 115: //spell to targetHero
                if (whoTurn == 1) {
                    targetHeroHp = heroesHp(battleId, 2);
                } else {
                    targetHeroHp = heroesHp(battleId, 1);
                }
                spellAttack = spellsAttack(cardOneId);
                targetHeroHp = targetHeroHp - spellAttack;


                if (whoTurn == 1) {
                    setHeroHp(targetHeroHp, battleId, 2);
                    setActiveHandFalseHero(battleId, 2); //hand control next turn false
                    removeHandCard(cardOneId, battleId, 1);

                } else {
                    setHeroHp(targetHeroHp, battleId, 1);
                    setActiveHandFalseHero(battleId, 1); //hand control next turn false
                    removeHandCard(cardOneId, battleId, 2);
                }
                break;
            case 116: //spell to targetHero
                if (whoTurn == 1) {
                    setActiveHandFalseHero(battleId, 2); //hand control next turn false
                    removeHandCard(cardOneId, battleId, 1);

                } else {
                    setActiveHandFalseHero(battleId, 1); //hand control next turn false
                    removeHandCard(cardOneId, battleId, 2);
                }
                break;
            case 311:
                if (whoTurn == 1) { ////hero is whose hand/card
                    minionsAttack = minionExtraAttack(cardOneId, battleId, 1);
                    minionsHp = minionExtraHp(cardOneId, battleId, 1);
                    setDamageHpAllTarget(minionsAttack, minionsHp, battleId, 1); ////hero is whose hand/card
                    if (cardTypeService.getByTypeCard(cardOneId) == 1) {
                        removeHandCard(cardOneId, battleId, 1);
                    }
                } else {
                    minionsAttack = minionExtraAttack(cardOneId, battleId, 2);
                    minionsHp = minionExtraHp(cardOneId, battleId, 2);
                    setDamageHpAllTarget(minionsAttack, minionsHp, battleId, 2); ////hero is whose hand/card
                    if (cardTypeService.getByTypeCard(cardOneId) == 1) {
                        removeHandCard(cardOneId, battleId, 2);
                    }
                }
                break;
            case 312:
                if (whoTurn == 1) { ////hero is whose hand/card
                    targetMinionHp = minionHp(cardTargetId, battleId, 2);//card
                } else {
                    targetMinionHp = minionHp(cardTargetId, battleId, 1);//card
                }
                spellAttack = spellsAttack(cardOneId);
                targetMinionHp = targetMinionHp - spellAttack;

                if (whoTurn == 1) {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);
                } else {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                    removeHandCard(cardOneId, battleId, 2);
                }
                break;
            case 313: // spell : set Attack and set Hp to target
                if (whoTurn == 1) { //hero is whose hand/card
                    minionsAttack = minionAttack(cardOneId, battleId, 1); //for set Attack
                    minionsHp = minionHp(cardOneId, battleId, 1); // for set Hp
                } else {
                    minionsAttack = minionAttack(cardOneId, battleId, 2);
                    minionsHp = minionHp(cardOneId, battleId, 2);

                }

                targetMinionHp = minionsHp;
                targetMinionAttack = minionsAttack;

                if (whoTurn == 1) {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 2);
                    settingMinionAttack(targetMinionAttack, cardTargetId, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);
                } else {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                    settingMinionAttack(targetMinionAttack, cardTargetId, battleId, 1);
                    removeHandCard(cardOneId, battleId, 2);
                }
                break;
            case 314: //spell damage + card from deck to table
                Battle b = usWaitBattService.getBattleId(battleId);
                if (whoTurn == 1) { ////hero is whose hand/card
                    targetMinionHp = minionHp(cardTargetId, battleId, 2);//card
                } else {
                    targetMinionHp = minionHp(cardTargetId, battleId, 1);//card
                }
                spellAttack = spellsAttack(cardOneId);
                targetMinionHp = targetMinionHp - spellAttack;

                if (whoTurn == 1) {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);
                    if (sizeHand(battleId, 1) < 1) {
                        if (sizeDeck(battleId, 1) > 0) {
                            deckHeroFromDeckToHand(battleId, 1);
                        }
                    }


                } else {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                    removeHandCard(cardOneId, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);
                    if (sizeHand(battleId, 2) < 1) {
                        if (sizeDeck(battleId, 2) > 0) {
                            deckHeroFromDeckToHand(battleId, 2);
                        }
                    }


                }
                break;
            case 315:
                if (whoTurn == 1) { ////hero is whose hand/card
                    targetMinionHp = minionHp(cardTargetId, battleId, 2);//card
                } else {
                    targetMinionHp = minionHp(cardTargetId, battleId, 1);//card
                }
                spellAttack = spellsAttack(cardOneId);
                targetMinionHp = targetMinionHp - spellAttack;

                if (whoTurn == 1) {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 2);
                    setHadleActiveMinions(cardTargetId, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);
                } else {
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                    setHadleActiveMinions(cardTargetId, battleId, 1);
                    removeHandCard(cardOneId, battleId, 2);
                }
                break;
            case 316:
                if (whoTurn == 1) {
                    setHadleActiveMinions(cardTargetId, battleId, 2);
                    removeHandCard(cardOneId, battleId, 1);
                } else {
                    setHadleActiveMinions(cardTargetId, battleId, 1);
                    removeHandCard(cardOneId, battleId, 2);
                }
                break;
            case 317: //Extra minion Boar (id 21) to table
                if (whoTurn == 1) {
                    if (sizeTable(battleId, 1) < 10) {
                        minionToTable(21, battleId, 1);
                    }
                } else {
                    if (sizeTable(battleId, 2) < 10) {
                        minionToTable(21, battleId, 2);
                    }
                }
                break;
            case 318:
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
                    setHadleActiveMinions(cardTargetId, battleId, 2);
                    setActiveMinions(cardOneId, battleId, 1);
                } else {
                    settingMinionHp(minionsHp, cardOneId, battleId, 2);
                    settingMinionHp(targetMinionHp, cardTargetId, battleId, 1);
                    setHadleActiveMinions(cardTargetId, battleId, 1);
                    setActiveMinions(cardOneId, battleId, 2);
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
        Battle b = usWaitBattService.getBattleId(battleId);
        if (heroHp > 0) {
            if (numberOfHero == 1) {
                b.setHpHero1(heroHp);
            } else {
                b.setHpHero2(heroHp);
            }
        } else {
            int point = abs(heroHp);
            if (numberOfHero == 2) {  //inverse Hero - Winner, looser - setting HeroHp
                b.setWin(1);

                b.setPointsHero1(point);
                addListFinalBattle(battleId, b.getIdUserHero1(), b.getLoginHero1(), b.getNameHero1(), b.getRaceidHero1(), b.getPointsHero1(), b.getGoldHero1() + 1,
                        b.getIdUserHero2(), b.getLoginHero2(), b.getNameHero2(), b.getRaceIdHero2(), b.getPointsHero2(), b.getGoldHero2(), b.getWin());
                User user1 = userService.getById(b.getIdUserHero1());
                user1.setGold(user1.getGold() + 1);
                user1.setPoints(user1.getPoints() + point);
                userService.update(user1);
            } else {
                b.setPointsHero2(point);
                b.setWin(2);
                addListFinalBattle(battleId, b.getIdUserHero1(), b.getLoginHero1(), b.getNameHero1(), b.getRaceidHero1(), b.getPointsHero1(), b.getGoldHero1(),
                        b.getIdUserHero2(), b.getLoginHero2(), b.getNameHero2(), b.getRaceIdHero2(), b.getPointsHero2(), b.getGoldHero2() + 1, b.getWin());
                User user2 = userService.getById(b.getIdUserHero2());
                user2.setGold(user2.getGold() + 1);
                user2.setPoints(user2.getPoints() + point);
                userService.update(user2);
            }

        }

    }

    private void perfomDeadHeat(String battleId, int heroHp) {
        Battle b = usWaitBattService.getBattleId(battleId);
        //int point = abs(heroHp);

        b.setWin(4);// deadHeat
        addListFinalBattle(battleId, b.getIdUserHero1(), b.getLoginHero1(), b.getNameHero1(), b.getRaceidHero1(), b.getPointsHero1(), b.getGoldHero1(),
                b.getIdUserHero2(), b.getLoginHero2(), b.getNameHero2(), b.getRaceIdHero2(), b.getPointsHero2(), b.getGoldHero2(), b.getWin());


    }


    private int sizeHand(String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        List<Integer> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = battle.getHandCollectionHero1();
            return array.size();
        } else {
            array = battle.getHandCollectionHero2();
            return array.size();
        }
    }

    private int sizeTable(String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        if (numberOfHero == 1) {
            return battle.getTableCollectionHero1().size();
        } else {
            return battle.getTableCollectionHero2().size();
        }
    }

    private int sizeDeck(String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        List<Integer> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = battle.getDeckCollectionHero1();
            return array.size();
        } else {
            array = battle.getDeckCollectionHero2();
            return array.size();
        }
    }


    private void setActiveMinions(int cardId, String battleId, int numberOfHero) { //for hand control
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


    private void setHadleActiveMinions(int cardId, String battleId, int numberOfHero) { //for hand control
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }

        if (array.size() > 0) {
            for (CardType ct : array) {
                if (ct.getId() == cardId)
                    ct.setActivate(0);
                ct.setActive(false);
            }
        }

        if (numberOfHero == 1) {
            battle.setTableCollectionHero1(array);
        } else {
            battle.setTableCollectionHero2(array);
        }

    }


    public void setActiveMinionsForReturn(String battleId, int whoTurn) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        if (whoTurn == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }

        if (array.size() > 0) {
            for (CardType ct : array) {
                if (ct.getActivate() == 0) {
                    ct.setActive(false);
                    ct.setActivate(1);
                } else {
                    ct.setActive(true);
                }
            }
        }


        if (whoTurn == 1) {
            battle.setTableCollectionHero1(array);
        } else {
            battle.setTableCollectionHero2(array);
        }

    }


    private int spellsAttack(int cardId) {
        int attack = cardTypeService.getByCardDamage(cardId);
        return attack;
    }

    private void setDamageHpAllTarget(int minionsAttack, int minionsHp, String battleId, int numberOfHero) { //hero is whose hand/card
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        if (minionsAttack < 0) { //damage to Target Minions

            if (numberOfHero == 1) {
                array = battle.getTableCollectionHero2();  //target
            } else {
                array = battle.getTableCollectionHero1(); //target
            }


            Iterator<CardType> cardTypeIterator = array.iterator();
            while (cardTypeIterator.hasNext()) {//до тех пор, пока в списке есть элементы
                CardType nextCardType = cardTypeIterator.next();
                if ((nextCardType.getHp() + minionsAttack) < 1) {
                    //  nextCardType.setActive(false); //only 1 time for turn
                    cardTypeIterator.remove();//удаляем
                }
            }

            for (CardType ct : array) {
                ct.setHp(ct.getHp() + minionsAttack);
            }

            if (numberOfHero == 1) {
                battle.setTableCollectionHero2(array);
            } else {
                battle.setTableCollectionHero1(array);
            }

        } else {   //Cure  or/and  addAttack for allOwnMinions

            if (numberOfHero == 1) {
                array = battle.getTableCollectionHero1();  //own
            } else {
                array = battle.getTableCollectionHero2(); //own
            }

            for (CardType ct : array) {

                ct.setHp(ct.getHp() + minionsHp);
                ct.setDamage(ct.getDamage() + minionsAttack);
            }
            if (numberOfHero == 1) {
                battle.setTableCollectionHero1(array);
            } else {
                battle.setTableCollectionHero2(array);
            }
        }

    }


    private void setActiveHandFalseHero(String battleId, int numberOfHero) {  //next turn Hero - false

        Battle battle = usWaitBattService.getBattleId(battleId);


        if (numberOfHero == 1) {
            battle.setFalseActiveHero1(true);
            battle.setActiveHero1(false);
        } else {
            battle.setActiveHero2(false);
            battle.setFalseActiveHero2(true);
        }

    }

    private void setActiveFalseHero(String battleId, int numberOfHero) {  //next turn Hero - false

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
        if (cardTypeService.getByTypeCard(cardId) == 1) {
            hp = cardTypeService.getByCardHp(cardId);

        } else {
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
        }
        return hp;
    }


    private int minionExtraHp(int cardId, String battleId, int numberOfHero) {

        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        int hpExtra = 0;
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }

        if (array.size() > 0) {
            for (CardType ct : array) {
                if (ct.getId() == cardId) hpExtra = ct.getHpadd();
            }
        }
        return hpExtra;
    }


    private int minionExtraAttack(int cardId, String battleId, int numberOfHero) {

        CardType ct = cardTypeService.getByCardType(cardId);
        return ct.getDamageadd();
    }


    private int minionAttack(int cardId, String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        int attack = 0;
        if (cardTypeService.getByTypeCard(cardId) == 1) {
            attack = cardTypeService.getByCardDamage(cardId);

        } else {
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

    private void minionToTable(int idCard, String battleId, int numberOfHero) {
        Battle battle = usWaitBattService.getBattleId(battleId);
        CardType ct = cardTypeService.getByCardType(idCard);
        ct.setActive(false);
        ct.setActivate(1);
        ArrayList<CardType> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }

        if (array.size() < 10) {
            array.add(ct);
        }
        if (numberOfHero == 1) {
            battle.setTableCollectionHero1(array);
        } else {
            battle.setTableCollectionHero2(array);
        }

    }

    private void settingMinionAttack(int minionAttack, int cardId, String battleId, int numberOfHero) { //hp<0 delete minion, hp>0 setHp minion
        Battle battle = usWaitBattService.getBattleId(battleId);
        ArrayList<CardType> array = new ArrayList<>();
        if (numberOfHero == 1) {
            array = battle.getTableCollectionHero1();
        } else {
            array = battle.getTableCollectionHero2();
        }
        for (CardType ct : array) {
            if (ct.getId() == cardId) {
                ct.setDamage(minionAttack);
            }
        }

        if (numberOfHero == 1) {
            battle.setTableCollectionHero1(array);
        } else {
            battle.setTableCollectionHero2(array);
        }

    }


}


