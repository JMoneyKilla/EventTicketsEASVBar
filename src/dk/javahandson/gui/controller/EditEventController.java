package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import dk.javahandson.gui.model.UserModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.util.*;

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
    @FXML
    private javafx.scene.control.Label lblWarning, lblTime, lblDate, lblSuccess;
    EventModel eventModel = EventModel.getInstance();
    UserModel userModel = UserModel.getInstance();
    TicketModel ticketModel = TicketModel.getInstance();
    ObservableList<User> allUsers = FXCollections.observableArrayList();
    ObservableList<User> eventUsers = FXCollections.observableArrayList();
    public ObservableList<String> ticketTypes = FXCollections.observableArrayList();
    public ObservableList<String> voucherTypes = FXCollections.observableArrayList();

    public void clickAddUser(ActionEvent actionEvent) {
        User selectedUser = tableAllUsers.getSelectionModel().getSelectedItem();
        eventUsers.add(selectedUser);
    }

    public void clickSave(ActionEvent actionEvent) {
        try {
            String title = txtFieldEventTitle.getText();
            String location = txtFieldLocation.getText();
            String dateStart = getFormattedDateFromDatePicker(datePickerStart);
            String dateEnd = getFormattedDateFromDatePicker(datePickerEnd);
            String timeStart = txtFieldTimeStart.getText();
            String timeEnd = txtFieldTimeEnd.getText();
            String notes = txtFieldNotes.getText();
            int maxVouchers = Integer.parseInt(txtFieldMaxVouchers.getText());
            int maxTickets = Integer.parseInt(txtFieldMaxTickets.getText());
            if (!txtFieldEventTitle.getText().isBlank() || !txtFieldEventTitle.getText().isEmpty()
                    || !txtFieldLocation.getText().isBlank() || !txtFieldLocation.getText().isEmpty() ||
                    isTimeValid(txtFieldTimeStart.getText()) && isTimeValid(txtFieldTimeEnd.getText()) &&
                            isDateValid()) {
                Event event = new Event(eventModel.getMatchingEvent().getId(), title, timeStart, timeEnd, location, notes, 0, 0, maxTickets, maxVouchers, dateStart, dateEnd);
                eventModel.updateEditedEvent(event);
                for (User u: eventUsers) {
                    if(u.getId()!=eventModel.getLoggedInUser().getId())
                        userModel.addUserToEvent(eventModel.getMatchingEvent(), u);
                }
                if (ticketTypes.size()>0){
                    for (String s: ticketTypes) {
                        ticketModel.addTicketType(s);
                    }
                }
                if(voucherTypes.size()>0){
                    for (String s: voucherTypes) {
                        ticketModel.addVoucherType(s);
                    }
                }
                for (String s: voucherTypes) {
                    ticketModel.addVoucherType(s);
                }
                eventModel.fetchAllEvents();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Event successfully updated!");
                alert.showAndWait();
                eventModel.fetchAllEvents();
            }
        }catch (NumberFormatException e){
            lblWarning.setText("Amount must be a number");
            System.out.println(isDateValid());
            System.out.println(isTimeValid(txtFieldTimeStart.getText()));
            System.out.println(isTimeValid(txtFieldTimeEnd.getText()));
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }

    public void addVoucherType(ActionEvent actionEvent) {
        if(!textFieldVoucherType.getText().isEmpty() && ! textFieldVoucherType.getText().isBlank()){
            String ticketType = textFieldVoucherType.getText();
            voucherTypes.add(ticketType);
            listViewVoucherType.setItems(voucherTypes);
            textFieldVoucherType.clear();
        }
    }

    public void addTicketType(ActionEvent actionEvent) {
        if(!textFieldTicketType.getText().isEmpty() && ! textFieldTicketType.getText().isBlank()){
            String ticketType = textFieldTicketType.getText();
            ticketTypes.add(ticketType);
            listViewTicketType.setItems(ticketTypes);
            textFieldTicketType.clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<User> duplicateUsers = userModel.getUsersOnEvent(eventModel.getMatchingEvent());
        HashMap<String, User> uniqueUsers = new HashMap<>();
        for (User u : duplicateUsers) {
            uniqueUsers.put(u.getName(), u);
        }
        eventUsers.addAll(uniqueUsers.values());
        allUsers.addAll(userModel.getUsers());
        Set<String> names = new HashSet<>();
        for (User u : eventUsers) {
            names.add(u.getName());
        }
        Iterator<User> iterator = allUsers.iterator();
        while(iterator.hasNext()){
            User u = iterator.next();
            if(names.contains(u.getName()))
                iterator.remove();
        }

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

        txtFieldTimeStart.lengthProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (txtFieldTimeStart.getText().length() >= 5) {
                    txtFieldTimeStart.setText(txtFieldTimeStart.getText().substring(0, 5));
                }
            }
        });
    }
    public LocalDate getLocalDate (String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }
    private String getFormattedDateFromDatePicker(DatePicker datePicker) {
        String date = null;
        if(datePicker.getValue()!=null){
            //Get the selected date
            LocalDate selectedDate = datePicker.getValue();

            //Create DateTimeFormatter
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            date = selectedDate.format(formatter);
        }
        //Convert LocalDate to formatted String
        return date;
    }

    public boolean isDateValid() {
        if(datePickerStart.getEditor().getText().isEmpty() || datePickerEnd.getEditor().getText().isBlank()
                || datePickerEnd.getEditor().getText().isEmpty() || datePickerEnd.getEditor().getText().isBlank()){
            lblDate.setText("Please input start and end date");
            return false;
        }
        if (datePickerStart.getValue() != null || datePickerEnd.getValue() != null) {
            DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate startDate = LocalDate.parse(datePickerStart.getValue().toString(), dateFormat);
            LocalDate endDate = LocalDate.parse(datePickerEnd.getValue().toString(), dateFormat);
            LocalDate current = LocalDate.now();
            if (endDate.isBefore(startDate)) {
                lblDate.setText("End date can't be before start date");
                return false;
            }
            if (startDate.isBefore(current)) {
                lblDate.setText("Start date can't be before today");
                return false;
            }
        }
        else lblDate.setText("");
        return true;
    }

    private boolean isTimeValid(String str){
        if(txtFieldTimeStart.getText().isBlank() || txtFieldTimeStart.getText().isEmpty()){
            lblTime.setText("Please input time start");
            return false;
        }
        if(txtFieldTimeEnd.getText().isBlank() || txtFieldTimeEnd.getText().isEmpty()){
            lblTime.setText("Please input time end");
            return false;
        }
        //checks if time input looks like 00:00
        if(!str.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")){
            lblTime.setText("Please input valid time");
            return false;
        }
        else {
            lblTime.setText("");
            return true;}
    }

    public void clickDelete(ActionEvent actionEvent) {
        eventModel.deleteEvent(eventModel.getMatchingEvent());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Event successfully deleted!");
        alert.showAndWait();
        eventModel.fetchAllEvents();
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }
}
