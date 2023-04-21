package dk.javahandson.bll.managers;

import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import dk.javahandson.dal.DataAccessFacade;

import java.sql.SQLException;
import java.util.List;

public class EventManager {
    private DataAccessFacade eventDAO = new DataAccessFacade();
    public List<Event> getAllEvents() throws SQLException {
        return eventDAO.getAllEvents();
    }
    public List<Event> getCoordinatorEvents(User u) throws SQLException {
        return eventDAO.getCoordinatorEvents(u);
    }
    public void createEvent(Event event) throws SQLException {
        eventDAO.createEvent(event);
    }
    public boolean deleteEvent(Event event) throws SQLException {
        return eventDAO.deleteEvent(event);
    }
    public void updateEvent(Event event) throws SQLException {
        eventDAO.updateEvent(event);
    }
    public int getEventId(String title) throws SQLException {
        return  eventDAO.getEventId(title);
    }
    public Event getEventByEventId(int id) throws SQLException {
        return eventDAO.getEventByEventId(id);
    }
    }
