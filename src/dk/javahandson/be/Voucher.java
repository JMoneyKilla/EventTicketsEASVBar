package dk.javahandson.be;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Voucher {

    private StringProperty uuid = new SimpleStringProperty();
    private IntegerProperty eventId =  new SimpleIntegerProperty();
    private StringProperty type = new SimpleStringProperty();
    private String eventName = "";
    public Voucher(String uuid, int eventId, String type){
        setUuid(uuid);
        setType(type);
        setEventId(eventId);
    }

    public String getUuid() {
        return uuid.get();
    }

    public StringProperty uuidProperty() {
        return uuid;
    }

    public void setUuid(String uuid) {
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

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public void setType(String type) {
        this.type.set(type);
    }
    public void setEventName(String eventName){
        this.eventName = eventName;
    }
}
