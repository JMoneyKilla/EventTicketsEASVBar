package dk.javahandson.gui.model;

import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import dk.javahandson.bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class EventModel {
    private static EventModel instance;

    private final ObservableList<Event> events;
    ManagerFacade bll = new ManagerFacade();
    User loggedInUser;



    public static EventModel getInstance(){
        if(instance==null)
            instance = new EventModel();
        return instance;
    }

    private EventModel() {
        events = FXCollections.observableArrayList();
        if(this.loggedInUser!=null)
            fetchAllEvents();
    }

    public void fetchAllEvents()
    {
        events.clear();
        try {
            events.addAll(bll.getCoordinatorEvents(loggedInUser));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Event> getEvents()
    {
        return events;
    }

    public void addEvent(Event event)
    {
        try {
            bll.createEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteEvent(Event event)
    {
        try {
            bll.deleteEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int getEventId(String title) {
        try {
            return bll.getEventId(title);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTicketsSold(Event event){
        event.setTicketsSold(event.getTicketsSold()+1);
        try {
            bll.updateEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setLoggedInUser(User user){
        this.loggedInUser = user;
    }
    public User getLoggedInUser(){
        return loggedInUser;
    }
}
