package dk.javahandson.bll;

import dk.javahandson.be.Ticket;
import dk.javahandson.dal.DataAccessFacade;

import java.sql.SQLException;

public class TicketManager {
    DataAccessFacade dataAccessFacade = new DataAccessFacade();

    public void createTicket(Ticket ticket) {
        try {
            dataAccessFacade.createTicket(ticket);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTicket(Ticket ticket) {
        try {
            dataAccessFacade.updateTicket(ticket);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteTicket(Ticket ticket) {
        try {
            dataAccessFacade.deleteTicket(ticket);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
