package controller;


import dao.UserDao;
import data.User;
import enums.RedirectPath;
import enums.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.RaceService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static enums.SessionAttribute.AUTHENTICATED;

@Controller
@RequestMapping("main")
public class MainController {

    private final RaceService raceService;
    private final UserService userService;




    @Autowired
    public MainController(
            final RaceService raceService,
            final UserService userService)
    {
        this.raceService = raceService;
        this.userService = userService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView out = new ModelAndView("main");
        out.addObject("title", "HearthStone");
        out.addObject("pathEditUser", RedirectPath.EDIT_USER.getValue());
        out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
       // String pathReg = RedirectPath.REG_PAGE.getValue();
        out.addObject("pathReg", RedirectPath.REG_PAGE.getValue());
        out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());
        out.addObject("pathAuth", RedirectPath.LOGIN_PAGE.getValue());
        out.addObject("deck", 1);
        if ((req.getSession().getAttribute(AUTHENTICATED.getValue())) != null) {
            User user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());
            out.addObject("deck",userService.getByDeck(user));
//width="120" height="80"
            out.addObject("idUser", user.getId());
            out.addObject("Login", user.getLogin());

            //out.addObject("userads", userService.getByUsersFromAds());

        }
        if(   req.getParameter(RequestParameter.LOGOFF.getValue()) != null){
            req.getSession().invalidate();
            out.addObject("Login", "");
        }


        return out;
    }
}
