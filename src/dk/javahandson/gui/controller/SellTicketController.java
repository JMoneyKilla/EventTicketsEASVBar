package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SellTicketController implements Initializable {
    @FXML
    private Button buttonSellTicket, buttonCancel;
    @FXML
    private TextField textFieldName, textFieldEmail;
    @FXML
    private ListView listViewEvents, listViewTickets;

    private TicketModel ticketModel = TicketModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private Event selectedEvent;


    public void clickEventTable(MouseEvent mouseEvent) {
        selectedEvent = (Event) listViewEvents.getSelectionModel().getSelectedItem();
        ticketModel.updateTicketsToEvent(selectedEvent.getId());
        listViewTickets.setItems(ticketModel.getTickets());
    }

    public void clickTicketTable(MouseEvent mouseEvent) {
    }

    public void clickSell(ActionEvent actionEvent) {
    }

    public void clickCancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel.fetchAllEvents();
        listViewEvents.setItems(eventModel.getEvents());
    }
}
