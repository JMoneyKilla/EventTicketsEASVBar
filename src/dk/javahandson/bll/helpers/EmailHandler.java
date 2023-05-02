package dk.javahandson.bll.helpers;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class EmailHandler {


    //Began implementation but never got working product
    public void openEmail(String recipient, String event){
        try {
            String body = "Thank you for buying tickets for " + event + ". We hope you enjoy the event!";
            String subject = "Event tickets for" + event;
            Desktop desktop = Desktop.getDesktop();
            URI uri = URI.create("mailto:" + recipient + "?subject=" + subject + "&body=" + body);
            desktop.mail(uri);

    } catch (IOException e) {
            throw new RuntimeException(e);
        }
}   }

