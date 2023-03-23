package dk.javahandson.gui.controller;

import dk.javahandson.be.Ticket;
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
    private AnchorPane createEventAnchorPane;
    @FXML
    private VBox ticketsVBox;
    @FXML
    private Label label1, label2, label3, label4, label5;
    @FXML
    private TextField txtFieldEventTitle, txtFieldLocation,
            txtFieldDateStart, txtFieldTimeStart, txtFieldDateEnd,
            txtFieldTimeEnd, txtFieldNotes,
            txtFieldTicketType, txtFieldPrice, txtFieldAmount;

    @FXML
    private Label lblWarning;

    EventModel eventModel = new EventModel();
    TicketModel ticketModel = new TicketModel();

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
        String dateStart = txtFieldDateStart.getText();
        String dateEnd = txtFieldDateEnd.getText();
        String notes = txtFieldNotes.getText();
        if(!txtFieldEventTitle.getText().isBlank() || !txtFieldEventTitle.getText().isEmpty()
                || !txtFieldLocation.getText().isBlank() || !txtFieldLocation.getText().isEmpty()
                || !txtFieldDateStart.getText().isEmpty() || !txtFieldDateStart.getText().isBlank())
        {

            eventModel.addEvent(title, location, dateStart, dateEnd, notes);
            int eventId = eventModel.getEventId(title);
            Ticket ticket = new Ticket("0124-145r1-1gt4-gvr2",eventId,"normal",null,null);
            ticketModel.createTicket(ticket);
            System.out.println("It worked!");
        }
        System.out.println("It didnt work :(");
    }

    public void clickCancel(ActionEvent actionEvent) {
        txtFieldEventTitle.clear();
        txtFieldLocation.clear();
        txtFieldDateStart.clear();
        txtFieldDateEnd.clear();
        txtFieldNotes.clear();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
