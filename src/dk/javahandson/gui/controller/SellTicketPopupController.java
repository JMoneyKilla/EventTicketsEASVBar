package dk.javahandson.gui.controller;

import dk.javahandson.be.Ticket;
import dk.javahandson.be.Voucher;
import dk.javahandson.bll.helpers.EmailHandler;
import dk.javahandson.bll.helpers.TicketGenerator;
import dk.javahandson.gui.model.TicketModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SellTicketPopupController implements Initializable {
    @FXML
    private ComboBox ticketVoucherBox, typeBox;
    @FXML
    private TextField textFieldName, textFieldEmail;
    TicketModel ticketModel = TicketModel.getInstance();
    EmailHandler emailHandler = new EmailHandler();
    TicketGenerator ticketGenerator = new TicketGenerator();

    public boolean isTicket = false;
    public boolean isVoucher = false;

    /*
    Checks if you are selling a ticket or voucher then generates an image with a qr code,
    updates the database to add a ticket/voucher and opens an email for the event coordinator
    to send the customer their ticket/voucher
     */
    public void clickSell(ActionEvent actionEvent) throws SQLException {
        if(isTicket){
            Ticket ticket = new Ticket(ticketModel.generateTicketUUID(), ticketModel.getSelectedEvent().getId(),
                (String) typeBox.getSelectionModel().getSelectedItem(), textFieldName.getText(), textFieldEmail.getText());
            ticketModel.createTicket(ticket);
            ticketGenerator.createCompleteTicket(ticket, ticketModel.getSelectedEvent());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Ticket has been sold!");
            alert.showAndWait();
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
            emailHandler.openEmail(textFieldEmail.getText(), ticketModel.getSelectedEvent().getName());
        }
        if(isVoucher){
            Voucher voucher = new Voucher(ticketModel.generateTicketUUID(), ticketModel.getSelectedEvent().getId(),
                    (String) typeBox.getSelectionModel().getSelectedItem());
            ticketModel.createVoucher(voucher);
            ticketGenerator.createCompleteVoucher(voucher, ticketModel.getSelectedEvent());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Voucher er blevet soldt!");
            alert.showAndWait();
            Node n = (Node) actionEvent.getSource();
            Stage stage = (Stage) n.getScene().getWindow();
            stage.close();
            emailHandler.openEmail(textFieldEmail.getText(), ticketModel.getSelectedEvent().getName());
        }

    }

    public void clickCancel(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
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
