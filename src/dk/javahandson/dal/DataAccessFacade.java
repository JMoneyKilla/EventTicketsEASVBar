package dk.javahandson.dal;

import dk.javahandson.be.Event;
import dk.javahandson.be.Ticket;
import dk.javahandson.be.User;
import dk.javahandson.be.Voucher;

import java.sql.SQLException;
import java.util.List;

public class DataAccessFacade {
    private EventDAO eventDAO = new EventDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private UserDAO userDAO = new UserDAO();
    private VoucherDAO voucherDAO = new VoucherDAO();

    //Methods for accessing EventDAO
    public List<Event> getAllEvents() throws SQLException {
        return eventDAO.getAllEvents();
    }
    public List<Event> getCoordinatorEvents(User u) throws SQLException {
        return eventDAO.getCoordinatorEvents(u);
    }
    public void createEvent(Event event) {
        eventDAO.createEvent(event);
    }
    public boolean deleteEvent(Event event) throws SQLException {
        return eventDAO.deleteEvent(event);
    }
    public void updateEvent(Event event) throws SQLException {
        eventDAO.updateEvent(event);
    }

    //Methods for accessing VoucherDAO

    public List<Voucher> getAllVouchers() throws SQLException {
        return voucherDAO.getAllVouchers();
    }
    public boolean deleteVoucher(Voucher voucher) throws SQLException {
        return voucherDAO.deleteVoucher(voucher);
    }
    public void createVoucher (Voucher voucher) throws SQLException {
        voucherDAO.createVoucher(voucher);
    }

    //Methods for accessing TicketDAO

    public void createTicket(Ticket ticket) throws SQLException {
        ticketDAO.createTicket(ticket);
    }




    }
