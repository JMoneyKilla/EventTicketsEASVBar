package dk.javahandson.bll.managers;

import dk.javahandson.be.Ticket;
import dk.javahandson.dal.DataAccessFacade;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

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
    public List<Ticket> getAllTickets() throws SQLException {
        return ticketDAO.getAllTickets();
    }
    public List<Ticket> getTicketsByEventId(int id){
        return ticketDAO.getTicketsByEventId(id);
    }
    public void batchCreateTickets(int records, int eventId, String type) throws SQLException {
        ticketDAO.batchCreateTickets(records, eventId, type);
    }
    public ObservableList getTicketTypes(int id) {
        return ticketDAO.getTicketTypes(id);
    }
}
