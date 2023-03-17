package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty time;
    private StringProperty location;
    private StringProperty notes;
    private IntegerProperty totalTickets;
    private IntegerProperty ticketsSold;
    private IntegerProperty totalVouchers;
    private IntegerProperty voucherUsed;

    public Event(int id,String name,String time, String location, String notes, int totalTickets, int ticketsSold, int totalVouchers, int voucherUsed){
    setId(id);
    setName(name);
    setTime(time);
    setLocation(location);
    setNotes(notes);
    setTotalTickets(totalTickets);
    setTicketsSold(ticketsSold);
    setTotalVouchers(totalVouchers);
    setVoucherUsed(voucherUsed);
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getTime() {
        return time.get();
    }

    public StringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getLocation() {
        return location.get();
    }

    public StringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getNotes() {
        return notes.get();
    }

    public StringProperty notesProperty() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes.set(notes);
    }

    public int getTotalTickets() {
        return totalTickets.get();
    }

    public IntegerProperty totalTicketsProperty() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets.set(totalTickets);
    }

    public int getTicketsSold() {
        return ticketsSold.get();
    }

    public IntegerProperty ticketsSoldProperty() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold.set(ticketsSold);
    }

    public int getTotalVouchers() {
        return totalVouchers.get();
    }

    public IntegerProperty totalVouchersProperty() {
        return totalVouchers;
    }

    public void setTotalVouchers(int totalVouchers) {
        this.totalVouchers.set(totalVouchers);
    }

    public int getVoucherUsed() {
        return voucherUsed.get();
    }

    public IntegerProperty voucherUsedProperty() {
        return voucherUsed;
    }

    public void setVoucherUsed(int voucherUsed) {
        this.voucherUsed.set(voucherUsed);
    }
}
