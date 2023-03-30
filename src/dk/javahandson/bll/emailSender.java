package dk.javahandson.bll;


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
        File file = new File("C:\\Users\\Mads\\Pictures\\easv.png");
        eS.sendEmail("madsp@hotmail.dk",  "test", "test", file);

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
            /*
            // add attachments
            File file = attachmentFile;
                MimeBodyPart attachment = new MimeBodyPart();
                DataSource source = new FileDataSource(file);
                attachment.setDataHandler(new DataHandler(source));
                attachment.setFileName(file.getName());
                multipart.addBodyPart(attachment);
            */
            // integration
            message.setContent(multipart);
            // store file


            message.writeTo(System.out);
            message.writeTo(new FileOutputStream("C:\\Users\\madsp\\Documents\\test.eml"));
            Desktop desktop = Desktop.getDesktop();

            desktop.mail(convertEmlToUri("C:\\Users\\madsp\\Documents\\test.eml"));

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
    public static URI convertEmlToUri(String filePath) throws Exception {
        // Read the contents of the EML file
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[(int) file.length()];
        fis.read(data);
        fis.close();

        // Encode the contents as a URI
        String encodedData = URLEncoder.encode(new String(data), "UTF-8");
        URI uri = new URI("data", "message/rfc822;charset=utf-8," + encodedData, null);

        return uri;
    }
}

