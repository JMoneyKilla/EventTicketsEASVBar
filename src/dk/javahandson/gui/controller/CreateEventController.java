package dk.javahandson.gui.controller;

import dk.javahandson.gui.model.EventModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import java.awt.*;
import java.net.URL;
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
            txtFieldDateStart, txtFieldTimeStart, txtFieldDateEnd,
            txtFieldTimeEnd, txtFieldNotes,
            txtFieldTicketType, txtFieldPrice, txtFieldAmount;

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
        String dateStart = txtFieldDateStart.getText();
        String dateEnd = txtFieldDateEnd.getText();
        String notes = txtFieldNotes.getText();
        if(!txtFieldEventTitle.getText().isBlank() || !txtFieldEventTitle.getText().isEmpty()
                || !txtFieldLocation.getText().isBlank() || !txtFieldLocation.getText().isEmpty()
                || !txtFieldDateStart.getText().isEmpty() || !txtFieldDateStart.getText().isBlank())
        {
            model.addEvent(title, location, dateStart, dateEnd, notes);
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
