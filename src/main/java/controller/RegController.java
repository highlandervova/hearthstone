package controller;


//import enums.EditUserStatus;
import enums.RedirectPath;
import enums.RequestParameter;
import enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import service.RaceService;
import service.UserOnlineService;
import service.UserService;
import service.ValidationService;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static enums.SessionAttribute.AUTHENTICATED;

@Controller
@RequestMapping("reg")
public class RegController {


    private ValidationService validationService;
    private final UserService userService;
    private final RaceService raceService;
    private UserOnlineService userOnlineService;

    @Autowired
    public RegController(
            final   UserService userService,
            ValidationService validationService,
            RaceService raceService,
            UserOnlineService userOnlineService

    ) {
        super();

        this.validationService = validationService;
        this.userService = userService;
        this.raceService = raceService;
        this.userOnlineService = userOnlineService;
    }




    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView out = new ModelAndView("reg");
        out.addObject("title", "reg page");
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());
        out.addObject("races", raceService.getRaces());
        return out;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView out = new ModelAndView("reg");
        out.addObject("title", "reg page");

        String login    = req.getParameter(RequestParameter.LOGIN.getValue());
        String pass1    = req.getParameter(RequestParameter.PASS1.getValue());
        String pass2    = req.getParameter(RequestParameter.PASS2.getValue());
        String name     = req.getParameter(RequestParameter.NAME.getValue());
        String lastname = req.getParameter(RequestParameter.LASTNAME.getValue());

        String email    = req.getParameter(RequestParameter.EMAIL.getValue());
        int race        =  Integer.parseInt(req.getParameter(RequestParameter.RACE.getValue()));

        if (validationService.validateRegistration(login, pass1, pass2)) {
             userService.addNewUser(login, pass1, name, lastname , race, email);
            req.getSession().setAttribute(AUTHENTICATED.getValue(), userService.getByLogin(login));
            userOnlineService.add(userService.getByLogin(login));
            resp.sendRedirect(RedirectPath.MAIN_REDIRECT.getValue());
            }
        UserStatus status = UserStatus.PASSWORD_FIELDS_MISMATCH;
        out.addObject("status", status.getValue());
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());
        out.addObject("races", raceService.getRaces());
        return out;
    }

}








