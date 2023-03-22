package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminEventsMenuController implements Initializable {

    @FXML
    private TextField txtFieldSearch;

    EventModel model = new EventModel();

    @FXML
    private TableColumn<dk.javahandson.be.Event, String> columnEventTitle, columnLocation,
            columnDateStart, columnDateEnd, columnTicketsSold, columnVouchersUsed;
    @FXML
    private TableView<Event> tableViewEvents;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        columnEventTitle.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        columnDateStart.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        columnDateEnd.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        columnTicketsSold.setCellValueFactory(new PropertyValueFactory<>("ticketsSold"));
        columnVouchersUsed.setCellValueFactory(new PropertyValueFactory<>("voucherUsed"));
        tableViewEvents.setItems(model.getEvents());

    }

    public void clickDelete(ActionEvent actionEvent) {
        Event event = tableViewEvents.getSelectionModel().getSelectedItem();
        if (tableViewEvents.getSelectionModel() != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete "+event.getName()+"?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                {
                    model.deleteEvent(event);
                }
            }
        }
    }
}
