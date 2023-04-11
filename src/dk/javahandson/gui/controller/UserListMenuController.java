package dk.javahandson.gui.controller;

import dk.javahandson.be.User;
import dk.javahandson.gui.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserListMenuController implements Initializable {

    @FXML
    private TableView<User> tableViewUsers;
    @FXML
    private TableColumn<User, String> tableColumnName, tableColumnEmail;
    @FXML
    private Label lblWarning;
    UserModel model = UserModel.getInstance();
    public void clickDeleteUser(ActionEvent actionEvent) {
        if (tableViewUsers.getSelectionModel().getSelectedItem() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove " + tableViewUsers.getSelectionModel().getSelectedItem().getName() + "?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.YES) {
                model.deleteUser(tableViewUsers.getSelectionModel().getSelectedItem());
                model.fetchAllUsers();
            }
        }
    }

    public void clickCreateUser(ActionEvent actionEvent) {
        if(tableViewUsers.getSelectionModel().getSelectedItem()!=null)
        {
            lblWarning.setText("Please deselect a user");
        }
        else{
            lblWarning.setText("");
        Node n = (Node) actionEvent.getSource();
        Window stage = n.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("dk/javahandson/gui/view/NewUserMenu.fxml"));
            Stage createNewUser = new Stage();
            createNewUser.setScene(new Scene(root));
            createNewUser.setTitle("Create New User");
            createNewUser.initModality(Modality.WINDOW_MODAL);
            createNewUser.centerOnScreen();
            createNewUser.initOwner(stage);
            createNewUser.show();


        } catch (IOException e) {
            e.printStackTrace();
        }}
    }

    public void clickEditUser(ActionEvent actionEvent) {
        if(tableViewUsers.getSelectionModel().getSelectedItem()!=null){
            lblWarning.setText("");
            model.setSelectedUser(tableViewUsers.getSelectionModel().getSelectedItem());
        Node n = (Node) actionEvent.getSource();
        Window stage = n.getScene().getWindow();
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("dk/javahandson/gui/view/NewUserMenu.fxml"));
            Stage editUser = new Stage();
            editUser.setScene(new Scene(root));
            editUser.setTitle("Edit User");
            editUser.initModality(Modality.WINDOW_MODAL);
            editUser.centerOnScreen();
            editUser.initOwner(stage);
            editUser.show();


        } catch (IOException e) {
            e.printStackTrace();
        }}
        else{
            lblWarning.setText("Please select a user");
        }

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableViewUsers.setItems(model.getUsers());
    }

    public void clickAnchorPane(MouseEvent mouseEvent) {
        tableViewUsers.getSelectionModel().clearSelection();
    }
}
