package controller;


import data.ListFinalBattle;
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
import java.util.ArrayList;

import static enums.SessionAttribute.AUTHENTICATED;

@Controller
@RequestMapping("endOfBattle")
public class EndOfBattleController {

    //    private ValidationService validationService;
//    private final UserService userService;
//    final UsWaitBattService usWaitBattService;
//    final BattleService battleService;
    private final EndOfBattleService endOfBattleService;
    ListFinalBattle finalBattle = null;
    String mess = " ";
    String idUser = " ";
    String login = " ";
    int gold = 0;
    int point = 0;


    @Autowired
    public EndOfBattleController(
//            final UserService userService,
//            UsWaitBattService usWaitBattService,
//            ValidationService validationService,
//            BattleService battleService
            EndOfBattleService endOfBattleService
    ) {

//        super();
//        this.battleService = battleService;
//        this.validationService = validationService;
//        this.usWaitBattService = usWaitBattService;
//        this.userService = userService;
        this.endOfBattleService = endOfBattleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp,
                               @RequestParam(name = "id", required = true) String id) throws ServletException, IOException {

        ModelAndView out = new ModelAndView("endOfBattle");
        out.addObject("title", "The End Of Battle");
        out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());

        out.addObject("pathWaitBattle", RedirectPath.WAITBATTLE_PAGE.getValue());

        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        req.getSession().setAttribute(AUTHENTICATED.getValue(), userFromSession);

        if (userFromSession != null) {


            if (id != null) {

                finalBattle = endOfBattleService.getId(id);
                int numOfUser = endOfBattleService.getNumOfHeroBattl(id, userFromSession.getId());
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


                out.addObject("mess", mess);
                out.addObject("idUser", idUser);
                out.addObject("login", login);
                out.addObject("gold", gold);
                out.addObject("point", point);

            }


        } else {
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }


        return out;
    }


}









