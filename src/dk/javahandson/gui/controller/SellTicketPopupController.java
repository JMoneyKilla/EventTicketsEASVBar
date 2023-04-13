package dk.javahandson.gui.controller;

import dk.javahandson.be.Ticket;
import dk.javahandson.be.Voucher;
import dk.javahandson.bll.EmailHandler;
import dk.javahandson.bll.helpers.TicketGenerator;
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
import java.sql.SQLException;
import java.util.*;

public class SellTicketPopupController implements Initializable {
    @FXML
    private MFXButton sellButton, cancelButton;
    @FXML
    private ComboBox ticketVoucherBox, typeBox;
    @FXML
    private TextField textFieldName, textFieldEmail;
    TicketModel ticketModel = TicketModel.getInstance();
    EmailHandler emailHandler = new EmailHandler();
    TicketGenerator ticketGenerator = new TicketGenerator();

    public boolean isTicket = false;
    public boolean isVoucher = false;

    public void clickSell(ActionEvent actionEvent) throws SQLException {
        if(isTicket){
            Ticket ticket = new Ticket(ticketModel.generateTicketUUID(), ticketModel.getSelectedEvent().getId(),
                (String) typeBox.getSelectionModel().getSelectedItem(), textFieldName.getText(), textFieldEmail.getText());
            ticketModel.createTicket(ticket);
            ticketGenerator.createCompleteTicket(ticket, ticketModel.getSelectedEvent());
        //emailHandler.openEmail(textFieldEmail.getText(), ticketModel.getSelectedEvent().getName(), "Welcome to the Party");
        }
        if(isVoucher){
            Voucher voucher = new Voucher(ticketModel.generateTicketUUID(), ticketModel.getSelectedEvent().getId(),
                    (String) typeBox.getSelectionModel().getSelectedItem());
            ticketModel.createVoucher(voucher);
            ticketGenerator.createCompleteVoucher(voucher, ticketModel.getSelectedEvent());
        }

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
                isTicket = true;
                isVoucher = false;
                typeBox.getItems().addAll(ticketModel.getTicketTypes(ticketModel.getSelectedEvent().getId()));
            } else if (newValue.equals("Voucher")) {
                isTicket = false;
                isVoucher = true;
                typeBox.getItems().addAll(ticketModel.getVoucherTypes(ticketModel.getSelectedEvent().getId()));
            }
        });
    }

}
