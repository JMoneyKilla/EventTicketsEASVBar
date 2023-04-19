package dk.javahandson.bll;

import dk.javahandson.be.Event;
import dk.javahandson.be.User;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerFacadeTest {

    ManagerFacade managerFacade = new ManagerFacade();

    @org.junit.jupiter.api.Test
    void getAllEvents() {
        int expectedSize = 4; //This needs to be changed for a known variable
        int expectedFirstId = 143; //This needs to be changed for a known variable
        List<Event> allEvents;

        try {
            allEvents = managerFacade.getAllEvents();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedSize, allEvents.size());
        assertEquals(expectedFirstId, allEvents.get(0).getId());
    }

    @org.junit.jupiter.api.Test
    void getCoordinatorEvents() {
        User user = new User(20, "julian", "EventCoordinator", "mailmail");
        int expectedSize = 2; //This needs to be changed for a known variable
        int expectedFirstId = 143; //This needs to be changed for a known variable
        List<Event> allEvents;

        try {
            allEvents = managerFacade.getCoordinatorEvents(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(expectedSize, allEvents.size());
        assertEquals(expectedFirstId, allEvents.get(0).getId());
    }

    @org.junit.jupiter.api.Test
    void createAndDeleteEvent() {
        String test = "test";
        boolean deleted = false;
        Event event = new Event("User TestingXYZYZ", test, test, test, test, 0, 0,0,0,test,test);
        try {
            managerFacade.createEvent(event);
            int id = managerFacade.getEventId(event.getName());
            event.setId(id);
            deleted = managerFacade.deleteEvent(event);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assertEquals(true, deleted);
    }

    @org.junit.jupiter.api.Test
    void updateEvent() {
    }

    @org.junit.jupiter.api.Test
    void createTicket() {
    }

    @org.junit.jupiter.api.Test
    void deleteTicket() {
    }

    @org.junit.jupiter.api.Test
    void updateTicket() {
    }

    @org.junit.jupiter.api.Test
    void redeemTicket() {
    }

    @org.junit.jupiter.api.Test
    void createUser() {
    }

    @org.junit.jupiter.api.Test
    void deleteUser() {
    }

    @org.junit.jupiter.api.Test
    void updateUser() {
    }

    @org.junit.jupiter.api.Test
    void addUserToEvent() {
    }

    @org.junit.jupiter.api.Test
    void getAllVouchers() {
    }

    @org.junit.jupiter.api.Test
    void deleteVoucher() {
    }

    @org.junit.jupiter.api.Test
    void createVoucher() {
    }
}