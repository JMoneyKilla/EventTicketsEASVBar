package dk.javahandson.gui.controller;

import dk.javahandson.be.User;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewUserMenuController implements Initializable {

    @FXML
    private Label labelUserType;
    @FXML
    private Button buttonCancel;
    @FXML
    private ChoiceBox comboUserType;
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

    String type;

    UserModel model = UserModel.getInstance();
    EventModel eventModel = EventModel.getInstance();

    /*
    Validates provided data and creates new User if data is valid
     */
    public void clickSave(ActionEvent actionEvent) {
        if(!isEditTrue){
        if(isEmailValid(txtFieldEmail.getText())
                && isInputValid() && !showPassword &&
                !passwordField.getText().isEmpty() || !passwordField.getText().isBlank()
                && isPasswordValid(passwordField.getText()))
        {
            model.createUser(new User(txtFieldFullName.getText(), type, txtFieldEmail.getText()), passwordField.getText());
            clearEverything();
            model.fetchAllUsers();
            lblWarning.setText("User successfully created");
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
        else if(!isUserNameAvailable(txtFieldEmail.getText())
                && showPassword && !txtFieldPassword.getText().isBlank()
                || !txtFieldPassword.getText().isEmpty()
                && isPasswordValid(txtFieldPassword.getText()))
        {
            model.createUser(new User(txtFieldFullName.getText(), type, txtFieldEmail.getText()), passwordField.getText());
            clearEverything();
            model.fetchAllUsers();
            lblWarning.setText("User successfully created");
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
        }
        }
        if(isEditTrue){
        if(isInputValid() && isEmailValid(txtFieldEmail.getText()) &&
                !showPassword && !passwordField.getText().isEmpty()
                || !passwordField.getText().isBlank()
                && isPasswordValid(passwordField.getText()))
        {
            User temp = new User(model.getSelectedUser().getId(), txtFieldFullName.getText(), comboUserType.getSelectionModel().getSelectedItem().toString(), txtFieldEmail.getText());
            model.updateUser(temp);
            model.updateLogin(temp, passwordField.getText());
            model.fetchAllUsers();
            if(!eventModel.getLoggedInUser().getType().equals("EventCoordinator")){
                clearEverything();
                Node n = (Node) actionEvent.getSource();
                Stage stage = (Stage) n.getScene().getWindow();
                stage.close();
                model.resetSelectedUser();
            }
        }
            else if(isInputValid() && isEmailValid(txtFieldEmail.getText()) &&
                showPassword && !txtFieldPassword.getText().isBlank()
                || !txtFieldPassword.getText().isEmpty() && isPasswordValid(passwordField.getText()))
            {
                User temp = new User(model.getSelectedUser().getId(), txtFieldFullName.getText(), comboUserType.getSelectionModel().getSelectedItem().toString(), txtFieldEmail.getText());
                model.updateUser(temp);
                model.updateLogin(temp, passwordField.getText());
                clearEverything();
                model.fetchAllUsers();
                if(!eventModel.getLoggedInUser().getType().equals("EventCoordinator")){
                    clearEverything();
                    Node n = (Node) actionEvent.getSource();
                    Stage stage = (Stage) n.getScene().getWindow();
                    stage.close();
                    model.resetSelectedUser();
                }

            }
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
        model.resetSelectedUser();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldPassword.setVisible(false);
        ObservableList<String> userTypes = FXCollections.observableArrayList();
        userTypes.addAll("Admin", "EventCoordinator");
        comboUserType.setItems(userTypes);
        comboUserType.valueProperty().addListener(
                (observable, oldValue, newValue) -> type = newValue.toString());
        if(eventModel.getLoggedInUser().getType().equals("EventCoordinator")){
            comboUserType.setVisible(false);
            buttonCancel.setVisible(false);
            buttonCancel.setDisable(true);
            labelUserType.setVisible(false);
        }
        if(model.getSelectedUser()!=null) {
            type = model.getSelectedUser().getType();
            comboUserType.setValue(type);
            txtFieldFullName.setText(""+model.getSelectedUser().getName());
            txtFieldEmail.setText(""+model.getSelectedUser().getEmail());
            passwordField.setText(""+model.getPasswordFromUser(model.getSelectedUser()));
            isEditTrue = true;
        }
        else isEditTrue = false;

    }

    /*
    Checks Database to ensure unique username is being created
     */
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
        if(!showPassword)
        {
            showPassword = true;
            btnShowPassword.setText("Hide Password");
            password = passwordField.getText();
            txtFieldPassword.setText(password);
            passwordField.setVisible(false);
            txtFieldPassword.setVisible(true);

        }
        else if (showPassword)
        {
            showPassword = false;
            btnShowPassword.setText("Show Password");
            password = txtFieldPassword.getText();
            passwordField.setText(password);
            txtFieldPassword.setVisible(false);
            passwordField.setVisible(true);

        }
    }

    /*
    Checks availability of username
     */
    public void clickCheckAvailability(ActionEvent actionEvent) {
        if(isEmailValid(txtFieldEmail.getText())){
        if(isUserNameAvailable(txtFieldEmail.getText()))
        {
            lblEmail.setText("Email is available");
        }
        else if(!isUserNameAvailable(txtFieldEmail.getText()))
        {
            lblEmail.setText("Email is already in use");
        }
        }
        else if(!isEmailValid(txtFieldEmail.getText()))
        {
            lblEmail.setText("Invalid email");
        }
    }

    /*
    Checks if email entered is valid email
     */
    private boolean isEmailValid(String str)
    {
        String email_regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        if(str.matches(email_regex))
        return true;
        else{
            lblEmail.setText("Invalid email");
            return false;}
    }

    /*
    Checks for valid password input
     */
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

    /*
    Checks all data, returns true if all data is valid
     */
    public boolean isInputValid()
    {
        if(!txtFieldEmail.getText().isBlank() || !txtFieldEmail.getText().isEmpty() ||
                !txtFieldFullName.getText().isBlank() || !txtFieldFullName.getText().isEmpty())
        {
            return true;
        }
        else return !txtFieldEmail.getText().isBlank() || !txtFieldEmail.getText().isEmpty() ||
                !txtFieldFullName.getText().isBlank() || !txtFieldFullName.getText().isEmpty();
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
