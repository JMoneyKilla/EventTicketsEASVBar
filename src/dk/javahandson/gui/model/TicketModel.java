package dk.javahandson.gui.model;


import dk.javahandson.be.Ticket;
import dk.javahandson.bll.ManagerFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TicketModel {

    private final ObservableList<Ticket> tickets;
    private ManagerFacade bll = new ManagerFacade();

    public TicketModel() {
        tickets = FXCollections.observableArrayList();
    }


    public void fetchAllTickets()
    {
        tickets.clear();

    }
    public void createTicket(Ticket ticket)
    {
        try {
            bll.createTicket(ticket);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
