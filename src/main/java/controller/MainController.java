package controller;


import data.User;
import enums.RedirectPath;
import enums.RequestParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.events.SessionDestroyedEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.*;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionListener;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static enums.SessionAttribute.AUTHENTICATED;
import static enums.SessionAttribute.valueOf;

@Controller
@RequestMapping("main")


public class MainController  implements HttpSessionListener
        //, ServerContextListener
        {

    private final RaceService raceService;
    private final UserService userService;
    private final UserOnlineService userOnlineService;
    private final UserDeckService userDeckService;

    //private final TimerTask timerTask;


    @Autowired
    public MainController(
            final RaceService raceService,
            final UserService userService,
            final UserOnlineService userOnlineService,
            final UserDeckService userDeckService
   )

    {
        this.raceService = raceService;
        this.userService = userService;
        this.userOnlineService = userOnlineService;
        this.userDeckService = userDeckService;


    }


    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView mainGet(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView out = new ModelAndView("main");
        userDeckService.createUsMainDeck();
        userDeckService.clearUsOwnDeck();
        out.addObject("title", "HearthStone");
        out.addObject("pathEditUser", RedirectPath.EDIT_USER.getValue());
        out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
        // String pathReg = RedirectPath.REG_PAGE.getValue();
        out.addObject("pathReg", RedirectPath.REG_PAGE.getValue());
        out.addObject("pathHead", RedirectPath.HEAD_PATH.getValue());
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());
        out.addObject("pathAuth", RedirectPath.LOGIN_PAGE.getValue());
        LocalDate date = LocalDate.now();
              String dateStr = new SimpleDateFormat("dd:MM:yyyy HH:mm").format(Calendar.getInstance().getTime());
        out.addObject("dateNow", dateStr);
        out.addObject("pathCreateDeck", RedirectPath.DECKCREATE_PAGE.getValue());
        out.addObject("yesOnlineUser", String.valueOf(userOnlineService.getUsOnlineCount()));
        out.addObject("deck", 1);
        if ((req.getSession().getAttribute(AUTHENTICATED.getValue())) != null) {
            User user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());
            out.addObject("deck", userService.getByDeck(user));
            out.addObject("lvl", user.getLvl());
            out.addObject("gold", user.getGold());
//width="120" height="80"
            out.addObject("idUser", user.getId());
            out.addObject("Login", user.getLogin());
        }

        if (req.getParameter(RequestParameter.LOGOFF.getValue()) != null) {
            User user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());
            userOnlineService.remove(user);
            req.getSession().invalidate();
            int i = userOnlineService.getUsOnlineCount();
            out.addObject("Login", "");
            out.addObject("yesOnlineUser", String.valueOf(i));
        }
                 out.addObject("userOnline", userOnlineService.getUsOnline());

            return out;
        }
    }
