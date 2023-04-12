package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EventMenuController implements Initializable {

    @FXML
    private AnchorPane eventPane;
    EventModel eventModel = EventModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int row = 0;
        int col = 0;
        for (Event event : eventModel.getEvents()) {
            StackPane stackPane = generateEventPane(event);
            handleViewButton(stackPane);
            handleEditButton(stackPane);
            eventPane.getChildren().add(stackPane);
            AnchorPane.setTopAnchor(stackPane, 25 + row * 160.0);
            AnchorPane.setLeftAnchor(stackPane, 50 + col * 205.0);
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }

    }

    public StackPane generateEventPane(Event event){
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(175, 140);
        stackPane.setOnMouseEntered(e -> {
            stackPane.setScaleX(1.1);
            stackPane.setScaleY(1.1);
        });

        stackPane.setOnMouseExited(e -> {
            stackPane.setScaleX(1.0);
            stackPane.setScaleY(1.0);
        });

        VBox vBox = new VBox();
        vBox.setPrefSize(175, 140);
        vBox.setId("vbox");

        Label eventName = new Label();
        eventName.setPrefSize(175, 16);
        eventName.setFont(Font.font(16));
        eventName.setText(event.getName());
        eventName.setId("eventName");

        Label eventDate = new Label();
        eventDate.setPrefSize(175, 14);
        eventDate.setFont(Font.font(14));
        eventDate.setText(event.getStartDate());
        eventDate.setId("eventDate");

        TextArea textArea = new TextArea();
        textArea.setPrefSize(175, 85);
        textArea.setFont(Font.font(12));
        textArea.setWrapText(true);
        textArea.setText(event.getNotes());
        textArea.setEditable(false);
        textArea.setId("textArea");

        HBox hbox = new HBox();
        hbox.setPrefSize(175,25);
        hbox.setId("hbox");

        MFXButton button1 = new MFXButton();
        button1.setPrefSize(86, 25);
        button1.setText("View");
        button1.setId("viewButton");
        MFXButton button2 = new MFXButton();
        button2.setText("Edit");
        button2.setId("editButton");
        button2.setPrefSize(86, 25);

        vBox.getChildren().add(eventName);
        vBox.getChildren().add(eventDate);
        vBox.getChildren().add(textArea);
        hbox.getChildren().add(button1);
        hbox.getChildren().add(button2);
        vBox.getChildren().add(hbox);


        stackPane.getChildren().add(vBox);

        return stackPane;
    }
    private void handleViewButton(StackPane eventPane) {
        VBox vBox = (VBox) eventPane.lookup("#vbox");
        HBox hBox = (HBox) vBox.lookup("#hbox");
        MFXButton viewButton = (MFXButton) hBox.lookup("#viewButton");
        viewButton.setOnAction(e -> {
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
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);

                // Create a VBox to hold the event information
                VBox popupVBox = new VBox();
                popupVBox.setSpacing(10);
                popupVBox.setPadding(new Insets(10));

                // Create Labels to display the event information
                Label name = new Label("Event name: " + matchingEvent.getName());
                Label start = new Label("Start date: " + matchingEvent.getStartDate().toString());
                Label startTime = new Label("Start time: " + matchingEvent.getStartTime().toString());
                Label end = new Label("End date: " + matchingEvent.getEndDate().toString());
                Label endTime = new Label("End time: " + matchingEvent.getEndTime().toString());
                Label notes = new Label("Notes: " + matchingEvent.getNotes());
                Label tickets = new Label("Total tickets: " + matchingEvent.getTotalTickets());
                Label sold = new Label("Tickets sold: " + matchingEvent.getTicketsSold());

                // Add the Labels to the popup VBox
                popupVBox.getChildren().addAll(name, start, startTime, end, endTime, notes, tickets, sold);

                // Create a Button to close the popup
                Button closeButton = new Button("Close");
                closeButton.setOnAction(event -> popupStage.close());

                // Add the closeButton to the popup VBox
                popupVBox.getChildren().add(closeButton);

                // Create a Scene for the popup and set it on the popupStage
                Scene popupScene = new Scene(popupVBox);
                popupStage.setScene(popupScene);

                // Show the popup
                popupStage.showAndWait();
            }
        });
    }
    private void handleEditButton(StackPane eventPane) {
        VBox vBox = (VBox) eventPane.lookup("#vbox");
        HBox hBox = (HBox) vBox.lookup("#hbox");
        MFXButton viewButton = (MFXButton) hBox.lookup("#editButton");
        viewButton.setOnAction(e -> {
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
                Stage popupStage = new Stage();
                popupStage.initModality(Modality.APPLICATION_MODAL);

                // Create a VBox to hold the event information
                VBox popupVBox = new VBox();
                popupVBox.setSpacing(10);
                popupVBox.setPadding(new Insets(10));

                // Create Labels to display the event information
                Label name = new Label("Event name: ");
                TextField nameText = new TextField(matchingEvent.getName());
                HBox nameBox = new HBox(name, nameText);

                Label start = new Label("Start date: ");
                TextField startText = new TextField(matchingEvent.getStartDate());
                HBox startDateBox = new HBox(start, startText);

                Label startTime = new Label("Start time: ");
                TextField startTimeText = new TextField(matchingEvent.getStartTime());
                HBox startTimeBox = new HBox(startTime, startTimeText);

                Label end = new Label("End date: ");
                TextField endText = new TextField(matchingEvent.getEndDate());
                HBox endDateBox = new HBox(end, endText);

                Label endTime = new Label("End time: ");
                TextField endTimeText = new TextField(matchingEvent.getEndTime());
                HBox endTimeBox = new HBox(endTime, endTimeText);

                Label notes = new Label("Notes: ");
                TextArea notesText = new TextArea(matchingEvent.getNotes());

                Label tickets = new Label("Total tickets: ");
                TextField ticketsText = new TextField();
                ticketsText.setText("" + matchingEvent.getTotalTickets());
                HBox ticketBox = new HBox(tickets, ticketsText);


                // Add the Labels to the popup VBox
                popupVBox.getChildren().addAll(nameBox, startDateBox, startTimeBox, endDateBox, endTimeBox, notes, notesText, ticketBox);

                // Create a Button to close the popup
                Button closeButton = new Button("Close");
                closeButton.setOnAction(event -> popupStage.close());

                // Add the closeButton to the popup VBox
                popupVBox.getChildren().add(closeButton);

                // Create a Scene for the popup and set it on the popupStage
                Scene popupScene = new Scene(popupVBox);
                popupStage.setScene(popupScene);

                // Show the popup
                popupStage.showAndWait();
            }
        });
    }
}
