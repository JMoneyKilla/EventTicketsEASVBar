package dk.javahandson.gui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;

import java.awt.*;

public class CreateEventController {

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

    public void clickAddTicket(ActionEvent actionEvent) {
        if(txtFieldTicketType !=null && txtFieldPrice !=null && txtFieldAmount !=null)
        {
            txtFieldTicketType.clear();
            txtFieldPrice.clear();
            txtFieldAmount.clear();
        }
    }
}
