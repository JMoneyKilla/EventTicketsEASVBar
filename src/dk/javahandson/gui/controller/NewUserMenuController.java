package dk.javahandson.gui.controller;

import dk.javahandson.be.User;
import dk.javahandson.gui.model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.awt.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUserMenuController implements Initializable {

    @FXML
    private TextField txtFieldFullName, txtFieldEmail, txtFieldPassword;

    @FXML
    private PasswordField passwordField;
    @FXML
    private Label lblEmail, lblPassword, lblWarning;
    @FXML
    private Button btnShowPassword;

    private boolean showPassword = false;
    private boolean isEditTrue;

    UserModel model = new UserModel();

    public void clickSave(ActionEvent actionEvent) {
        if(isEditTrue == false){
        if(isEmailValid(txtFieldEmail.getText())== true
                && isInputValid() == true && showPassword == false &&
                !passwordField.getText().isEmpty() || !passwordField.getText().isBlank()
                && isPasswordValid(passwordField.getText()) == true)
        {
            model.createUser(txtFieldFullName.getText(), txtFieldEmail.getText(), passwordField.getText());
            clearEverything();
            lblWarning.setText("User successfully created");
        }
        else if(isUserNameAvailable(txtFieldEmail.getText())==false
                && showPassword == true && !txtFieldPassword.getText().isBlank()
                || !txtFieldPassword.getText().isEmpty()
                && isPasswordValid(txtFieldPassword.getText()) == true)
        {
            model.createUser(txtFieldFullName.getText(), txtFieldEmail.getText(), txtFieldPassword.getText());
            clearEverything();
            lblWarning.setText("User successfully created");
        }
        }
        if(isEditTrue == true){
        if(isInputValid() == true && isEmailValid(txtFieldEmail.getText()) == true &&
                showPassword == false && !passwordField.getText().isEmpty()
                || !passwordField.getText().isBlank()
                && isPasswordValid(passwordField.getText()) == true)
        {
            model.getSelectedUser().setName(txtFieldFullName.getText());
            model.getSelectedUser().setEmail(txtFieldEmail.getText());
            model.updateUser(model.getSelectedUser());
            model.updatePassword(model.getSelectedUser(), passwordField.getText());
            clearEverything();
            System.out.println(model.getSelectedUser().getId());
        }
            else if(isInputValid() == true && isEmailValid(txtFieldEmail.getText()) == true &&
                showPassword == true && !txtFieldPassword.getText().isBlank()
                || !txtFieldPassword.getText().isEmpty() && isPasswordValid(txtFieldPassword.getText()) == true)
            {
                model.getSelectedUser().setName(txtFieldFullName.getText());
                model.getSelectedUser().setEmail(txtFieldEmail.getText());
                model.updateUser(model.getSelectedUser());
                model.updatePassword(model.getSelectedUser(), txtFieldPassword.getText());
                clearEverything();
                lblWarning.setText("User successfully updated");
            }
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldPassword.setVisible(false);
        if(model.getSelectedUser()!=null) {
            txtFieldFullName.setText(""+model.getSelectedUser().getName());
            txtFieldEmail.setText(""+model.getSelectedUser().getEmail());
            passwordField.setText(""+model.getPasswordFromUser(model.getSelectedUser()));
            isEditTrue = true;
        }
        else isEditTrue = false;

    }

    public boolean isUserNameAvailable(String str)
    {

        List<User> users = model.getUsers();
        for(User u : users)
        {
            if(str.toLowerCase().equals(u.getEmail().toLowerCase()))
            {
                lblEmail.setText("This email is already in use");
                return false;
            }
        }
        return true;
    }



    public void clickShowPassword(ActionEvent actionEvent) {
        String password = "";
        if(showPassword == false)
        {
            showPassword = true;
            btnShowPassword.setText("Hide Password");
            password = passwordField.getText();
            txtFieldPassword.setText(password);
            passwordField.setVisible(false);
            txtFieldPassword.setVisible(true);

        }
        else if (showPassword == true)
        {
            showPassword = false;
            btnShowPassword.setText("Show Password");
            password = txtFieldPassword.getText();
            passwordField.setText(password);
            txtFieldPassword.setVisible(false);
            passwordField.setVisible(true);

        }
    }

    public void clickCheckAvailability(ActionEvent actionEvent) {
        if(isEmailValid(txtFieldEmail.getText())==true){
        if(isUserNameAvailable(txtFieldEmail.getText())==true)
        {
            lblEmail.setText("Email is available");
        }
        else if(isUserNameAvailable(txtFieldEmail.getText())==false)
        {
            lblEmail.setText("Email is already in use");
        }
        }
        else if(isEmailValid(txtFieldEmail.getText())==false)
        {
            lblEmail.setText("Invalid email");
        }
    }

    private boolean isEmailValid(String str)
    {
        String email_regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(str.matches(email_regex))
        return true;
        else{
            lblEmail.setText("Invalid email");
            return false;}
    }

    private boolean isPasswordValid(String str)
    {
        Pattern special = Pattern.compile ("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasSpecial = special.matcher(str);
        if(str.length()<8 || str.length()>20)
        {
            lblPassword.setText("Password must contain 8-20 characters");
            return false;
        }
        else if(hasSpecial.find())
        {
            lblPassword.setText("Password must not contain special characters");
            return false;
        }
        lblPassword.setText("");
        return true;
    }

    public boolean isInputValid()
    {
        if(!txtFieldEmail.getText().isBlank() || !txtFieldEmail.getText().isEmpty() ||
                !txtFieldFullName.getText().isBlank() || !txtFieldFullName.getText().isEmpty())
        {
            return true;
        }
        else if(!txtFieldEmail.getText().isBlank() || !txtFieldEmail.getText().isEmpty() ||
                !txtFieldFullName.getText().isBlank() || !txtFieldFullName.getText().isEmpty())
        {
            return true;
        }
        else return false;
    }

    public void clearEverything()
    {
        txtFieldFullName.clear();
        txtFieldPassword.clear();
        passwordField.clear();
        txtFieldEmail.clear();
        passwordField.setVisible(true);
        txtFieldPassword.setVisible(false);
        lblEmail.setText("");
        lblPassword.setText("");
    }
}
