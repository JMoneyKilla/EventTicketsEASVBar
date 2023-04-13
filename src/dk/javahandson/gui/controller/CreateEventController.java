package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {

    @FXML
    private javafx.scene.control.Label lblWarning, lblTime, lblDate, lblSuccess;
    @FXML
    private AnchorPane createEventAnchorPane;
    @FXML
    private VBox ticketsVBox;

    @FXML
    private TextField txtFieldEventTitle, txtFieldLocation,
            txtFieldTimeStart, txtFieldTimeEnd, txtFieldNotes,
            txtFieldMaxVouchers, txtFieldMaxTickets;
    @FXML
    private DatePicker datePickerStart, datePickerEnd;

    EventModel modelEvent = EventModel.getInstance();

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
            int maxTickets = Integer.parseInt(txtFieldMaxVouchers.getText());
            if (!txtFieldEventTitle.getText().isBlank() || !txtFieldEventTitle.getText().isEmpty()
                || !txtFieldLocation.getText().isBlank() || !txtFieldLocation.getText().isEmpty() ||
            isTimeValid(txtFieldTimeStart.getText()) && isTimeValid(txtFieldTimeEnd.getText()) &&
            isDateValid()) {
                Event event = new Event(title, timeStart, timeEnd, location, notes, 0, 0, maxTickets, maxVouchers, dateStart, dateEnd);
                modelEvent.addEvent(event);
                modelEvent.addUserToEvent(event, modelEvent.getLoggedInUser());
                lblSuccess.setText("Event successfully created");
                clearTextFields();
            }
        }catch (NumberFormatException e){
            lblWarning.setText("Amount must be a number");
            System.out.println(isDateValid());
            System.out.println(isTimeValid(txtFieldTimeStart.getText()));
            System.out.println(isTimeValid(txtFieldTimeEnd.getText()));
        }
    }

    public void clickCancel(ActionEvent actionEvent) {
        clearTextFields();

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

    public void clearTextFields(){
        txtFieldEventTitle.clear();
        txtFieldLocation.clear();
        txtFieldTimeStart.clear();
        txtFieldTimeEnd.clear();
        txtFieldNotes.clear();
        datePickerStart.getEditor().clear();
        datePickerEnd.getEditor().clear();
        txtFieldMaxVouchers.clear();
        txtFieldMaxTickets.clear();
        lblDate.setText("");
        lblTime.setText("");
        lblWarning.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtFieldTimeStart.lengthProperty().addListener(new ChangeListener<Number>() {

            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                if (newValue.intValue() > oldValue.intValue()) {
                    if (txtFieldTimeStart.getText().length() >= 5) {
                        txtFieldTimeStart.setText(txtFieldTimeStart.getText().substring(0, 5));
                    }
                }
            }
        });
    }
}