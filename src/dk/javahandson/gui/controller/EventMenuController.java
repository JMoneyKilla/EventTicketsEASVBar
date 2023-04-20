package dk.javahandson.gui.controller;

import dk.javahandson.be.Event;
import dk.javahandson.gui.model.EventModel;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

            for (Event event : eventModel.getEvents()) {
                if (event.getName().equals(eventName)) {
                    eventModel.setMatchingEvent(event);
                    break;
                }
            }
                    Node n = viewButton;
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

      // VBox vBox = (VBox) eventPane.lookup("#vbox");
      // HBox hBox = (HBox) vBox.lookup("#hbox");
      // MFXButton viewButton = (MFXButton) hBox.lookup("#editButton");
      // viewButton.setOnAction(e -> {
      //     // Retrieve the eventName Label from the eventPane
      //     Label eventNameLabel = (Label) vBox.lookup("#eventName");
      //     String eventName = eventNameLabel.getText();

      //     // Search the eventList for an event with a matching name
      //     Event matchingEvent = eventModel.getEvents().stream().filter(event -> event.getName().equals(eventName)).findFirst().orElse(null);

      //     // If a matching event is found, do something with it
      //     if (matchingEvent != null) {
      //         Stage popupStage = new Stage();
      //         popupStage.initModality(Modality.APPLICATION_MODAL);

      //         // Create a VBox to hold the event information
      //         VBox popupVBox = new VBox();
      //         popupVBox.setSpacing(10);
      //         popupVBox.setPadding(new Insets(10));

      //         // Create Labels to display the event information
      //         Label name = new Label("Event name: ");
      //         name.setStyle("-fx-text-fill: #ffffff");
      //         TextField nameText = new TextField(matchingEvent.getName());
      //         HBox nameBox = new HBox(name, nameText);

      //         Label location = new Label("Location: ");
      //         location.setStyle("-fx-text-fill: #ffffff");
      //         TextField locationText = new TextField(matchingEvent.getLocation());
      //         HBox locationBox = new HBox(location, locationText);

      //         Label start = new Label("Start date: ");
      //         start.setStyle("-fx-text-fill: #ffffff");
      //         TextField startText = new TextField(matchingEvent.getStartDate());
      //         HBox startDateBox = new HBox(start, startText);

      //         Label startTime = new Label("Start time: ");
      //         startTime.setStyle("-fx-text-fill: #ffffff");
      //         TextField startTimeText = new TextField(matchingEvent.getStartTime());
      //         HBox startTimeBox = new HBox(startTime, startTimeText);

      //         Label end = new Label("End date: ");
      //         end.setStyle("-fx-text-fill: #ffffff");
      //         TextField endText = new TextField(matchingEvent.getEndDate());
      //         HBox endDateBox = new HBox(end, endText);

      //         Label endTime = new Label("End time: ");
      //         endTime.setStyle("-fx-text-fill: #ffffff");
      //         TextField endTimeText = new TextField(matchingEvent.getEndTime());
      //         HBox endTimeBox = new HBox(endTime, endTimeText);

      //         Label notes = new Label("Notes: ");
      //         notes.setStyle("-fx-text-fill: #ffffff");
      //         TextArea notesText = new TextArea(matchingEvent.getNotes());

      //         Label tickets = new Label("Total tickets: ");
      //         tickets.setStyle("-fx-text-fill: #ffffff");
      //         TextField ticketsText = new TextField();
      //         ticketsText.setText("" + matchingEvent.getTotalTickets());
      //         Label vouchers = new Label("Total vouchers: ");
      //         vouchers.setStyle("-fx-text-fill: #ffffff");
      //         TextField vouchersText = new TextField();
      //         vouchersText.setText("" + matchingEvent.getTotalVouchers());
      //         HBox ticketBox = new HBox(tickets, ticketsText);
      //         HBox voucherBox = new HBox(vouchers, vouchersText);


      //         // Add the Labels to the popup VBox
      //         popupVBox.getChildren().addAll(nameBox, locationBox, startDateBox, startTimeBox, endDateBox, endTimeBox, notes, notesText, ticketBox, voucherBox);

      //         // Create a Button to close the popup
      //         Button closeButton = new Button("Close");
      //         Button saveButton = new Button("Save");
      //         Label spaceLabel = new Label("                            ");
      //         Button deleteButton = new Button("Delete Event");
      //         HBox buttonBox = new HBox(closeButton, saveButton, spaceLabel, deleteButton);

      //         closeButton.setOnAction(event -> popupStage.close());
      //         saveButton.setOnAction(event -> handleSaveButton(new Event(matchingEvent.getId(), nameText.getText(), startTimeText.getText(),
      //                 endTimeText.getText(), locationText.getText(), notesText.getText(), matchingEvent.getTicketsSold(), matchingEvent.getVoucherUsed(),
      //                 Integer.parseInt(ticketsText.getText()), Integer.parseInt(vouchersText.getText()), startText.getText(), endText.getText()), popupStage));
      //         deleteButton.setOnAction(event -> handleDeleteButton(matchingEvent, popupStage));

      //         // Add the closeButton to the popup VBox
      //         popupVBox.getChildren().add(buttonBox);
      //         popupVBox.setStyle("-fx-background-color: #0C2D48");

      //         // Create a Scene for the popup and set it on the popupStage
      //         Scene popupScene = new Scene(popupVBox);
      //         popupStage.setScene(popupScene);

      //         // Show the popup
      //         popupStage.showAndWait();
      //     }
      // });
    });}

    public boolean isValidEventEdit(Event event){
        int check = 0;
        if(!event.getName().isEmpty() && !event.getName().isBlank())
            check++;
        if(event.getId()>0)
            check++;
        if(!event.getStartDate().isEmpty() && !event.getStartDate().isBlank())
            check++;
        if(!event.getStartTime().isEmpty() && !event.getStartTime().isBlank())
            check++;
        if(!event.getEndDate().isEmpty() && !event.getEndDate().isBlank())
            check++;
        if(!event.getEndTime().isEmpty() && !event.getEndTime().isBlank())
            check++;
        if(!event.getName().isEmpty() && !event.getName().isBlank())
            check++;
        if(!event.getLocation().isEmpty() && !event.getLocation().isBlank())
            check++;
        return check == 8;
    }

    public void handleSaveButton(Event event, Stage stage){
        if(isValidEventEdit(event)){
            eventModel.updateEditedEvent(event);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Event successfully edited!");
            alert.showAndWait();
            stage.close();
        }
        eventModel.fetchAllEvents();
        loadData();
    }
    public void handleDeleteButton(Event event, Stage stage){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Event successfully deleted!");
        alert.showAndWait();
        eventModel.deleteEvent(event);
        eventModel.fetchAllEvents();
        loadData();
        stage.close();
    }
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
