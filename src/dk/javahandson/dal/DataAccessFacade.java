package dk.javahandson.dal;

import dk.javahandson.be.Event;
import dk.javahandson.be.Ticket;
import dk.javahandson.be.User;
import dk.javahandson.be.Voucher;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class DataAccessFacade {
    private EventDAO eventDAO = new EventDAO();
    private TicketDAO ticketDAO = new TicketDAO();
    private UserDAO userDAO = new UserDAO();
    private VoucherDAO voucherDAO = new VoucherDAO();


    /*
    Methods used to access Event DAO
     */

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
        return eventDAO.getEventId(title);
    }
    public Event getEventByEventId(int id) throws SQLException {
        return eventDAO.getEventByEventId(id);
    }
    
    /*
    Methods used to access TicketDAO
     */

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
    public void addTicketType(String type){
        ticketDAO.addTicketType(type);
    }
    public void addVoucherType(String type){
        ticketDAO.addVoucherType(type);
    }


    /*
    Methods used to access UserDAO
     */

    public List<User> getAllUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    public void createLogin(String username, String password) throws SQLException {
        userDAO.createLogin(username, password);
    }
    public void createUser(User user) throws SQLException {
        userDAO.createUser(user);
    }
    public boolean deleteUser(User user) throws SQLException {
        return userDAO.deleteUser(user);
    }

    public boolean deleteUserFromLogin(User user) throws SQLException {
        return userDAO.deleteFromLoginUser(user);
    }

    public boolean deleteUserFromUserEvent(User user) throws SQLException {
        return userDAO.deleteFromUserEvent(user);
    }
    public void updateUser(User user) throws SQLException {
        userDAO.updateUser(user);
    }
    public void addUserToEvent(Event event, User user) throws SQLException {
        userDAO.addUserToEvent(event, user);
    }

    public String getPasswordFromUser(User user) throws SQLException {
        return userDAO.getPasswordFromUser(user);
    }

    public void updateLogin(User user, String password) throws SQLException {
        userDAO.updateLogin(user, password);
    }
    public User loginUser(String email) throws SQLException{
        return userDAO.loginUser(email);
    }
    public boolean validateLogin(String email, String password) throws SQLException {
        return userDAO.validateLogin(email, password);
    }
    public ObservableList<User> getUsersOnEvent(Event event) throws SQLException {
        return userDAO.getUsersOnEvent(event);
    }

    /*
    Methods used to access VoucherDAO
     */

    public List<Voucher> getAllVouchers() throws SQLException {
        return voucherDAO.getAllVouchers();
    }
    public boolean deleteVoucher(Voucher voucher) throws SQLException {
        return voucherDAO.deleteVoucher(voucher);
    }
    public void createVoucher (Voucher voucher) throws SQLException {
        voucherDAO.createVoucher(voucher);
    }
    public ObservableList getVoucherTypes(int id) {
        return voucherDAO.getVoucherTypes(id);
    }
    }
