package controller;


import enums.RedirectPath;
import enums.RequestParameter;
import enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.UserService;
import service.ValidationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static enums.SessionAttribute.AUTHENTICATED;

@Controller
@RequestMapping("auth")
public class AuthController {


    private ValidationService validationService;
    private final UserService userService;


    @Autowired
    public AuthController(
            final   UserService userService,
            ValidationService validationService

    ) {
        super();

        this.validationService = validationService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView out = new ModelAndView("auth");
        out.addObject("title", "authorization page");
        String pathMain = RedirectPath.MAIN_PAGE.getValue();
        out.addObject("pathMain", pathMain);
        String pathAuth = RedirectPath.LOGIN_PAGE.getValue();
        out.addObject("pathAuth", pathAuth);
       

        return out;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView out2 = new ModelAndView("auth");
        out2.addObject("title", "auth page");

        String login = req.getParameter(RequestParameter.LOGIN.getValue());
        String pass = req.getParameter(RequestParameter.PASS.getValue());
        

        if (validationService.validateAuthentication(login, pass)) {
            req.getSession().setAttribute(AUTHENTICATED.getValue(), userService.getByLogin(login));
            resp.sendRedirect(RedirectPath.MAIN_REDIRECT.getValue());
            }
        else {
            UserStatus status = UserStatus.PASSWORD_INCORRECT;
            out2.addObject("status", status);
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());}
        UserStatus status = UserStatus.PASSWORD_INCORRECT;
        out2.addObject("status", status);
        return out2;

    }

}









