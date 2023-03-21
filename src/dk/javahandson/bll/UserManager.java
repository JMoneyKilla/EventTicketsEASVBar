package dk.javahandson.bll;


import dk.javahandson.be.User;
import dk.javahandson.dal.DataAccessFacade;

import java.sql.SQLException;

public class UserManager {
    DataAccessFacade dataAccessFacade = new DataAccessFacade();

    public void createUser(User user) {
        try {
            dataAccessFacade.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateUser(User user) {
        try {
            dataAccessFacade.updateUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteUser(User user) {
        try {
            dataAccessFacade.deleteUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
