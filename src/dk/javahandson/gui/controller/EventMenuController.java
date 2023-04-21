package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventMenuController implements Initializable {

    @FXML
    private AnchorPane eventPane;
    EventModel eventModel = EventModel.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        loadData();
        eventModel.getEvents().addListener((ListChangeListener<Event>) c -> {
            while (c.next()){
                if(c.wasAdded() || c.wasRemoved())
                    loadData();
            }
        });
    }

    /**
     * Generates a stackpane which will be used as visual tile for an event that the user can interact with
     * @param event
     * @return
     */
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

        HBox hbox = new HBox();
        hbox.setPrefSize(175,25);
        hbox.setPadding(new Insets(0, 0, 0, 3));
        hbox.setId("hbox");

        MFXButton button1 = new MFXButton();
        button1.setPrefSize(83, 25);
        button1.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 13px;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-border-color: #000000;\n" +
                "    -fx-background-color: #145DA0;");
        button1.setPadding(new Insets(0, 0, 0, 5));
        button1.setText("View");
        button1.setId("viewButton");
        MFXButton button2 = new MFXButton();
        button2.setStyle("-fx-font-family: arial;\n" +
                "    -fx-font-size: 13px;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-border-radius: 5px;\n" +
                "    -fx-border-color: #000000;\n" +
                "    -fx-background-color: #145DA0;");
        button2.setText("Edit");
        button2.setId("editButton");
        button2.setPrefSize(83, 25);

        vBox.getChildren().add(eventName);
        vBox.getChildren().add(eventDate);
        vBox.getChildren().add(ticketAvailability);
        hbox.getChildren().add(button1);
        hbox.getChildren().add(new Label(" "));
        hbox.getChildren().add(button2);
        vBox.getChildren().add(hbox);



        stackPane.getChildren().add(vBox);
        stackPane.setStyle("-fx-background-radius: 10px; -fx-background-color: #B1D4E0;");

        return stackPane;
    }

    /*
    Finds the view button on the StackPane and adds an action event to open up view window when clicked
     */
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
    /*
    Finds edit button on provided StackPane and adds action event to it to open up the edit event
    window
     */
    private void handleEditButton(StackPane eventPane) {
        VBox vBox = (VBox) eventPane.lookup("#vbox");
        HBox hBox = (HBox) vBox.lookup("#hbox");
        MFXButton editButton = (MFXButton) hBox.lookup("#editButton");
        editButton.setOnAction(e -> {
            // Retrieve the eventName Label from the eventPane
            Label eventNameLabel = (Label) vBox.lookup("#eventName");
            String eventName = eventNameLabel.getText();

            // Search the eventList for an event with a matching name

            for (Event event : eventModel.getEvents()) {
                if (event.getName().equals(eventName)) {
                    eventModel.setMatchingEvent(event);
                    break;
                }
            }
                    Node n = editButton;
                    Window stage = n.getScene().getWindow();
                    Parent root;
                    try {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("dk/javahandson/gui/view/EditEvent.fxml"));
                        Stage editUser = new Stage();
                        editUser.setScene(new Scene(root));
                        editUser.setTitle("Edit Event");
                        editUser.initModality(Modality.WINDOW_MODAL);
                        editUser.centerOnScreen();
                        editUser.initOwner(stage);
                        editUser.show();


                    } catch (IOException c) {
                        c.printStackTrace();
                    }

    });}

    /*
    Generates StackPanes for each event that the logged in user is a part of and populates the window
    with the event tiles
     */
    public void loadData(){
        int row = 0;
        int col = 0;
        for (Event event : eventModel.getEvents()) {
            StackPane stackPane = generateEventPane(event);
            handleViewButton(stackPane);
            handleEditButton(stackPane);
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
}
