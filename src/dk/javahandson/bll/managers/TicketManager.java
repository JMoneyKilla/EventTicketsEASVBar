package dk.javahandson.bll.managers;

import dk.javahandson.be.Ticket;
import dk.javahandson.dal.DataAccessFacade;

import java.sql.SQLException;

public class TicketManager {
    private DataAccessFacade ticketDAO = new DataAccessFacade();

    public void createTicket(Ticket ticket) throws SQLException {
        ticketDAO.createTicket(ticket);
    }
    public boolean deleteTicket(Ticket ticket) throws SQLException {
        return ticketDAO.deleteTicket(ticket);
    }
    public void updateTicket(Ticket ticket) throws SQLException {
        ticketDAO.updateTicket(ticket);
    }
    public void redeemTicket(Ticket ticket) throws SQLException {
        ticketDAO.redeemTicket(ticket);
    }
}
