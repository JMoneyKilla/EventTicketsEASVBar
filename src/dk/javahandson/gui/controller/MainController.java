package dk.javahandson.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView btn1, btn2, btn3;

    @FXML
    private Label lbl;
    @FXML
    private BorderPane borderPane;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
      btn1.setImage(new javafx.scene.image.Image("C:\\Users\\madsp\\billeder\\buttons\\btn1.png"));
      btn2.setImage(new javafx.scene.image.Image("C:\\Users\\madsp\\billeder\\buttons\\btn2.png"));
      btn3.setImage(new javafx.scene.image.Image("C:\\Users\\madsp\\billeder\\buttons\\btn3.png"));
    }

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
        FXMLLoader fxmlLoader =
                new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/EventsMenu.fxml"));
        System.out.println(fxmlLoader);
        borderPane.getChildren().remove(borderPane.getCenter()); //remove existing fxml from center.
        try {
            borderPane.setCenter(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
