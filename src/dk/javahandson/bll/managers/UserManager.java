package dk.javahandson.bll.managers;

import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import dk.javahandson.dal.DataAccessFacade;

import java.sql.SQLException;

public class UserManager {
    private DataAccessFacade userDAO = new DataAccessFacade();

    public void createUser(User user) throws SQLException {
        userDAO.createUser(user);
    }
    public boolean deleteUser(User user) throws SQLException {
        return userDAO.deleteUser(user);
    }
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }
    public void addUserToEvent(Event event, User user) throws SQLException {
        userDAO.addUserToEvent(event, user);
    }
}
