package controller;

import data.Battle;
import data.User;
import enums.RedirectPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static enums.SessionAttribute.AUTHENTICATED;


@Controller
@RequestMapping("battle")
public class BattleController {
    private final CardService cardService;
    private final UserOnlineService userOnlineService;
    private final BattleService battleService;
    private final UsWaitBattService usWaitBattService;
    private final UserService userService;
    private final CardTypeService cardTypeService;
    String currentHeroId = null;
    int firstTurn = 0;
    int whoTurn = 0;

    @Autowired
    public BattleController(
            final CardService cardService,
            final CardTypeService cardTypeService,
            final UserOnlineService userOnlineService,
            final BattleService battleService,
            final UsWaitBattService usWaitBattService,
            final UserService userService
    ) {
        this.cardService = cardService;
        this.userOnlineService = userOnlineService;
        this.battleService = battleService;
        this.usWaitBattService = usWaitBattService;
        this.userService = userService;
        this.cardTypeService = cardTypeService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp,
                               @RequestParam(name = "id", required = true) String id) throws ServletException, IOException {

        ModelAndView out = new ModelAndView("battle");
        out.addObject("title", "Battle page");
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());
        out.addObject("pathReg", RedirectPath.REG_PAGE.getValue());
        out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
        out.addObject("pathBattle", RedirectPath.BATTLE_PAGE.getValue());
        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        if (userFromSession != null) {
            String idBattle = null;
            if (id.equals("RETURN")) {
                String idUser = userFromSession.getId();
                idBattle = usWaitBattService.getBattleIdForUser(idUser);
                Battle b = usWaitBattService.getBattleId(idBattle);
                whoTurn = whoTurn == 1 ? 2 : 1;
                if (whoTurn == 1) {

                    if ((b.getMannaHero1() + 1) < 10) {
                        b.setMannaHero1(b.getMannaHero1() + 1);
                    } else {
                        b.setMannaHero1(10);
                    }
                    b.setCurrentMannaHero1(b.getMannaHero1());



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


                    if (b.getDeckCollectionHero2().size() > 0) {
                        battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero2(), b.getHandCollectionHero2(), idUser, 2);
                    }
                }

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

            out.addObject("idBattle", idBattle);
            //out.addObject("cardFromMain", cardService.get());

            Battle b = usWaitBattService.getBattleId(idBattle);
            int whoIs = 0;
            if (b.getIdUserHero1() == userFromSession.getId()) {
                whoIs = 1;
            } else {
                whoIs = 2;
            }
            out.addObject("whoIs", whoIs);


            String getIdUserHero1 = b.getIdUserHero1();
            String getIdUser1Login = b.getLoginHero1();
            int manaHero1 = b.getMannaHero1();
            int currentMannaHero1 = b.getCurrentMannaHero1();
            int hpHero1 = b.getHpHero1();
            int maxHpHero1 = b.getMaxHpHero1();
            int attackHero1 = b.getAttackHero1();
            int deckCardHero1 = battleService.deckHeroForBattleCount(b.getDeckCollectionHero1());
            List<Integer> deckCollectionHero1 = b.getDeckCollectionHero1();
            List<Integer> handCollectionHero1 = b.getHandCollectionHero1();
            List<Integer> tableCollectionHero1 = b.getTableCollectionHero1();

            String getIdUserHero2 = b.getIdUserHero2();
            String getIdUser2Login = b.getLoginHero2();
            int manaHero2 = b.getMannaHero2();
            int currentMannaHero2 = b.getCurrentMannaHero2();
            int hpHero2 = b.getHpHero2();
            int maxHpHero2 = b.getMaxHpHero2();
            int attackHero2 = b.getAttackHero2();
            int deckCardHero2 = battleService.deckHeroForBattleCount(b.getDeckCollectionHero2());
            List<Integer> deckCollectionHero2 = b.getDeckCollectionHero2();
            List<Integer> handCollectionHero2 = b.getHandCollectionHero2();
            List<Integer> tableCollectionHero2 = b.getTableCollectionHero2();

            out.addObject("idUser1", getIdUserHero1);
            out.addObject("idUser1Login", getIdUser1Login);
            out.addObject("manaHero1", manaHero1);
            out.addObject("currentManaHero1", currentMannaHero1);
            out.addObject("hpHero1", hpHero1);
            out.addObject("maxHpHero1", maxHpHero1);
            out.addObject("attackHero1", attackHero1);
            out.addObject("deckCardHero1", deckCardHero1);
            out.addObject("handCollectionHero1", handCollectionHero1);
            out.addObject("tableCollectionHero1", tableCollectionHero1);


            out.addObject("idUser2", getIdUserHero2);
            out.addObject("idUser2Login", getIdUser2Login);
            out.addObject("manaHero2", manaHero2);
            out.addObject("currentManaHero2", currentMannaHero2);
            out.addObject("hpHero2", hpHero2);
            out.addObject("maxHpHero2", maxHpHero2);
            out.addObject("attackHero2", attackHero2);
            out.addObject("deckCardHero2", deckCardHero2);
            out.addObject("handCollectionHero2", handCollectionHero2);
            out.addObject("tableCollectionHero2", tableCollectionHero2);


            if (firstTurn == 0) { //Who is the First turn
                whoTurn = battleService.whoTurnFirst();

                if (whoTurn == 1) {
                    battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero1(), b.getHandCollectionHero1(), b.getIdUserHero1(), 1);
                } else {
                    battleService.deckHeroFromDeckToHand(b.getDeckCollectionHero2(), b.getHandCollectionHero2(), b.getIdUserHero2(), 2);
                }
                firstTurn = 1;
            }


            out.addObject("whoTurn", whoTurn);

            if (whoTurn == 1) {
                out.addObject("turnLogin", b.getLoginHero1());
            } else {
                out.addObject("turnLogin", b.getLoginHero2());
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
                    resp.sendRedirect("battle?id=" + batId);
                }


                if ("avatarHero".equals(id.substring(36, id.length() - 1))) {   //to Return
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(id.length() - 1, id.length()));
                    if (numberOfHero == 1) {
                    } else {

                    }
                    resp.sendRedirect("battle?id=" + batId);
                }
            }

            // 52932b68-2 4d4-4c9a-9 eb0-8cf414 51975dhand 18
            if (id.length() > 40) {
                if ("hand".equals(id.substring(36, 40))) {
                    String idUser = userFromSession.getId();
                    String batId = usWaitBattService.getBattleIdForUser(idUser);
                    int numberOfHero = Integer.valueOf(id.substring(40, 41));
                    int idCard = Integer.parseInt(id.substring(41, id.length()));
                    int cardType = cardTypeService.getByTypeCard(idCard); //spell 1, minion 2
                    if (numberOfHero == 1) {
                        if (cardType == 2) { //if minion
                            if ((b.getCurrentMannaHero1() - cardService.getByMana(idCard))>-1) {
                                battleService.fromHandToTable(b.getHandCollectionHero1(), b.getTableCollectionHero1(), idCard, idUser, numberOfHero);

                                b.setCurrentMannaHero1((b.getCurrentMannaHero1() - cardService.getByMana(idCard))); //How is mana,  current -mana
                            }
                        }

                    } else {  //Hero==2
                        if (cardType == 2) { //if minion
                            if ((b.getCurrentMannaHero2() - cardService.getByMana(idCard))>-1) {
                                battleService.fromHandToTable(b.getHandCollectionHero2(), b.getTableCollectionHero2(), idCard, idUser, numberOfHero);
                                b.setCurrentMannaHero2((b.getCurrentMannaHero2() - cardService.getByMana(idCard)));
                            }
                        }
                    }
                    resp.sendRedirect("battle?id=" + batId);
                }
            }
            //http://localhost:8080/hearthstone_war/battle?id=RETURN


        } else {
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }
//        out.addObject("deckCardHero2", 9);

        return out;
    }
}










