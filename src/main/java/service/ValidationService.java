package service;

import antlr.StringUtils;
import dao.RaceDao;
import data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationService {


    private final UserService userService;


    @Autowired
    public ValidationService(
            final   UserService userService) {this.userService = userService;    }





    public boolean validateRegistration(String login, String pass1, String pass2) {


        String log=userService.getByLoginStr(login);


        if (log instanceof String) {
          return false;
      } else {

          return (pass1 != null && pass1.equals(pass2) && login != null);
        }

    }


    public boolean validateAuthentication(String login, String pass) {
        User u = userService.getByLogin(login);
        if ( u!= null){
          return userService.checkUserPassword(u, pass);
        } return false;
    }
}