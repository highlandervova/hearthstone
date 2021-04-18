package controller;


import data.Card;
import data.Race;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.CardService;
import service.RaceService;
import service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
@RequestMapping("hearthstone_war/adImage")
public class AdImageController {

    private final UserService userService;
    private final RaceService raceService;
    private final CardService cardService;
    @Autowired
    public AdImageController(UserService userService,
                             RaceService raceService,
                             CardService cardService) {
        this.userService = userService;
        this.raceService = raceService;
        this.cardService = cardService;
    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(HttpServletResponse resp, HttpServletRequest req,
                          @RequestParam(name = "id", required = true) String id) throws IOException {
        if (id.length()>10) {
            User u = userService.getById(id);
            Race r = raceService.getById(u.getRaceid());
            resp.setContentType(r.getPictype());
            byte[] imgBytes = r.getAvatar();
            resp.getOutputStream().write(imgBytes);

            resp.getOutputStream().flush(); //close();
        } else {

            Card c = cardService.getById(Integer.valueOf(id));
            resp.setContentType(c.getPictype());
            byte[] imgBytes = c.getPic();
            resp.getOutputStream().write(imgBytes);

            resp.getOutputStream().flush(); //close();

        }
        //String url = "data:image/png;base64,"+ Base64.getEncoder().encodeToString(imgBytes);
        //req.getSession().setAttribute("url",url);

    }
}