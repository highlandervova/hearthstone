package controller;

import data.User;
import enums.RedirectPath;
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

import static enums.SessionAttribute.AUTHENTICATED;


@Controller
@RequestMapping("deck")
public class DeckController {
    private final CardService cardService;
    private final UserOnlineService userOnlineService;
    private final UserDeckService userDeckService;
    @Autowired
    public DeckController(
            final CardService cardService,
            final UserOnlineService userOnlineService,
            final UserDeckService userDeckService
    ) {
        this.cardService = cardService;
        this.userOnlineService = userOnlineService;
        this.userDeckService = userDeckService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ModelAndView out = new ModelAndView("deck");
        out.addObject("title", "sort deck");
        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        if (userFromSession != null ) {

            out.addObject("pathUserDeck", RedirectPath.DECKUSER_PAGE.getValue());
            out.addObject("pathDeckBottom", RedirectPath.DECKBOTTOM_PAGE.getValue());
            out.addObject("pathMainDeck", RedirectPath.DECKMAIN_PAGE.getValue());
        } else{
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }


   return out;
    }
}






