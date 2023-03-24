package dk.javahandson.gui.controller;

import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateEventController implements Initializable {
    @FXML
    private ListView listViewTickets;
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
            txtFieldTimeEnd, txtFieldNotes,
            txtFieldTicketType, txtFieldAmount;

    EventModel modelEvent = new EventModel();
    TicketModel modelTicket = new TicketModel();

    public void clickAddTicket(ActionEvent actionEvent) {
        try{
        if(txtFieldTicketType !=null && txtFieldAmount !=null && !txtFieldTicketType.getText().isBlank() && !txtFieldAmount.getText().isBlank() && txtFieldAmount.getText().equals(Integer.toString(Integer.parseInt(txtFieldAmount.getText()))));
        {
            listViewTickets.getItems().add(txtFieldTicketType.getText() + " , " + txtFieldAmount.getText());
            txtFieldTicketType.clear();
            txtFieldAmount.clear();
        }
        }catch (NumberFormatException e){
        lblWarning.setText("Amount must be a number");
        }
    }
    private void generateTickets(String title){
        int eventId = modelEvent.getEventId(title);

        listViewTickets.getItems().forEach(ticket -> {
            String[] ticketInfo = ticket.toString().split(" , ");
            String type = ticketInfo[0];
            int amount = Integer.parseInt(ticketInfo[1]);
            modelTicket.batchCreateTickets(amount,eventId,type);
        });

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
            modelEvent.addEvent(title, location, dateStart, dateEnd, notes);
            generateTickets(title);
        }

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
