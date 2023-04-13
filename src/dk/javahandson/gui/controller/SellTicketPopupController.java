package dk.javahandson.gui.controller;

import dk.javahandson.gui.model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;

import java.net.URL;
import java.util.*;

public class SellTicketPopupController implements Initializable {
    @FXML
    private MFXButton sellButton, cancelButton;
    @FXML
    private ComboBox ticketVoucherBox, typeBox;
    @FXML
    private TextField textFieldName, textFieldEmail;
    TicketModel ticketModel = TicketModel.getInstance();

    public boolean ticketOrVoucher; //true if ticket, false if voucher

    public void clickSell(ActionEvent actionEvent) {
    }

    public void clickCancel(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> tickets = FXCollections.observableArrayList("Ticket", "Voucher");
        ticketVoucherBox.setItems(tickets);
        ticketVoucherBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            // Clear the options in comboBox2
            typeBox.getItems().clear();

            // Add new options to comboBox2 based on the selected value in comboBox1
            if (newValue.equals("Ticket")) {
                typeBox.getItems().addAll(ticketModel.getTicketTypes(ticketModel.getSelectedEvent().getId()));
            } else if (newValue.equals("Voucher")) {
                typeBox.getItems().addAll(ticketModel.getVoucherTypes(ticketModel.getSelectedEvent().getId()));
            }
        });
    }

}
