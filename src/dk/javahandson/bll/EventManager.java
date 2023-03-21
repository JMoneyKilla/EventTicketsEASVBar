package dk.javahandson.bll;

import dk.javahandson.be.Event;
import dk.javahandson.dal.DataAccessFacade;

import java.sql.SQLException;

public class EventManager {
    DataAccessFacade dataAccessFacade = new DataAccessFacade();

    public void createEvent(Event event) {
        try {
            dataAccessFacade.createEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateEvent(Event event) {
        try {
            dataAccessFacade.updateEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteEvent(Event event) {
        try {
            dataAccessFacade.deleteEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
