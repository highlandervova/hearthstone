package controller;

import data.User;
import enums.RedirectPath;
import enums.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserDeckService;
import service.UserOnlineService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static enums.RequestParameter.SAVEDECK;
import static enums.SessionAttribute.AUTHENTICATED;


@Controller
@RequestMapping("deckBottom")
public class DeckBottomController {
    private final UserDeckService userDeckService;
    @Autowired
    public DeckBottomController(final UserDeckService userDeckService)
    {   this.userDeckService = userDeckService; }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView out = new ModelAndView("deckBottom");
        out.addObject("title", "deckBottom");
        out.addObject("pathReg", RedirectPath.REG_PAGE.getValue());
        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        if (userFromSession != null ) {
            out.addObject("mightSave", userDeckService.getUsOwnDeckCount());
            String pathMain = RedirectPath.MAIN_PAGE.getValue();
            String pathReg = RedirectPath.REG_PAGE.getValue();
            out.addObject("pathMain", pathMain);
            out.addObject("pathReg", pathReg);
            String pathAuth = RedirectPath.LOGIN_PAGE.getValue();
            out.addObject("pathAuth", pathAuth);
            if  (req.getParameter(RequestParameter.SAVEDECK.getValue()) != null) {
               userDeckService.setUsOwnDeck(userFromSession);
               resp.sendRedirect(RedirectPath.MAIN_PAGE.getValue());
            }

        } else{
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }
   return out;
    }
}










