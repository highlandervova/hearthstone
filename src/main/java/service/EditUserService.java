package service;

import data.User;
import enums.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EditUserService {

    @Autowired
    UserService userService;

    public UserStatus checkPasswordFields(User curUser, String enteredCurPass, String pass1, String pass2) {
        if (!enteredCurPass.equals("")) {
            if (!pass1.equals("") && pass1.equals(pass2)) { //New entered passwords match)
                if (userService.checkUserPassword(curUser, enteredCurPass)) {
                    return UserStatus.CHANGES_WITH_PASSWORD_OK;
                }
                return UserStatus.PASSWORD_INCORRECT;
            }
            if (pass1.equals("") && pass2.equals("")) {
                if (userService.checkUserPassword(curUser, enteredCurPass)) {
                    return UserStatus.CHANGES_SAVED;
                }
                return UserStatus.PASSWORD_INCORRECT;
            }
            return UserStatus.PASSWORD_FIELDS_MISMATCH;
        }
        return UserStatus.ENTER_PASSWORD;

    }

    public boolean editUser(User curUser, User editedUser, UserStatus updateType) {
        switch (updateType) {
            case CHANGES_WITH_PASSWORD_OK:

                User updateUser = new User();
                updateUser.setId(curUser.getId());
                updateUser.setLogin(editedUser.getLogin());
                updateUser.setPass(editedUser.getPass());
                updateUser.setRaceid(editedUser.getRaceid());
                updateUser.setName(editedUser.getName());
                updateUser.setEmail(editedUser.getEmail());
                updateUser.setLastname(editedUser.getLastname());
                updateUser.setLvl(curUser.getLvl());
                updateUser.setDeckdate(curUser.getDeckdate());
                updateUser.setPoints(curUser.getPoints());
                updateUser.setMoney(curUser.getMoney());
                updateUser.setGold(curUser.getGold());
                updateUser.setDeck(curUser.getDeck());
                updateUser.setCreationdate(curUser.getCreationdate());


                return userService.updateUserWithPassword(updateUser,curUser);
            case CHANGES_SAVED:
            User updateWithoutPassUser = new User();
                updateWithoutPassUser.setId(curUser.getId());
                updateWithoutPassUser.setLogin(editedUser.getLogin());
                updateWithoutPassUser.setPass(curUser.getPass());
                updateWithoutPassUser.setRaceid(editedUser.getRaceid());
                updateWithoutPassUser.setName(editedUser.getName());
                updateWithoutPassUser.setEmail(editedUser.getEmail());
                updateWithoutPassUser.setLastname(editedUser.getLastname());
                updateWithoutPassUser.setLvl(curUser.getLvl());
                updateWithoutPassUser.setDeck(curUser.getDeck());
                updateWithoutPassUser.setDeckdate(curUser.getDeckdate());
                updateWithoutPassUser.setPoints(curUser.getPoints());
                updateWithoutPassUser.setMoney(curUser.getMoney());
                updateWithoutPassUser.setGold(curUser.getGold());
                updateWithoutPassUser.setCreationdate(curUser.getCreationdate());
                return userService.updateUserWithoutPassword( updateWithoutPassUser,curUser);
            default:
                return false;
        }
    }
}
