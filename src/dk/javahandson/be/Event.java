package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty startDate = new SimpleStringProperty();
    private StringProperty startTime = new SimpleStringProperty();
    private StringProperty endDate = new SimpleStringProperty();
    private StringProperty endTime = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private StringProperty notes = new SimpleStringProperty();
    private IntegerProperty ticketsSold = new SimpleIntegerProperty();
    private IntegerProperty voucherUsed = new SimpleIntegerProperty();
    private IntegerProperty totalTickets = new SimpleIntegerProperty();
    private IntegerProperty totalVouchers = new SimpleIntegerProperty();

    /**
     * Constructor for Event when you know the event id
     * @param id
     * @param name
     * @param startTime
     * @param endTime
     * @param location
     * @param notes
     * @param ticketsSold
     * @param voucherUsed
     * @param totalTickets
     * @param totalVouchers
     * @param startDate
     * @param endDate
     */
    public Event(int id,String name,String startTime, String endTime, String location, String notes,
                 int ticketsSold, int voucherUsed, int totalTickets, int totalVouchers, String startDate, String endDate){
    setId(id);
    setName(name);
    setStartTime(startTime);
    setEndTime(endTime);
    setLocation(location);
    setNotes(notes);
    setTicketsSold(ticketsSold);
    setVoucherUsed(voucherUsed);
    setTotalTickets(totalTickets);
    setTotalVouchers(totalVouchers);
    setStartDate(startDate);
    setEndDate(endDate);
    }

    /**
     * Constructor for event when event id is not known
     * @param name
     * @param startTime
     * @param endTime
     * @param location
     * @param notes
     * @param ticketsSold
     * @param voucherUsed
     * @param totalTickets
     * @param totalVouchers
     * @param startDate
     * @param endDate
     */
    public Event(String name,String startTime, String endTime, String location, String notes,
                 int ticketsSold, int voucherUsed, int totalTickets, int totalVouchers, String startDate, String endDate){
        setName(name);
        setStartTime(startTime);
        setEndTime(endTime);
        setLocation(location);
        setNotes(notes);
        setTicketsSold(ticketsSold);
        setVoucherUsed(voucherUsed);
        setTotalTickets(totalTickets);
        setTotalVouchers(totalVouchers);
        setStartDate(startDate);
        setEndDate(endDate);
    }


    public int getTotalTickets() {
        return totalTickets.get();
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets.set(totalTickets);
    }

    public int getTotalVouchers() {
        return totalVouchers.get();
    }

    public void setTotalVouchers(int totalVouchers) {
        this.totalVouchers.set(totalVouchers);
    }

    public String getStartDate() {
        return startDate.get();
    }

    public void setStartDate(String startDate) {
        this.startDate.set(startDate);
    }

    public String getEndDate() {
        return endDate.get();
    }

    public void setEndDate(String endDate) {
        this.endDate.set(endDate);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getStartTime() {
        return startTime.get();
    }

    public void setStartTime(String startTime) {
        this.startTime.set(startTime);
    }

    public String getLocation() {
        return location.get();
    }


    public void setLocation(String location) {
        this.location.set(location);
    }

    public String getNotes() {
        return notes.get();
    }


    public void setNotes(String notes) {
        this.notes.set(notes);
    }


    public int getTicketsSold() {
        return ticketsSold.get();
    }


    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold.set(ticketsSold);
    }

    public int getVoucherUsed() {
        return voucherUsed.get();
    }


    public void setVoucherUsed(int voucherUsed) {
        this.voucherUsed.set(voucherUsed);
    }

    public String getEndTime() {
        return endTime.get();
    }

    public void setEndTime(String endTime) {
        this.endTime.set(endTime);
    }
}
