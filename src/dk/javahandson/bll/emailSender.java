package dk.javahandson.bll;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.Properties;

public class emailSender {

    public static void main(String[] args) {
        emailSender eS = new emailSender();
        File file = new File(("resources/Pictures/easv.png"));
        //eS.sendEmailWithAttachment("mail@hotmail.dk",  "very important subject", "big body of great test indeed yes", file);
        try {
            try {
                eS.sendEmail("mail@hotmail.dk",  "very important subject", "big body of great test indeed yes", file);
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

    public void sendEmailWithAttachment(String recipient, String subject, String body, String attachment) {
        try {
            Desktop desktop = Desktop.getDesktop();
            desktop.mail(new URI("mailto:" + recipient + "?subject=" + URLEncoder.encode(subject, "UTF-8") + "&body=" + URLEncoder.encode(body, "UTF-8") + "&attachment=" + URLEncoder.encode(attachment, "UTF-8")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public void sendEmail(String recipient, String subject, String body, File attachmentFile) throws MessagingException, URISyntaxException, IOException {



        // Set mail properties
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "localhost");
        properties.setProperty("mail.smtp.port", "25");

        // Create session
        Session session = Session.getDefaultInstance(properties);

        // Create message
        Message message = new MimeMessage(session);

        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
        message.setSubject(subject);

        // Create email body
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(body);

        // Create attachment
        BodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(attachmentFile);
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(attachmentFile.getName());

        // Add body and attachment to email
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);
        multipart.addBodyPart(attachmentPart);
        message.setContent(multipart);

        // Save email as draft
        File emlFile = File.createTempFile("email", ".eml");
        FileOutputStream emlOut = new FileOutputStream(emlFile);
        message.writeTo(emlOut);
        emlOut.close();

        // Open email as draft
        Desktop desktop = Desktop.getDesktop();
        message.setFlag(Flags.Flag.DRAFT, true);

        desktop.open(emlFile);

    }

}






