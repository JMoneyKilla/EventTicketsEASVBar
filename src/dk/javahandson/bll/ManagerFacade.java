package dk.javahandson.bll;

import dk.javahandson.be.Event;
import dk.javahandson.be.Ticket;
import dk.javahandson.be.User;
import dk.javahandson.be.Voucher;
import dk.javahandson.bll.managers.*;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class ManagerFacade {
    private EventManager eventManager = new EventManager();
    private TicketManager ticketManager = new TicketManager();
    private UserManager userManager = new UserManager();
    private VoucherManager voucherManager = new VoucherManager();

    /*
    Methods used to access EventManager
     */

    public List<Event> getAllEvents() throws SQLException {
        return eventManager.getAllEvents();
    }
    public List<Event> getCoordinatorEvents(User u) throws SQLException {
        return eventManager.getCoordinatorEvents(u);
    }
    public void createEvent(Event event) throws SQLException {
        eventManager.createEvent(event);
    }
    public boolean deleteEvent(Event event) throws SQLException {
        return eventManager.deleteEvent(event);
    }
    public void updateEvent(Event event) throws SQLException {
        eventManager.updateEvent(event);
    }
    public int  getEventId(String title) throws SQLException {
        return eventManager.getEventId(title);
    }
    public Event getEventByEventId(int id) throws SQLException {
        return eventManager.getEventByEventId(id);
    }

    /*
    Methods used to access TicketManager
     */

    public void createTicket(Ticket ticket) throws SQLException {
        ticketManager.createTicket(ticket);
    }
    public boolean deleteTicket(Ticket ticket) throws SQLException {
        return ticketManager.deleteTicket(ticket);
    }
    public void updateTicket(Ticket ticket) throws SQLException {
        ticketManager.updateTicket(ticket);
    }
    public void redeemTicket(Ticket ticket) throws SQLException {
        ticketManager.redeemTicket(ticket);
    }
    public List<Ticket> getAllTickets() throws SQLException {
        return ticketManager.getAllTickets();
    }
    public List<Ticket> getTicketsByEventId(int id){
        return ticketManager.getTicketsByEventId(id);
    }
    public void batchCreateTickets(int records, int eventId, String type) throws SQLException {
        ticketManager.batchCreateTickets(records, eventId, type);
    }
    public ObservableList getTicketTypes(int id) {
        return ticketManager.getTicketTypes(id);
    }
    public void addTicketType(String type){
        ticketManager.addTicketType(type);
    }
    public void addVoucherType(String type){
        ticketManager.addVoucherType(type);
    }


    /*
    Methods used to access UserManager
     */

    public List<User> getAllUsers() throws SQLException {
        return userManager.getAllUsers();
    }

    public void createLogin(String username, String password) throws SQLException {
        userManager.createLogin(username, password);
    }
    public void createUser(User user) throws SQLException {
        userManager.createUser(user);
    }
    public boolean deleteUser(User user) throws SQLException {
        return userManager.deleteUser(user);
    }

    public boolean deleteUserFromLogin(User user) throws SQLException {
        return userManager.deleteUserFromLogin(user);
    }

    public boolean deleteUserFromUserEvent(User user) throws SQLException {
        return userManager.deleteUserFromUserEvent(user);
    }

    public void updateUser(User user) throws SQLException {
        userManager.updateUser(user);
    }
    public void addUserToEvent(Event event, User user) throws SQLException {
        userManager.addUserToEvent(event, user);
    }

    public String getPasswordFromUser(User user) throws SQLException {
        return userManager.getPasswordFromUser(user);
    }

    public void updateLogin(User user, String password) throws SQLException {
        userManager.updateLogin(user, password);
    }
    public User loginUser(String email) throws SQLException{
        return userManager.loginUser(email);
    }
    public boolean validateLogin(String email, String password) throws SQLException {
        return userManager.validateLogin(email, password);
    }

    /*
    Methods used to access VoucherManager
     */

    public List<Voucher> getAllVouchers() throws SQLException {
        return voucherManager.getAllVouchers();
    }
    public boolean deleteVoucher(Voucher voucher) throws SQLException {
        return voucherManager.deleteVoucher(voucher);
    }
    public void createVoucher (Voucher voucher) throws SQLException {
        voucherManager.createVoucher(voucher);
    }
    public ObservableList getVoucherTypes(int id) {
        return voucherManager.getVoucherTypes(id);
    }
    }
