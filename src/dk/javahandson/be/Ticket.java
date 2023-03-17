package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
    private IntegerProperty id;
    private IntegerProperty eventId;
    private StringProperty customer;
    private StringProperty customerEmail;

    public Ticket(int id, int eventId, String customer, String customerEmail){
        setId(id);
        setEventId(eventId);
        setCustomer(customer);
        setCustomerEmail(customerEmail);

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
