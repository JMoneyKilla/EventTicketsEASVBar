package dk.javahandson.bll;


import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.internet.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;

public class emailSender {
    //https://stackoverflow.com/questions/28471326/how-to-open-mail-in-draft-and-attach-file-to-mail-using-java
    //https://stackoverflow.com/questions/157195/create-a-eml-email-file-in-java
    //https://stackoverflow.com/questions/11434094/attachment-to-email-java
    public static void main(String[] args) {
        emailSender eS = new emailSender();
        File file = new File("resources/Pictures/easv.png");
        eS.sendEmail("madsp@hotmail.dk",  "very important subject", "big body of great test indeed yes", file);

    }

    public void sendEmail(String recipient, String subject, String body, File attachmentFile) {
        try {

            Message message = new MimeMessage(Session.getInstance(System.getProperties()));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            // create the message part
            MimeBodyPart content = new MimeBodyPart();
            // fill message
            content.setText(body);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(content);

             //add attachments
            File file = attachmentFile;
            MimeBodyPart attachment = new MimeBodyPart();
            DataSource source = new FileDataSource(file);
            attachment.setDataHandler(new DataHandler(source));
            attachment.setFileName(file.getName());
            multipart.addBodyPart(attachment);


            // integration
            message.setContent(multipart);
            // store file


            message.writeTo(System.out);
            message.writeTo(new FileOutputStream("resources\\test.eml"));
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File("resources\\test.eml"));
            desktop.mail();

        } catch (AddressException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

