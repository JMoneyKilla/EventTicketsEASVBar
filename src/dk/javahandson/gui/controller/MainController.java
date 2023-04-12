package dk.javahandson.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView imgMyEvents, imgCreateEvent, imgSellTicket, imgManageUsers, imgCreateMoreTickets, imageEASV;
    @FXML
    private Label lbl;
    @FXML
    private BorderPane borderPane;

    @FXML
    private void clickBtn(ActionEvent actionEvent) {
        lbl.setText("It's Alive!!");
    }

    @FXML
    private void clickAllEvents(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/AdminEventsMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickCreateEvent(MouseEvent mouseEvent) {
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

    public void clickMyEvents(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/EventsMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void clickSellTicket(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/SellTicket.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        imgMyEvents.setImage(new Image("/Pictures/myEvents.png"));
        imgCreateEvent.setImage(new Image("/Pictures/createEvent.png"));
        imgSellTicket.setImage(new Image("/Pictures/sellTicket.png"));
        imgManageUsers.setImage(new Image("Pictures/manageUsers.png"));
        imgCreateMoreTickets.setImage(new Image("/Pictures/createMoreTickets.png"));
        imageEASV.setImage(new Image("/Pictures/easv.png"));
    }


    public void clickCreateMoreTickets(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/CreateMoreTickets.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clickManageUsers(MouseEvent mouseEvent) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/UserListMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
