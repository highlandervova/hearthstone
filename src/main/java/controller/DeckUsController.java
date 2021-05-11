package controller;

import data.Card;
import data.User;
import enums.RedirectPath;
import enums.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserDeckService;
import service.UserOnlineService;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import static enums.SessionAttribute.AUTHENTICATED;


@Controller
@RequestMapping("deckUs")
public class DeckUsController {
    private final CardService cardService;
    private final UserOnlineService userOnlineService;
    private final UserDeckService userDeckService;

    @Autowired
    public DeckUsController(
            final CardService cardService,
            final UserOnlineService userOnlineService,
            final UserDeckService userDeckService
    ) {
        this.cardService = cardService;
        this.userOnlineService = userOnlineService;
        this.userDeckService = userDeckService;
    }

    String usId = "";

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView deckUsGet(HttpServletRequest req, HttpServletResponse resp,
                                  @RequestParam(name = "id", required = true) String id) throws ServletException, IOException {

        ModelAndView out = new ModelAndView("deckUs");
        HttpSession httpSession = req.getSession(false);

        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());

        if (httpSession != null) {

            if (!usId.equals(userFromSession.getId())) {

                userDeckService.createUsMainDeck(userFromSession.getId());
                User user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());

                if (user.getDeck() != null) {
                  userDeckService.createOwnDeckFromUserDeck(userFromSession);
                         }
                usId = userFromSession.getId();
                resp.sendRedirect("deckUs?id=all");
                return out;
            }

            out.addObject("title", "DeckUser");
            out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());

            out.addObject("pathReg", RedirectPath.REG_PAGE.getValue());
            out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
            out.addObject("pathMainDeck", RedirectPath.DECKMAIN_PAGE.getValue());

            int mightSave = userDeckService.getUsOwnDeckCount(userFromSession.getId());
            out.addObject("cardForUs", userDeckService.getUsOwnDeck(userFromSession.getId()));

            out.addObject("mightSave", mightSave);


            out.addObject("cardForDeck", userDeckService.getUsMainDeck(userFromSession.getId()));
            mightSave = userDeckService.getUsOwnDeckCount(userFromSession.getId());
            out.addObject("cardForUs", userDeckService.getUsOwnDeck(userFromSession.getId()));
            out.addObject("mightSave", mightSave);


            out.addObject("cardForDeck", userDeckService.getUsMainDeck(userFromSession.getId()));


            if (id != null) {
                if (id.equals("all")) {

                    out.addObject("cardForDeck", userDeckService.getUsMainDeck(userFromSession.getId()));


                }

                String checkDeckFromMain = "mn";

                if (checkDeckFromMain.equals(id.substring(id.length() - 2))) {

                    int cardUsId = Integer.valueOf(id.substring(0, id.length() - 2));

                    userDeckService.addForUsOwnDeck(cardService.getById(cardUsId), userFromSession.getId());
                    out.addObject("mightSave", userDeckService.getUsOwnDeckCount(userFromSession.getId()));
                }
                String checkDeckFromDeckUser = "us";
                if (checkDeckFromDeckUser.equals(id.substring(id.length() - 2))) {
                    int cardUsId = Integer.valueOf(id.substring(0, id.length() - 2));
                    userDeckService.addForUsMainDeck(cardService.getById(cardUsId), userFromSession.getId());

                    out.addObject("mightSave", userDeckService.getUsOwnDeckCount(userFromSession.getId()));
                }
            }

            out.addObject("cardForDeck", userDeckService.getUsMainDeck(userFromSession.getId()));

            if (id.equals("SaveDeck")) {
                //out.addObject("cardForUs", userDeckService.getUsOwnDeck(userFromSession.getId()));
                userDeckService.setUsOwnDeck(userFromSession);
                resp.sendRedirect(RedirectPath.MAIN_PAGE.getValue());
            }


        } else {
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }
        return out;
    }


}










