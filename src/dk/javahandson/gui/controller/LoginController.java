package dk.javahandson.gui.controller;

import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.UserModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXPasswordField;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    UserModel userModel;
    EventModel eventModel;
    @FXML
    private MFXPasswordField textFieldPassword;
    @FXML
    private MFXTextField textFieldEmail;
    @FXML
    private MFXButton buttonLogin;
    @FXML
    private Label lblWarning;

    /*
    validates login information and sets logged in user on the event model
     */
    public void clickLogin(ActionEvent actionEvent) {
        String email = textFieldEmail.getText();
        String password = textFieldPassword.getText();
        if(userModel.validateLogin(email, password)) {
            Parent root;
            eventModel.setLoggedInUser(userModel.loginUser(email));
            eventModel.fetchAllEvents();
            try { FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/Main.fxml"));
                root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Node n = (Node) actionEvent.getSource();
            Stage stage2 = (Stage) n.getScene().getWindow();
            stage2.close();
        }
        else lblWarning.setText("Incorrect login");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = UserModel.getInstance();
        eventModel = EventModel.getInstance();
    }
}
