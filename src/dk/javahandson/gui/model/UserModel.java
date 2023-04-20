package dk.javahandson.gui.model;

import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import dk.javahandson.bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class UserModel {
    private static UserModel instance;
    private final ObservableList<User> users;
    ManagerFacade bll = new ManagerFacade();
    private static User selectedUser;

    public static UserModel getInstance(){
        if(instance == null)
            instance = new UserModel();
        return instance;
    }

    private UserModel() {
        users = FXCollections.observableArrayList();
        fetchAllUsers();
    }

    public void fetchAllUsers() {
        users.clear();
        try {
            users.addAll(bll.getAllUsers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<User> getUsers()
    {
        return users;
    }


    public void createUser(User user, String password)
    {
        try {
            bll.createUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            bll.createLogin(user.getEmail(), password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(User selectedUser) {
        try {
            bll.deleteUserFromLogin(selectedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            bll.deleteUserFromUserEvent(selectedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            bll.deleteUser(selectedUser);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void setSelectedUser(User user)
    {
        this.selectedUser=user;
    }

    public User getSelectedUser() {
        return selectedUser;
    }

    public void resetSelectedUser(){
        selectedUser=null;
    }
    public String getPasswordFromUser(User user)
    {
        try {
            return bll.getPasswordFromUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser(User user)
    {
        try {
            bll.updateUser(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateLogin(User user, String password)
    {
        try {
            bll.updateLogin(user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean validateLogin(String email, String password){
        try {
            return bll.validateLogin(email, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public User loginUser(String email){
        try {
            return bll.loginUser(email);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUserToEvent(Event event, User user){
        try {
            bll.addUserToEvent(event, user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}