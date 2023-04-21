package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.be.Ticket;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateTicketController implements Initializable {
    @FXML
    private TableColumn<Event, String>  columnEvent;
    @FXML
    private TableColumn<Ticket, String>  columnTicketType;
    @FXML
    private Button buttonCreate;
    @FXML
    private TextField textFieldAmount;
    @FXML
    private TableView tableEvent, tableTicketType;

    private EventModel eventModel = EventModel.getInstance();
    private TicketModel ticketModel = TicketModel.getInstance();
    private Event selectedEvent;
    private Ticket selectedTicket;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel.fetchAllEvents();
        columnEvent.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTicketType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableEvent.setItems(eventModel.getEvents());

    }

    public void clickCreate(ActionEvent actionEvent) {
        ticketModel.batchCreateTickets(Integer.parseInt(textFieldAmount.getText()), selectedTicket.getEventId(), selectedTicket.getType());
    }

    public void clickEvent(MouseEvent mouseEvent) {
        selectedEvent = (Event) tableEvent.getSelectionModel().getSelectedItem();
        ticketModel.updateTicketsToEvent(selectedEvent.getId());
        tableTicketType.setItems(ticketModel.getTickets());

    }

    public void clickTicket(MouseEvent mouseEvent) {
        selectedTicket = (Ticket) tableTicketType.getSelectionModel().getSelectedItem();
    }
}
