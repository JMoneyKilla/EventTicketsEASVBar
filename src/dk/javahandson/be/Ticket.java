package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
    private StringProperty uuid =  new SimpleStringProperty();
    private IntegerProperty eventId =  new SimpleIntegerProperty();
    private StringProperty customer = new SimpleStringProperty();
    private StringProperty customerEmail = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();

    public Ticket(String uuid, int eventId, String type, String customer, String customerEmail){
        setUuid(uuid);
        setEventId(eventId);
        setType(type);
        setCustomer(customer);
        setCustomerEmail(customerEmail);
    }

    public void sellTicket(Ticket ticket, String customer, String customerEmail){
        ticket.setCustomer(customer);
        ticket.setCustomerEmail(customerEmail);
    }

    public void setUuid(String uuid) {
        this.uuid.set(uuid);
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public String getUuid() {
        return uuid.get();
    }

    public StringProperty uuidProperty() {
        return uuid;
    }

    public void setId(String uuid) {
        this.uuid.set(uuid);
    }

    public int getEventId() {
        return eventId.get();
    }

    public IntegerProperty eventIdProperty() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId.set(eventId);
    }

    public String getCustomer() {
        return customer.get();
    }

    public StringProperty customerProperty() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer.set(customer);
    }

    public String getCustomerEmail() {
        return customerEmail.get();
    }

    public StringProperty customerEmailProperty() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail.set(customerEmail);
    }
}
