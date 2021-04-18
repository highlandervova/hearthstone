package controller;


import data.User;
import enums.RedirectPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static enums.SessionAttribute.AUTHENTICATED;

@Controller
@RequestMapping("waitBattle")
public class WaitBattleController {

    private ValidationService validationService;
    private final UserService userService;
    final UsWaitBattService usWaitBattService;


    @Autowired
    public WaitBattleController(
            final   UserService userService,
            UsWaitBattService usWaitBattService,
            ValidationService validationService) {

        super();

        this.validationService = validationService;
        this.usWaitBattService = usWaitBattService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView out = new ModelAndView("waitBattle");
        out.addObject("title", "waitBattle");
        String pathMain = RedirectPath.MAIN_PAGE.getValue();
        String pathReg = RedirectPath.REG_PAGE.getValue();
        out.addObject("pathMain", pathMain);
        out.addObject("pathReg", pathReg);
        String pathAuth = RedirectPath.LOGIN_PAGE.getValue();
        out.addObject("pathAuth", pathAuth);

        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        if (userFromSession != null) {


            if (usWaitBattService.waitBattleEmpty()) {
              usWaitBattService.addWaitBattle(userFromSession.getId(),userFromSession);

            }else{
                User user2 = usWaitBattService.getUserFromMapAsPlayer2();
                String idBattle = usWaitBattService.addPrepareForBattle(userFromSession, user2);
                resp.sendRedirect("battle?id="+idBattle);
            }

        }else{
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }


        return out;
    }



}









