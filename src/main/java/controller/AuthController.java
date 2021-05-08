package controller;


import data.User;
import enums.RedirectPath;
import enums.RequestParameter;
import enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.UserOnlineService;
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

     int i =0;
    private ValidationService validationService;
    private final UserService userService;
    private UserOnlineService userOnlineService;

    @Autowired
    public AuthController(
            final   UserService userService,
            ValidationService validationService,
            UserOnlineService userOnlineService

    ) {
        super();
        this.userOnlineService = userOnlineService;
        this.validationService = validationService;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView out = new ModelAndView("auth");
        out.addObject("title", "authorization page");
        String pathMain = RedirectPath.MAIN_PAGE.getValue();
        String pathReg = RedirectPath.REG_PAGE.getValue();
        out.addObject("pathMain", pathMain);
        out.addObject("pathReg", pathReg);
        String pathAuth = RedirectPath.LOGIN_PAGE.getValue();
        out.addObject("pathAuth", pathAuth);
       if (i==1){
           UserStatus status = UserStatus.PASSWORD_INCORRECT;
           out.addObject("status", status);
           i=0;
       }

        if (i>1){
            UserStatus status = UserStatus.USER_ALREADY_REGISTER;
            out.addObject("status", status);
            i=0;
        }

        return out;
    }


    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView postGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView out2 = new ModelAndView("auth");
        out2.addObject("title", "auth page");
        String login = req.getParameter(RequestParameter.LOGIN.getValue());
        String pass = req.getParameter(RequestParameter.PASS.getValue());
        

        if (validationService.validateAuthentication(login, pass)) {
            if (userOnlineService.findUser(userService.getByLogin(login))) {
                req.getSession().setAttribute(AUTHENTICATED.getValue(), userService.getByLogin(login));
                User user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());

                userOnlineService.add(user);

                //add((User) req.getSession().getAttribute(AUTHENTICATED.getValue()));

                resp.sendRedirect(RedirectPath.MAIN_REDIRECT.getValue());
            }else {
                UserStatus status = UserStatus.USER_ALREADY_REGISTER;
                out2.addObject("status", status);
                i=2;
                resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
            }
        }
        else {
            UserStatus status = UserStatus.PASSWORD_INCORRECT;
            out2.addObject("status", status);
            i=1;
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }
        String pathMain = RedirectPath.MAIN_PAGE.getValue();
        String pathReg = RedirectPath.REG_PAGE.getValue();
        out2.addObject("pathMain", pathMain);
        out2.addObject("pathReg", pathReg);
        //UserStatus status = UserStatus.PASSWORD_INCORRECT;
        //out2.addObject("status", status.getValue());

        return out2;

    }

}









