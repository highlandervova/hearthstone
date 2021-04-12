package service;

import data.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserOnlineService {


    private static List<User> usOnline = new ArrayList<>();


    public static void add(User user) {
        if (!usOnline.contains(user)) {
            usOnline.add(user);
        }
    }


    public Collection<User> getOnlineFalse(HttpServletRequest req) {
        Collection<User> out2 = new ArrayList<>();

        for (User user : usOnline) {
            User userFromSession = (User) req.getSession(false).getAttribute(user.getId());

            if ((req.getSession(false) != null) && ((User) req.getSession(false).getAttribute(user.getId()) != null)) {
                out2.add(user);
            }
        }
        usOnline.removeAll(out2);
        return usOnline;
    }


    public static void remove(User userCur) {
        usOnline.remove(userCur);
    }

    public static int getUsOnlineCount() {
        return usOnline.size();
    }

    public static List<User> getUsOnline() {
        return usOnline;
    }
}
