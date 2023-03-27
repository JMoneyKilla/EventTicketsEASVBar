package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
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
            txtFieldTimeEnd, txtFieldNotes, txtFieldMaxVouchers, txtFieldMaxTickets;


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
    }

    public void clickCancel(ActionEvent actionEvent) {
        txtFieldEventTitle.clear();
        txtFieldLocation.clear();
        txtFieldDateStart.clear();
        txtFieldDateEnd.clear();
        txtFieldNotes.clear();
        txtFieldMaxVouchers.clear();
        txtFieldMaxTickets.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
