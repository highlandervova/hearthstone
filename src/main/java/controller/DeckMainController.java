package controller;

import data.User;
import enums.RedirectPath;
import enums.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import service.CardService;
import service.UserDeckService;
import service.UserOnlineService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static enums.RequestParameter.PIC;
import static enums.RequestParameter.PICUSERREMOVE;
import static enums.SessionAttribute.AUTHENTICATED;


@Controller
@RequestMapping("deckMain")
public class DeckMainController {
    private final CardService cardService;
    private final UserOnlineService userOnlineService;
    private final UserDeckService userDeckService;


    //private final TimerTask timerTask;


    @Autowired
    public DeckMainController(
            final CardService cardService,
            final UserOnlineService userOnlineService,
            final UserDeckService userDeckService
    ) {
        this.cardService = cardService;
        this.userOnlineService = userOnlineService;
        this.userDeckService = userDeckService;
    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView regGet(HttpServletRequest req, HttpServletResponse resp,
    @RequestParam(name = "id", required = true) String id  ) throws ServletException, IOException {

        ModelAndView out = new ModelAndView("deckMain");
        out.addObject("title", "DECKMAIN");
        out.addObject("pathReg", RedirectPath.REG_PAGE.getValue());
        out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
        out.addObject("pathMainDeck", RedirectPath.DECKMAIN_PAGE.getValue());

        out.addObject("cardForDeck",userDeckService.getUsMainDeck());
        User userFromSession = (User) req.getSession(false).getAttribute(AUTHENTICATED.getValue());
        if (userFromSession != null) {

            int mightSave = userDeckService.getUsOwnDeckCount();
            out.addObject("mightSave",mightSave);
       if (id!= null ){
                    if (id.equals("all")){
               out.addObject("cardForDeck",userDeckService.getUsMainDeck());
           }else{
               int cardUsId = Integer.valueOf(id);
           userDeckService.addForUsOwnDeck(cardService.getById(cardUsId));
                      out.addObject("mightSave",userDeckService.getUsOwnDeckCount());
           }
       }

             out.addObject("cardForDeck",userDeckService.getUsMainDeck());

        } else {
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }
        return out;
    }
}










