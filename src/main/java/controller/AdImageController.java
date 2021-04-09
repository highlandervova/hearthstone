package controller;


import data.Race;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    @Autowired
    public AdImageController(UserService userService,
                             RaceService raceService) {
        this.userService = userService;
        this.raceService = raceService;
    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(HttpServletResponse resp, HttpServletRequest req,
                          @RequestParam(name = "id", required = true) String id) throws IOException {

        User u = userService.getById(id);
        Race r = raceService.getById(u.getRaceid());
        resp.setContentType(r.getPictype());
        byte[] imgBytes = r.getAvatar();
        resp.getOutputStream().write(imgBytes);

        resp.getOutputStream().flush(); //close();

        //String url = "data:image/png;base64,"+ Base64.getEncoder().encodeToString(imgBytes);
        //req.getSession().setAttribute("url",url);

    }
}