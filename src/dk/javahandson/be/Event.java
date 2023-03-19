package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private IntegerProperty id;
    private StringProperty name;
    private StringProperty startTime;
    private StringProperty endtime;
    private StringProperty location;
    private StringProperty notes;
    private IntegerProperty totalTickets;
    private IntegerProperty ticketsSold;
    private IntegerProperty totalVouchers;
    private IntegerProperty voucherUsed;

    public Event(int id,String name,String startTime, String endtime, String location, String notes, int totalTickets, int ticketsSold, int totalVouchers, int voucherUsed){
    setId(id);
    setName(name);
    setStartTime(startTime);
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

    public String getStartTime() {
        return startTime.get();
    }

    public StringProperty startTimeProperty() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
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

    public String getEndtime() {
        return endtime.get();
    }

    public StringProperty endtimeProperty() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime.set(endtime);
    }
}
