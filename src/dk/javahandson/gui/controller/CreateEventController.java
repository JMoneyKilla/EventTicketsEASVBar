package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import java.awt.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {

    @FXML
    private javafx.scene.control.Label lblWarning;
    @FXML
    private AnchorPane createEventAnchorPane;
    @FXML
    private VBox ticketsVBox;
    @FXML
    private Label label1, label2, label3, label4, label5;

    @FXML
    private TextField txtFieldEventTitle, txtFieldLocation,
            txtFieldDateStart, txtFieldTimeStart, txtFieldDateEnd,
            txtFieldTimeEnd, txtFieldNotes, txtFieldMaxVouchers, txtFieldMaxTickets,
            txtFieldTicketType, txtFieldPrice, txtFieldAmount;
    @FXML
    private DatePicker datePickerStart, datePickerEnd;

    EventModel modelEvent = new EventModel();

    public void clickSave(ActionEvent actionEvent) {
        try {
            String title = txtFieldEventTitle.getText();
            String location = txtFieldLocation.getText();
            String dateStart = txtFieldDateStart.getText();
            String dateEnd = txtFieldDateEnd.getText();
            String timeStart = txtFieldTimeStart.getText();
            String timeEnd = txtFieldTimeEnd.getText();
            String notes = txtFieldNotes.getText();
            int maxVouchers = Integer.parseInt(txtFieldMaxVouchers.getText());
            int maxTickets = Integer.parseInt(txtFieldMaxVouchers.getText());
            if (!txtFieldEventTitle.getText().isBlank() || !txtFieldEventTitle.getText().isEmpty()
                    || !txtFieldLocation.getText().isBlank() || !txtFieldLocation.getText().isEmpty()
                    || !txtFieldDateStart.getText().isEmpty() || !txtFieldDateStart.getText().isBlank()) {
                Event event = new Event(title, timeStart, timeEnd, location, notes, 0, 0, maxTickets, maxVouchers, dateStart, dateEnd);
                modelEvent.addEvent(event);

            }
        }catch (NumberFormatException e){
            lblWarning.setText("Amount must be a number");
        }
        String title = txtFieldEventTitle.getText();
        String location = txtFieldLocation.getText();
        String dateStart = getFormattedDateFromDatePicker(datePickerStart);
        String dateEnd = getFormattedDateFromDatePicker(datePickerEnd);
        String notes = txtFieldNotes.getText();
        if(!txtFieldEventTitle.getText().isBlank() || !txtFieldEventTitle.getText().isEmpty()
                || !txtFieldLocation.getText().isBlank() || !txtFieldLocation.getText().isEmpty() ||
                !getFormattedDateFromDatePicker(datePickerStart).isEmpty() ||
                !getFormattedDateFromDatePicker(datePickerStart).isBlank())
        {
           // modelEvent.addEvent(title, location, dateStart, dateEnd, notes);
           // generateTickets(title);
            System.out.println("It worked!");
            lblWarning.setText("Event has been successfully created!");
            //TODO dateEnd returns same value as dateStart. Needs to be fixed.
        }
        System.out.println("It didnt work :(");
        lblWarning.setText("Please input valid information.");
    }

    public void clickCancel(ActionEvent actionEvent) {
        txtFieldEventTitle.clear();
        txtFieldLocation.clear();
        txtFieldNotes.clear();
        txtFieldMaxVouchers.clear();
        txtFieldMaxTickets.clear();

        System.out.println(getFormattedDateFromDatePicker(datePickerStart));
        System.out.println(getFormattedDateFromDatePicker(datePickerEnd));
        System.out.println(datePickerStart.getValue().toString());
        System.out.println(datePickerEnd.getValue().toString());
    }

    private String getFormattedDateFromDatePicker(DatePicker datePicker) {
        String date = null;
        if(datePicker.getValue()!=null){
            //Get the selected date
            LocalDate selectedDate = datePicker.getValue();

        //Create DateTimeFormatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        date = selectedDate.format(formatter);
        }
        //Convert LocalDate to formatted String
        return date;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
