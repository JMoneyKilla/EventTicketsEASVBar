package dk.javahandson.gui.model;


import dk.javahandson.be.Event;
import dk.javahandson.be.Ticket;
import dk.javahandson.bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class TicketModel {

    private static TicketModel instance;

    private final ObservableList<Ticket> tickets;
    private ManagerFacade bll = new ManagerFacade();
    private Event selectedEvent;

    public static TicketModel getInstance() {
        if (instance == null)
            instance = new TicketModel();
        return instance;
    }

    public Event getSelectedEvent() {
        return selectedEvent;
    }

    public void setSelectedEvent(Event selectedEvent) {
        this.selectedEvent = selectedEvent;
    }

    public ObservableList<Ticket> getTickets() {
        return tickets;
    }

    private TicketModel() {
        tickets = FXCollections.observableArrayList();
    }


    public void fetchAllTickets() {
        tickets.clear();

    }

    public void updateTicketsToEvent(int id) {
        List<Ticket> eventTickets = bll.getTicketsByEventId(id);
        tickets.clear();
        tickets.addAll(eventTickets);
    }

    public void createTicket(Ticket ticket) {
        try {
            bll.createTicket(ticket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void batchCreateTickets(int records, int eventId, String type) {
        try {
            bll.batchCreateTickets(records, eventId, type);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void updateCustomerToTicket(Ticket ticket, String customerName, String customerEmail) {
        Ticket updatedTicket = new Ticket(ticket.getUuid(), ticket.getEventId(), ticket.getType(), customerName, customerEmail);
        try {
            bll.updateTicket(updatedTicket);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList getTicketTypes(int id) {
        return bll.getTicketTypes(id);
    }
    public ObservableList getVoucherTypes(int id) {
        return bll.getVoucherTypes(id);
    }
    }

