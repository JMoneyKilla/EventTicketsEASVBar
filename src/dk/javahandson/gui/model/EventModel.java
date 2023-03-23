package dk.javahandson.gui.model;

import dk.javahandson.be.Event;
import dk.javahandson.bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class EventModel {
    private static EventModel instance;

    private final ObservableList<Event> events;
    ManagerFacade bll = new ManagerFacade();

    public static EventModel getInstance(){
        if(instance==null)
            instance = new EventModel();
        return instance;
    }

    public EventModel() {
        events = FXCollections.observableArrayList();
        fetchAllEvents();
    }

    public void fetchAllEvents()
    {
        events.clear();
        try {
            events.addAll(bll.getAllEvents());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Event> getEvents()
    {
        return events;
    }

    public void addEvent(String name, String location, String dateStart, String dateEnd, String notes)
    {
        try {
            bll.createEvent(new Event(name, location, dateStart, dateEnd, notes));
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
    public int getEventId(String title)
    {
        try {
           return bll.getEventId(title);
        } catch (SQLException e) {
            throw new RuntimeException(e);
    }
}
}
