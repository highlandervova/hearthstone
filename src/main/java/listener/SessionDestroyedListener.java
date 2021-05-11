package listener;

import data.User;
import enums.SessionAttribute;
import service.UserDeckService;
import service.UserOnlineService;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionDestroyedListener implements HttpSessionListener {
    public void sessionDestroyed(HttpSessionEvent se) {
        UserOnlineService.remove((User) se.getSession().getAttribute(SessionAttribute.AUTHENTICATED.getValue()));
       // UserDeckService.remove((User) ((User) se.getSession().getAttribute(SessionAttribute.AUTHENTICATED.getValue())).getId());
    }
}
