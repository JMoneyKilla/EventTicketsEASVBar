package dk.javahandson.bll;

import java.awt.*;
import java.io.IOException;
import java.net.URI;

public class emailSender {

    public void sendEmail(String recipiente, String subject, String body){
        try {

            Desktop desktop = Desktop.getDesktop();
            URI uri = URI.create("mailto:" + recipiente + "?subject=" + subject + "&body=" + body);
            desktop.mail(uri);

    } catch (IOException e) {
            throw new RuntimeException(e);
        }
}   }

