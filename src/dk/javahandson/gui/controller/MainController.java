package dk.javahandson.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MainController {
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/EventsMenu.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/CreateEventMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
