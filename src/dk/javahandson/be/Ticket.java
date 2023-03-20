package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ticket {
    private IntegerProperty id =  new SimpleIntegerProperty();
    private IntegerProperty eventId =  new SimpleIntegerProperty();
    private StringProperty customer = new SimpleStringProperty();
    private StringProperty customerEmail = new SimpleStringProperty();
    private StringProperty type = new SimpleStringProperty();

    public Ticket(int id, int eventId, String type, String customer, String customerEmail){
        setId(id);
        setEventId(eventId);
        setType(type);
        setCustomer(customer);
        setCustomerEmail(customerEmail);
    }

    public void sellTicket(Ticket ticket, String customer, String customerEmail){
        ticket.setCustomer(customer);
        ticket.setCustomerEmail(customerEmail);
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
