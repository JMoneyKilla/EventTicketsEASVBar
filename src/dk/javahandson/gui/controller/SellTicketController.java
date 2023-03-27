package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.be.Ticket;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SellTicketController implements Initializable {
    @FXML
    private TableView tableTicketType, tableEvent;
    @FXML
    private TableColumn<Event, String> columnEvent;
    @FXML
    private TableColumn<Ticket, String>  columnTicketType;
    @FXML
    private Button buttonSellTicket, buttonPreview;
    @FXML
    private TextField textFieldName, textFieldEmail;
    @FXML
    private TicketModel ticketModel = TicketModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();
    private Event selectedEvent;
    private Ticket selectedTicket;


    public void clickSell(ActionEvent actionEvent) {
        if(!textFieldEmail.getText().equals("") && textFieldEmail.getText() != null &&
                !textFieldName.getText().equals("") && textFieldName.getText() != null){
            ticketModel.updateCustomerToTicket(selectedTicket, textFieldName.getText(), textFieldEmail.getText());
            eventModel.updateTicketsSold(selectedEvent);
            textFieldName.clear();
            textFieldEmail.clear();
            //send email to customer
            //confirmation popup
        }
        //display popup to enter email or name
    }

    public void clickPreview(ActionEvent actionEvent) {
        //Preview image of ticket to be sold to customer.
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel.fetchAllEvents();
        columnEvent.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnTicketType.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableEvent.setItems(eventModel.getEvents());
    }

    public void clickTicket(MouseEvent mouseEvent) {
        selectedTicket = (Ticket) tableTicketType.getSelectionModel().getSelectedItem();
    }

    public void clickEvent(MouseEvent mouseEvent) {
        if(tableEvent.getSelectionModel().getSelectedItem() != null){
            selectedEvent = (Event) tableEvent.getSelectionModel().getSelectedItem();
            ticketModel.updateTicketsToEvent(selectedEvent.getId());
            tableTicketType.setItems(ticketModel.getTickets());
        }
    }
}
