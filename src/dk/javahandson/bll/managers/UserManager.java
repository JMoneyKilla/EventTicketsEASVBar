package dk.javahandson.bll.managers;

import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import dk.javahandson.dal.DataAccessFacade;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class UserManager {
    private DataAccessFacade userDAO = new DataAccessFacade();

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void createLogin(String username, String password) throws SQLException {
        userDAO.createLogin(username, password);
    }
    public void createUser(User user) throws SQLException {
        userDAO.createUser(user);
    }
    public boolean deleteUser(User user) throws SQLException {
        return userDAO.deleteUser(user);
    }

    public boolean deleteUserFromLogin(User user) throws SQLException {
        return userDAO.deleteUserFromLogin(user);
    }

    public boolean deleteUserFromUserEvent(User user) throws SQLException {
        return userDAO.deleteUserFromUserEvent(user);
    }

    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }
    public void addUserToEvent(Event event, User user) throws SQLException {
        userDAO.addUserToEvent(event, user);
    }

    public String getPasswordFromUser(User user) throws SQLException {
        return userDAO.getPasswordFromUser(user);
    }
    public void updateLogin(User user, String password) throws SQLException {
        userDAO.updateLogin(user, password);
    }
    public User loginUser(String email) throws SQLException{
        return userDAO.loginUser(email);
    }
    public boolean validateLogin(String email, String password) throws SQLException {
        return userDAO.validateLogin(email, password);
    }
    public List<User> getUsersOnEvent(Event event) throws SQLException {
        return userDAO.getUsersOnEvent(event);
    }
    }
