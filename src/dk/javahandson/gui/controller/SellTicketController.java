package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import dk.javahandson.gui.model.TicketModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SellTicketController implements Initializable {

    @FXML
    private AnchorPane eventPane;
    private TicketModel ticketModel = TicketModel.getInstance();
    private EventModel eventModel = EventModel.getInstance();

    /*
    Sell Ticket uses the same structure of the event menu controller to generate the window for the user
    with different event tiles
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
    }


    public StackPane generateEventPane(Event event){
        StackPane stackPane = new StackPane();

        stackPane.setPrefSize(175, 130);
        stackPane.setOnMouseEntered(e -> {
            stackPane.setScaleX(1.1);
            stackPane.setScaleY(1.1);
        });

        stackPane.setOnMouseExited(e -> {
            stackPane.setScaleX(1.0);
            stackPane.setScaleY(1.0);
        });


        VBox vBox = new VBox();
        vBox.setPrefSize(175, 130);
        vBox.setId("vbox");

        Label eventName = new Label();
        eventName.setPrefSize(175, 16);
        eventName.setFont(Font.font(16));
        eventName.setStyle("-fx-text-fill: #0C2D48;");
        eventName.setText(event.getName());
        eventName.setPadding(new Insets(5, 5, 5, 5));
        eventName.setId("eventName");

        Label eventDate = new Label();
        eventDate.setPrefSize(175, 14);
        eventDate.setFont(Font.font(14));
        eventDate.setStyle("-fx-text-fill: #0C2D48;");
        eventDate.setText(event.getStartDate());
        eventDate.setPadding(new Insets(5, 5, 5, 5));
        eventDate.setId("eventDate");

        Label ticketAvailability = new Label();
        ticketAvailability.setPrefSize(175, 14);
        ticketAvailability.setFont(Font.font(14));
        ticketAvailability.setStyle("-fx-text-fill: #0C2D48;");
        ticketAvailability.setText("Tickets: " + event.getTicketsSold() + "/" + event.getTotalTickets());
        ticketAvailability.setPadding(new Insets(5, 5, 5, 5));
        ticketAvailability.setId("ticketAvailability");


        MFXButton button1 = new MFXButton();
        button1.setPrefSize(163, 25);
        button1.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 13px;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-border-color: #000000;\n" +
                "    -fx-background-color: #145DA0;");
        button1.setPadding(new Insets(0, 0, 0, 5));
        button1.setText("Sell Ticket");
        button1.setId("sellButton");

        HBox hBox = new HBox(button1);
        hBox.setPadding(new Insets(0,0,0,5));
        hBox.setId("hbox");

        vBox.getChildren().add(eventName);
        vBox.getChildren().add(eventDate);
        vBox.getChildren().add(ticketAvailability);
        vBox.getChildren().add(hBox);



        stackPane.getChildren().add(vBox);
        stackPane.setStyle("-fx-background-radius: 10px; -fx-background-color: #B1D4E0;");

        return stackPane;
    }
    public void loadData(){
        int row = 0;
        int col = 0;
        for (Event event : eventModel.getEvents()) {
            StackPane stackPane = generateEventPane(event);
            handleSellButton(stackPane);
            eventPane.getChildren().add(stackPane);
            eventPane.setStyle("-fx-background-color: #0C2D48; -fx-border-color: #0C2D48");
            AnchorPane.setTopAnchor(stackPane, 25 + row * 160.0);
            AnchorPane.setLeftAnchor(stackPane, 50 + col * 205.0);
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }
    }
    /*
    Adds action event to sell button on event tile which will open up a sell ticket popup.
     */
    private void handleSellButton(StackPane eventPane) {
        VBox vBox = (VBox) eventPane.lookup("#vbox");
        HBox hBox = (HBox) vBox.lookup("#hbox");
        MFXButton sellButton = (MFXButton) hBox.lookup("#sellButton");
        sellButton.setOnAction(e -> {
            // Retrieve the eventName Label from the eventPane
            Label eventNameLabel = (Label) vBox.lookup("#eventName");
            String eventName = eventNameLabel.getText();

            // Search the eventList for an event with a matching name
            Event matchingEvent = null;
            for (Event event : eventModel.getEvents()) {
                if (event.getName().equals(eventName)) {
                    matchingEvent = event;
                    break;
                }
            }

            // If a matching event is found, do something with it
            if (matchingEvent != null) {
                ticketModel.setSelectedEvent(matchingEvent);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/javahandson/gui/view/SellTicketPopup.fxml"));
                Parent root = null;
                try {
                    root = loader.load();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            }
        });
    }

}
