package dk.javahandson.gui.controller;

import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.UserModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private MFXButton buttonManage;
    @FXML
    private ImageView  imageEASV;
    @FXML
    private Label lblWelcome;
    @FXML
    private BorderPane borderPane;

    EventModel eventModel = EventModel.getInstance();
    UserModel userModel = UserModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imageEASV.setImage(new Image("/Pictures/easv.png"));
        lblWelcome.setText("Welcome back, "+eventModel.getLoggedInUser().getName()+"!");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/EventsMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(eventModel.getLoggedInUser().getType().equals("EventCoordinator"))
            buttonManage.setText("User Info");
    }

    public void clickMyEvents(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/EventsMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void clickManageUsers(ActionEvent actionEvent) {
     if(eventModel.getLoggedInUser().getType().equals("Admin")){
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/UserListMenu.fxml"));
       System.out.println(fxmlLoader);
       borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
       try {
           borderPane.setCenter(fxmlLoader.load());
       } catch (IOException e) {
           e.printStackTrace();
       }
     }
    if(eventModel.getLoggedInUser().getType().equals("EventCoordinator")){
        userModel.setSelectedUser(eventModel.getLoggedInUser());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/NewUserMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    }

    public void clickNewEvent(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/CreateEventMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickSellTickets(ActionEvent actionEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/SellTicket.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
