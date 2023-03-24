package dk.javahandson.gui.controller;

import dk.javahandson.gui.model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
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
    private ListView tableViewTickets;
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
            txtFieldTimeStart, txtFieldTimeEnd, txtFieldNotes,
            txtFieldTicketType, txtFieldPrice, txtFieldAmount;
    @FXML
    private DatePicker datePickerStart, datePickerEnd;

    EventModel model = new EventModel();

    public void clickAddTicket(ActionEvent actionEvent) {
        if(txtFieldTicketType !=null && txtFieldPrice !=null && txtFieldAmount !=null)
        {
            txtFieldTicketType.clear();
            txtFieldPrice.clear();
            txtFieldAmount.clear();
        }
    }

    public void clickSave(ActionEvent actionEvent) {
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
            model.addEvent(title, location, dateStart, dateEnd, notes);
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
