package controller;

import data.Battle;
import data.CardType;
import data.ListFinalBattle;
import data.User;
import enums.RedirectPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static enums.SessionAttribute.AUTHENTICATED;


@Controller
//@RequestMapping("battle")
public class BattleController {
    private final CardService cardService;
    private final UserOnlineService userOnlineService;
    private final BattleService battleService;
    private final UsWaitBattService usWaitBattService;
    private final UserService userService;
    private final CardTypeService cardTypeService;
    // private final EndOfBattleService endOfBattleService;
    int currentHero = 0;
    int cardOneId = 0;
    int onlyOneTime = 0;
    String mess = " ";
    int firstTurn = 0;
    int whoTurn = 0;
    int win = 0;

    ListFinalBattle finalBattle = null;
    int gold = 0;
    int point = 0;
    String idUser = " ";
    String login = " ";


    @Autowired
    public BattleController(
            final CardService cardService,
            final CardTypeService cardTypeService,
            final UserOnlineService userOnlineService,
            final BattleService battleService,
            final UsWaitBattService usWaitBattService,
            final UserService userService
            //         final EndOfBattleService endOfBattleService
    ) {
        this.cardService = cardService;
        this.userOnlineService = userOnlineService;
        this.battleService = battleService;
        this.usWaitBattService = usWaitBattService;
        this.userService = userService;
        this.cardTypeService = cardTypeService;
        //   this.endOfBattleService = endOfBattleService;
    }


    @RequestMapping(value = "/battle", method = RequestMethod.GET)
    //public String FianlOfBatt (ModelMap model,
    public String Battl(ModelMap model, HttpServletRequest req, HttpServletResponse resp,
                        @RequestParam(name = "id", required = true) String id) throws ServletException, IOException {

        //  ModelAndView out = new ModelAndView("battle");
        //model.addAttribute()
        model.addAttribute("title", "Battle page");
        model.addAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());
        model.addAttribute("pathReg", RedirectPath.REG_PAGE.getValue());
        model.addAttribute("pathHead", RedirectPath.HEAD_PATH.getValue());
        model.addAttribute("pathBattle", RedirectPath.BATTLE_PAGE.getValue());
        model.addAttribute("mess", mess);
        model.addAttribute("cardOneId",cardOneId);
        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        if (userFromSession != null) {
            String idBattle = null;
            String massive = "";

            idBattle = usWaitBattService.getBattleIdForUser(userFromSession.getId());
            Battle b = usWaitBattService.getBattleId(idBattle);
            if (userFromSession.getId() == b.getIdUserHero1()) {
                currentHero = 1;
            } else {
                currentHero = 2;
            }
            win = b.getWin();
            if (win > 0) {
                if (onlyOneTime != currentHero) { //not Winner - remove object
                    resp.sendRedirect("finalOfBattle?id=" + idBattle);    //win
                    usWaitBattService.removeUsBattle(idBattle);
                    System.gc();
                    return "battle";
                }
            }


            if (id.equals("RETURN")) {

                String idUser = userFromSession.getId();
                idBattle = usWaitBattService.getBattleIdForUser(idUser);
                b = usWaitBattService.getBattleId(idBattle);
                whoTurn = whoTurn == 1 ? 2 : 1;
                mess = "";
                battleService.prepeareTable(whoTurn, idBattle); //clean Table and active Minions
                win = b.getWin();
                if (win > 0) {


                    if (onlyOneTime != currentHero) { //  for remove only time
                        usWaitBattService.removeUsBattle(idBattle);
                        System.gc();
                    }
                    resp.sendRedirect("finalOfBattle?id=" + idBattle);    //win
                    return "battle";
                }

                cardOneId = 0;
                if (whoTurn == 1) {  // countMana and activeHero:true
                    if ((b.getMannaHero1() + 1) < 10) {
                        b.setMannaHero1(b.getMannaHero1() + 1);
                    } else {
                        b.setMannaHero1(10);
                    }
                    b.setCurrentMannaHero1(b.getMannaHero1());
                    if (b.isFalseActiveHero1()) {
                        b.setActiveHero1(false);
                        b.setFalseActiveHero1(false);
                    } else {
                        b.setActiveHero1(true);
                    }
                    if (b.getDeckCollectionHero1().size() > 0) {
                        battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero1(), b.getHandCollectionHero1(), idUser, 1);
                    }

                } else { //whoTurn==2
                    if ((b.getMannaHero2() + 1) < 10) {
                        b.setMannaHero2(b.getMannaHero2() + 1);
                    } else {
                        b.setMannaHero2(10);
                    }
                    b.setCurrentMannaHero2(b.getMannaHero2());
                    if (b.isFalseActiveHero2()) {
                        b.setActiveHero2(false);
                        b.setFalseActiveHero2(false);
                    } else {
                        b.setActiveHero2(true);
                    }
                    if (b.getDeckCollectionHero2().size() > 0) {
                        battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero2(), b.getHandCollectionHero2(), idUser, 2);
                    }

                }

                model.addAttribute("cardOneId",cardOneId);
                resp.sendRedirect("battle?id=" + idBattle);

            }


            if (id.length() > 36) {
                idBattle = id;
                idBattle = idBattle.substring(0, 36);
            } else {
                if (idBattle == null) {
                    idBattle = id;
                }

            }

            model.addAttribute("idBattle", idBattle);
            b = usWaitBattService.getBattleId(idBattle);
            int whoIs = 0;
            if (b.getIdUserHero1() == userFromSession.getId()) {
                whoIs = 1;
            } else {
                whoIs = 2;
            }
            model.addAttribute("whoIs", whoIs);


            String getIdUserHero1 = b.getIdUserHero1();
            String getIdUser1Login = b.getLoginHero1();
            int manaHero1 = b.getMannaHero1();
            int currentMannaHero1 = b.getCurrentMannaHero1();
            int hpHero1 = b.getHpHero1();
            int maxHpHero1 = b.getMaxHpHero1();
            boolean activateHero1 = b.isActiveHero1();
            int attackHero1 = b.getAttackHero1();
            int deckCardHero1 = battleService.deckHeroForBattleCount(b.getDeckCollectionHero1());
            List<Integer> deckCollectionHero1 = b.getDeckCollectionHero1();
            List<Integer> handCollectionHero1 = b.getHandCollectionHero1();
            ArrayList<CardType> tableCollectionHero1 = b.getTableCollectionHero1();

            String getIdUserHero2 = b.getIdUserHero2();
            String getIdUser2Login = b.getLoginHero2();
            int manaHero2 = b.getMannaHero2();
            int currentMannaHero2 = b.getCurrentMannaHero2();
            int hpHero2 = b.getHpHero2();
            int maxHpHero2 = b.getMaxHpHero2();
            boolean activateHero2 = b.isActiveHero2();
            int attackHero2 = b.getAttackHero2();
            int deckCardHero2 = battleService.deckHeroForBattleCount(b.getDeckCollectionHero2());
            List<Integer> deckCollectionHero2 = b.getDeckCollectionHero2();
            List<Integer> handCollectionHero2 = b.getHandCollectionHero2();
            ArrayList<CardType> tableCollectionHero2 = b.getTableCollectionHero2();

            model.addAttribute("idUser1", getIdUserHero1);
            model.addAttribute("idUser1Login", getIdUser1Login);
            model.addAttribute("manaHero1", manaHero1);
            model.addAttribute("currentManaHero1", currentMannaHero1);
            model.addAttribute("hpHero1", hpHero1);
            model.addAttribute("maxHpHero1", maxHpHero1);
            model.addAttribute("attackHero1", attackHero1);
            model.addAttribute("deckCardHero1", deckCardHero1);
            model.addAttribute("handCollectionHero1", handCollectionHero1);
            model.addAttribute("tableCollectionHero1", tableCollectionHero1);
            model.addAttribute("activateHero1", activateHero1);


            model.addAttribute("idUser2", getIdUserHero2);
            model.addAttribute("idUser2Login", getIdUser2Login);
            model.addAttribute("manaHero2", manaHero2);
            model.addAttribute("currentManaHero2", currentMannaHero2);
            model.addAttribute("hpHero2", hpHero2);
            model.addAttribute("maxHpHero2", maxHpHero2);
            model.addAttribute("attackHero2", attackHero2);
            model.addAttribute("deckCardHero2", deckCardHero2);
            model.addAttribute("handCollectionHero2", handCollectionHero2);
            model.addAttribute("tableCollectionHero2", tableCollectionHero2);
            model.addAttribute("activateHero2", activateHero2);
            model.addAttribute("cardOneId",cardOneId);

            if (firstTurn == 0) { //Who is the First turn
                whoTurn = battleService.whoTurnFirst();
                mess = " ";
                if (whoTurn == 1) {
                    battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero1(), b.getHandCollectionHero1(), b.getIdUserHero1(), 1);
                    b.setCurrentMannaHero1(1);
                    b.setMannaHero1(1);
                    b.setCurrentMannaHero2(0);
                    b.setMannaHero2(0);
                    b.setActiveHero1(true);
                } else {
                    battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero2(), b.getHandCollectionHero2(), b.getIdUserHero2(), 2);
                    b.setCurrentMannaHero2(1);
                    b.setMannaHero2(1);
                    b.setCurrentMannaHero1(0);
                    b.setMannaHero1(0);
                    b.setActiveHero2(true);
                }
                firstTurn = 1;
            }


            model.addAttribute("whoTurn", whoTurn);

            if (whoTurn == 1) {
                model.addAttribute("turnLogin", b.getLoginHero1());
            } else {
                model.addAttribute("turnLogin", b.getLoginHero2());
            }


            if (id.length() > 36) {

                if ("deckHero".equals(id.substring(36, id.length() - 1))) {   //to Return
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(id.length() - 1, id.length()));
                    if (numberOfHero == 1) {
                        if (deckCardHero1 > 0) {
                            battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero1(), b.getHandCollectionHero1(), idUser, 1);
                        }
                    } else {
                        if (deckCardHero2 > 0) {
                            battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero2(), b.getHandCollectionHero2(), idUser, 2);
                        }
                    }
                    model.addAttribute("cardOneId",cardOneId);
                    resp.sendRedirect("battle?id=" + batId);
                }


                if ("avatarHero".equals(id.substring(36, id.length() - 1))) {   //to Return
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(id.length() - 1, id.length()));


                    if (cardOneId == 0) {
                        if (numberOfHero == 1) {
                            if (b.isActiveHero1()) {
                                mess = "choose a target";
                                cardOneId = -1; //hero

                            } else {
                                mess = "hero is not active! Try one more...";
                                cardOneId = 0;
                            }
                        } else {
                            if (b.isActiveHero2()) {
                                mess = "choose a target";
                                cardOneId = -1; //hero
                            } else {
                                mess = "hero is not active! Try one more...";
                                cardOneId = 0;
                            }
                        }

                    } else { //chek Hero1 hero2
                        //block for cure
                    }


                    if (numberOfHero == 1) {
                    } else {

                    }
                    model.addAttribute("cardOneId",cardOneId);
                    resp.sendRedirect("battle?id=" + batId);
                }

                massive = id.substring(36, id.length() - 1);
                if ("tgHero".equals(massive)) {   //to Return
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(id.length() - 1, id.length()));

                    if (cardOneId == 0) {  //who is attack
                        cardOneId = 0;
                        mess = "Error! Try one more!";
                        //  cardTargetId = 0;
                    } else {
                        int subCase = battleService.subTypeCaseToPerfom(massive, cardOneId);
                        switch (subCase) {
                            case 101:
                                battleService.perfom(cardOneId, -1, whoTurn, batId, 101);// hero to thHero
                                cardOneId = 0;
                                mess = "attack";
                                break;
                            case 104:
                                battleService.perfom(cardOneId, -1, whoTurn, batId, 104); // minion attack thHero
                                cardOneId = 0;
                                mess = "attack";
                                break;
                            case 112:
                            case 114:
                            case 115:
                                battleService.perfom(cardOneId, -1, whoTurn, batId, subCase); // minion attack thHero
                                mess = "attack";
                                if (whoTurn == 1) {
                                    b.setCurrentMannaHero1((b.getCurrentMannaHero1() - cardService.getByMana(cardOneId))); //  current -mana
                                } else {
                                    b.setCurrentMannaHero2((b.getCurrentMannaHero2() - cardService.getByMana(cardOneId))); //  current -mana
                                }
                                cardOneId = 0;
                                break;
                            default:
                                mess = "not for hero. Try one more!";
                                cardOneId = 0;
                                break;

                        }
                    }

                    if (numberOfHero == 1) {
                        //cardOneId!=null else mess choose who attack!  // Hp Hero - attack //cardOneId=-1 for hand spell(2)
                    } else {


                    }

                    win = b.getWin();
                    if (win > 0) {
                        onlyOneTime = whoTurn; //winner
                        resp.sendRedirect("finalOfBattle?id=" + batId);    //win
                        return "battle";
                    }
                    resp.sendRedirect("battle?id=" + batId);
                }

            }

            if (id.length() > 40) {
                massive = id.substring(36, 40);
                if ("hand".equals(massive)) {
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(40, 41));
                    int idCard = Integer.parseInt(id.substring(41, id.length()));
                    int cardType = cardTypeService.getByTypeCard(idCard); //spell 1, minion 2
                    if (numberOfHero == 1) {
                        if ((b.getCurrentMannaHero1() - cardService.getByMana(idCard)) > -1) {
                            if (cardTypeService.getBySubTypeCard(idCard) != 0) { //if extra active
                                switch (battleService.subTypeCaseToPerfom(massive, idCard)) { //  always First card for start
                                    case 311:
                                        battleService.perfom(idCard, idCard, 1, batId, 311); // case 311
                                        cardOneId = 0;
                                        mess = "spell applies"; //spell
                                        break;
                                    case 319:
                                        battleService.perfom(idCard, idCard, 1, batId, 319); // case 311
                                        cardOneId = 0;
                                        mess = "spell applies"; //spell
                                        break;
                                }

                            }
                            if (cardType == 2) { //if minion
                                switch (battleService.subTypeCaseToPerfom(massive, idCard)) {
                                    case 317:
                                        battleService.perfom(idCard, idCard, 1, batId, 317);
                                        break;
                                }
                                battleService.fromHandToTable(b.getHandCollectionHero1(), b.getTableCollectionHero1(), idCard, idUser, numberOfHero);
                                b.setCurrentMannaHero1((b.getCurrentMannaHero1() - cardService.getByMana(idCard))); //How is mana,  current -mana
                            }
                            if (cardType == 1) { //if spell
                                if (cardOneId != 0) {
                                    cardOneId = 0;
                                    mess = "Error! Try one more!";

                                } else {
                                    cardOneId = idCard;
                                    mess = "Choose a target";
                                }
                            }
                        } else {
                            mess = "not enough mana";
                        }


                    } else {  //for Hero==2
                        if ((b.getCurrentMannaHero2() - cardService.getByMana(idCard)) > -1) {
                            if (cardTypeService.getBySubTypeCard(idCard) != 0) {
                                switch (battleService.subTypeCaseToPerfom(massive, idCard)) { //  addExtra action
                                    case 311:
                                        battleService.perfom(idCard, idCard, 2, batId, 311); // case 311
                                        cardOneId = 0;
                                        mess = "spell applies"; //spell
                                        break;
                                    case 319:
                                        battleService.perfom(idCard, idCard, 2, batId, 319); // case 311
                                        cardOneId = 0;
                                        mess = "spell applies"; //spell
                                        break;
                                }
                            }

                            if (cardType == 2) { //if minion
                                switch (battleService.subTypeCaseToPerfom(massive, idCard)) {
                                    case 317:
                                        battleService.perfom(idCard, idCard, 2, batId, 317);
                                        break;
                                }
                                battleService.fromHandToTable(b.getHandCollectionHero2(), b.getTableCollectionHero2(), idCard, idUser, numberOfHero);
                                b.setCurrentMannaHero2((b.getCurrentMannaHero2() - cardService.getByMana(idCard)));
                            }
                            if (cardType == 1) { //if spell
                                if (cardOneId != 0) {
                                    cardOneId = 0;
                                    mess = "Error! Try one more!";

                                } else {
                                    cardOneId = idCard;
                                    mess = "Choose a target";
                                }
                            }


                        } else {
                            mess = "not enough mana";
                        }

                    }
                    model.addAttribute("cardOneId",cardOneId);
                    resp.sendRedirect("battle?id=" + batId);
                }

                massive = id.substring(36, 41);
                if ("table".equals(massive)) {
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(41, 42));
                    int idCard = Integer.parseInt(id.substring(42, id.length()));
                    if (battleService.getByActiveCard(idBattle, idCard, whoTurn)) { // active card ?
                        if (cardOneId == 0) {  //who is attack
                            mess = "choose a target";
                            cardOneId = idCard;
                            //cardTargetId = 0;
                        } else {
                            //not yet cure!
                            cardOneId = 0;
                            mess = "Target error! Try one more!";
                        }
                        if (numberOfHero == 1) {

                        } else {  //Hero==2
                        }
                    } else {
                        mess = "card is not Active";
                    }
                    model.addAttribute("cardOneId",cardOneId);
                    resp.sendRedirect("battle?id=" + batId);
                }

                massive = id.substring(36, 42);
                if ("target".equals(massive)) {
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(42, 43));
                    int idCard = Integer.parseInt(id.substring(43, id.length()));
                    // int cardType = cardTypeService.getByTypeCard(idCard); //spell 1, minion 2

                    if (cardOneId == 0) {  //who is attack
                        mess = "Error! Try one more!";
                        cardOneId = 0;
                        //cardTargetId = 0;
                    } else {
                        int subCase = battleService.subTypeCaseToPerfom(massive, cardOneId);
                        battleService.perfom(cardOneId, idCard, whoTurn, batId, subCase);
                        mess = "attack";
                        switch (subCase) {
                            case 312:
                            case 314:
                            case 313:
                            case 315:
                            case 316:
                                if (whoTurn == 1) {
                                    b.setCurrentMannaHero1((b.getCurrentMannaHero1() - cardService.getByMana(cardOneId))); //How is mana,  current -mana
                                } else {
                                    b.setCurrentMannaHero2((b.getCurrentMannaHero2() - cardService.getByMana(cardOneId))); //How is mana,  current -mana
                                }
                                cardOneId = 0;
                                break;
                        }
                    }
                    if (numberOfHero == 1) { //
                    } else {
                    }
                    resp.sendRedirect("battle?id=" + batId);
                }
            }

        } else {
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }
//        model.addAttribute("deckCardHero2", 9);

        model.addAttribute("cardOneId",cardOneId);
        return "battle";
    }


    @RequestMapping(value = "/finalOfBattle", method = RequestMethod.GET)
    public String FianlOfBatt(ModelMap model, HttpServletRequest req, HttpServletResponse resp,
                              @RequestParam(name = "id", required = true) String id) throws ServletException, IOException {
        model.addAttribute("title", "The End Of Battle");
        model.addAttribute("pathHead", RedirectPath.HEAD_PATH.getValue());
        model.addAttribute("pathMain", RedirectPath.MAIN_PAGE.getValue());

        model.addAttribute("pathWaitBattle", RedirectPath.WAITBATTLE_PAGE.getValue());

        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        req.getSession().setAttribute(AUTHENTICATED.getValue(), userFromSession);

        if (userFromSession != null) {


            if (id != null) {


                finalBattle = battleService.getIdFinalBattle(id);
                int numOfUser = battleService.getNumOfHeroBattl(id, userFromSession.getId());
                if (finalBattle.getWin() == 4) {
                    mess = "Dead heat! point is Zero!. Try one more";
                } else {
                    mess = "You lose. Try one more!";
                }

                if (finalBattle.getWin() == numOfUser) {
                    mess = "Congratulations! You are Winner! Try again!";
                }
                if (numOfUser == 1) {
                    login = finalBattle.getLoginHero1();
                    idUser = finalBattle.getIdUserHero1();
                    gold = finalBattle.getGoldHero1();
                    point = finalBattle.getPointsHero1();
                } else {
                    login = finalBattle.getLoginHero2();
                    idUser = finalBattle.getIdUserHero2();
                    gold = finalBattle.getGoldHero2();
                    point = finalBattle.getPointsHero2();

                }


                model.addAttribute("mess", mess);
                model.addAttribute("idUser", idUser);
                model.addAttribute("login", login);
                model.addAttribute("gold", gold);
                model.addAttribute("point", point);

            }


        } else {
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }


        return "finalOfBattle";
    }

}










