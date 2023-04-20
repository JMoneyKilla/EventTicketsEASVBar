package dk.javahandson.gui.controller;

import dk.javahandson.be.User;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class EditEventController implements Initializable {
    @FXML
    private TextField txtFieldEventTitle, txtFieldLocation, txtFieldTimeStart, txtFieldTimeEnd, txtFieldNotes,
            txtFieldMaxTickets, textFieldTicketType, txtFieldMaxVouchers, textFieldVoucherType ;
    @FXML
    private ListView listViewTicketType, listViewVoucherType;
    @FXML
    private TableView<User> tableAllUsers, tableEventCoordinators;
    @FXML
    private TableColumn<User, String> columnAllUsers, columnEventCoordinators;
    @FXML
    private DatePicker datePickerStart, datePickerEnd;
    @FXML
    private AnchorPane createEventAnchorPane;
    EventModel eventModel = EventModel.getInstance();
    UserModel userModel = UserModel.getInstance();
    ObservableList<User> allUsers = FXCollections.observableArrayList();
    ObservableList<User> eventUsers = FXCollections.observableArrayList();

    public void clickAddUser(ActionEvent actionEvent) {
    }

    public void clickSave(ActionEvent actionEvent) {
    }

    public void clickCancel(ActionEvent actionEvent) {
    }

    public void addVoucherType(ActionEvent actionEvent) {
    }

    public void addTicketType(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        allUsers = userModel.getUsers();
        eventUsers = userModel.getUsersOnEvent(eventModel.getMatchingEvent());
        columnAllUsers.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnEventCoordinators.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableAllUsers.setItems(allUsers);
        tableEventCoordinators.setItems(eventUsers);
        txtFieldEventTitle.setText(eventModel.getMatchingEvent().getName());
        txtFieldLocation.setText(eventModel.getMatchingEvent().getLocation());
        txtFieldNotes.setText(eventModel.getMatchingEvent().getNotes());
        txtFieldMaxTickets.setText(String.valueOf(eventModel.getMatchingEvent().getTotalTickets()));
        txtFieldMaxVouchers.setText(String.valueOf(eventModel.getMatchingEvent().getTotalVouchers()));
        txtFieldTimeStart.setText(eventModel.getMatchingEvent().getStartTime());
        txtFieldTimeEnd.setText(eventModel.getMatchingEvent().getEndTime());
        datePickerStart.setValue(getLocalDate(eventModel.getMatchingEvent().getStartDate()));
        datePickerEnd.setValue(getLocalDate(eventModel.getMatchingEvent().getEndDate()));

    }
    public LocalDate getLocalDate (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
}
