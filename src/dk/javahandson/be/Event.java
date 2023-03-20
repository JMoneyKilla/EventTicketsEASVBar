package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Event {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private StringProperty startTime = new SimpleStringProperty();
    private StringProperty endtime = new SimpleStringProperty();
    private StringProperty location = new SimpleStringProperty();
    private StringProperty notes = new SimpleStringProperty();
    private IntegerProperty ticketsSold = new SimpleIntegerProperty();
    private IntegerProperty voucherUsed = new SimpleIntegerProperty();

    public Event(int id,String name,String startTime, String endtime, String location, String notes, int ticketsSold, int voucherUsed){
    setId(id);
    setName(name);
    setStartTime(startTime);
    setLocation(location);
    setNotes(notes);
    setTicketsSold(ticketsSold);
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


    public int getTicketsSold() {
        return ticketsSold.get();
    }

    public IntegerProperty ticketsSoldProperty() {
        return ticketsSold;
    }

    public void setTicketsSold(int ticketsSold) {
        this.ticketsSold.set(ticketsSold);
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
