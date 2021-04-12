package controller;

import data.User;
import enums.RedirectPath;
import enums.RequestParameter;
import enums.SessionAttribute;
import enums.UserStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.EditUserService;
import service.RaceService;
import service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static enums.SessionAttribute.AUTHENTICATED;

@Controller
@RequestMapping("editUser")
public class EditUserController {

    private final UserService userService;
    private final RaceService raceService;
    private final EditUserService editUserService;

    public EditUserController(UserService userService, EditUserService editUserService, RaceService raceService) {

        this.raceService=raceService;
        this.userService = userService;
        this.editUserService = editUserService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView editUserGet(HttpServletRequest req) {
        ModelAndView out = new ModelAndView("editUser");
        out.addObject("title", "Edit User Account");
        out.addObject("pathMain", RedirectPath.MAIN_PAGE.getValue());
        User user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());
        out.addObject("allRace", raceService.getRaces());
        int raceId = user.getRaceid();
        out.addObject("raceIdUser", raceId);
        out.addObject("otherRaces", raceService.getOtherRaces(raceId));
        return out;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView editUserPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ModelAndView out = new ModelAndView("editUser");
        out.addObject("title", "Edit User Account");
        String pathMain = RedirectPath.MAIN_REDIRECT.getValue();
        out.addObject("pathMain", pathMain);
        String login = req.getParameter(RequestParameter.LOGIN.getValue());
        String curPass = req.getParameter("curPass");
        String pass1 = req.getParameter(RequestParameter.PASS1.getValue());
        String pass2 = req.getParameter(RequestParameter.PASS2.getValue());
        int raceId = Integer.parseInt(req.getParameter(RequestParameter.RACE.getValue()));
        String name = req.getParameter(RequestParameter.NAME.getValue());
        String lastname = req.getParameter(RequestParameter.LASTNAME.getValue());
        String email = req.getParameter(RequestParameter.EMAIL.getValue());

        HttpSession session = req.getSession(false);
        if (session != null) {
            User user = (User) session.getAttribute(SessionAttribute.AUTHENTICATED.getValue());
            User editedUser = new User( );
            editedUser.setId(user.getId());
            editedUser.setLogin(login);
            editedUser.setPass(userService.md5Apache(pass1));
            editedUser.setName(name);
            editedUser.setLastname(lastname);
            editedUser.setRaceid(raceId);
            editedUser.setEmail(email);


            UserStatus status = editUserService.checkPasswordFields(user, curPass, pass1, pass2);
            if (editUserService.editUser(user, editedUser, status)) {
                if (status.equals(UserStatus.CHANGES_SAVED)) { //that's basically hack to update authorized User in session without query to DB
                    editedUser.setPass(user.getPass());
                }
                req.getSession().setAttribute(SessionAttribute.AUTHENTICATED.getValue(), editedUser);
                status=UserStatus.CHANGES_SAVED;
            }
            out.addObject("status", status.getValue());
            user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());
            out.addObject("allRace", raceService.getRaces());
            int raceId2 = user.getRaceid();
            out.addObject("raceIdUser", user.getRaceid());
            out.addObject("otherRaces", raceService.getOtherRaces(raceId2));
            return out;

        } else {
            resp.sendRedirect(RedirectPath.LOGIN_PAGE.getValue());
        }
        User user = (User) req.getSession().getAttribute(AUTHENTICATED.getValue());
        out.addObject("allRace", raceService.getRaces());

        out.addObject("raceIdUser", user.getRaceid());
        out.addObject("otherRaces", raceService.getOtherRaces(raceId));
        return out;
    }
}
