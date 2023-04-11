package dk.javahandson.bll;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;

public class EmailHandler {
    private String toEmail;
    private String subject;
    private String body;
    private File attachment;

    public EmailHandler(String toEmail, String subject, String body, File attachment) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.body = body;
        this.attachment = attachment;
    }

    public static void main(String[] args) {
        File file = new File(("resources/Pictures/easv.png"));
        EmailHandler eS = new EmailHandler("julianhesberg@gmail.com", "1234567890",  "big", file);
        eS.sendEmail();

    }

    public void send() throws IOException, URISyntaxException, URISyntaxException {
        // Create a mailto URI with the recipient and subject
        URI uri = new URI("mailto:" + URLEncoder.encode(toEmail, StandardCharsets.UTF_8) + "?subject=" + subject);

        // Create a new StringBuilder object to hold the email body
        StringBuilder builder = new StringBuilder();

        // Add the body text to the StringBuilder
        builder.append(body);

        // If an attachment file is provided, add it to the StringBuilder
        if (attachment != null) {
            builder.append("\n\n");

            // Read the attachment file into a byte array
            byte[] attachmentBytes = Files.readAllBytes(attachment.toPath());

            // Encode the attachment as a base64 string
            String base64Attachment = Base64.getEncoder().encodeToString(attachmentBytes);

            // Create a data URI for the attachment
            String attachmentDataUri = "data:image/png;base64," + base64Attachment;

            // Add the data URI to the email body
            builder.append("<img src=\"" + attachmentDataUri + "\">");
        }

        // Convert the StringBuilder to a String
        String mailBody = builder.toString();

        // Encode the mail body as a URI component
        String encodedMailBody = URLEncoder.encode(mailBody);

        // Add the encoded mail body to the mailto URI
        uri = new URI(uri + "&body=" + encodedMailBody);

        // Use the Desktop class to open the default mail client with the mailto URI
        Desktop.getDesktop().mail(uri);
    }
    public void sendEmail() {
        try {
            // Open default email app
            Desktop desktop = Desktop.getDesktop();
            if (!desktop.isSupported(Desktop.Action.MAIL)) {
                System.out.println("Email is not supported on this system.");
                return;
            }

            // Prepare mailto URI
            String uriStr = "mailto:" + toEmail + "?subject=" + subject + "&body=" + body;
            URI uri = new URI(uriStr);

            // Attach file
            if (attachment.exists())
                //desktop.mail(uri).(attachment);
                return;
            else {
                // Open email app without attachment
                desktop.mail(uri);
            }
        } catch (IOException e) {
            System.out.println("Failed to open email client.");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            System.out.println("Invalid email address.");
            e.printStackTrace();
        }
    }
}
