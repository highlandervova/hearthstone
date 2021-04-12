package service;

import data.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

@Service
public class UserOnlineService {


    public List<User> usOnline = new ArrayList<User>();



    public void  add(User user) {
        usOnline.add(user);
        LocalDate date = LocalDate.now();
        String dateStr = new SimpleDateFormat("dd:MM:yyyy HH:mm").format(Calendar.getInstance().getTime());
    }




    public Collection<User> getOnlineFalse(HttpServletRequest req ) {
        Collection<User> out2 = new ArrayList();

        for (User user : usOnline) {
            User userFromSession = (User) req.getSession(false).getAttribute(user.getId());

           if ((req.getSession(false)!=null) &&( (User) req.getSession(false).getAttribute(user.getId())!=null)){
                out2.add(user);
            }
        }
        usOnline.removeAll(out2);
        return usOnline;
    }


     public void  remove(User userCur) {
        Iterator<User> userIterator = usOnline.iterator();
        while(userIterator.hasNext()){
           if( userIterator.next().getId() ==userCur.getId())
            userIterator.remove();
         }
     int i = usOnline.size();
    }


}
