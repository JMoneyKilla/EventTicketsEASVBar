package dk.javahandson.bll.helpers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dk.javahandson.be.Ticket;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class TicketGenerator{

    public void createQRCode(Ticket ticket) {
        String uuid = ticket.getUuid();
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;

        try {
            bitMatrix = qrCodeWriter.encode(uuid, BarcodeFormat.QR_CODE, 300, 300);
        } catch (WriterException e){
            System.out.println("Failed to generate QR code :(");
        }

        ClassLoader classLoader = TicketGenerator.class.getClassLoader();
        String resourcePath = "/Pictures/Tickets/";
        String filePath = "C:\\Users\\Julian\\IdeaProjects\\EventTicketsEASVBar\\resources\\Pictures\\Tickets";
        String fileName = ticket.getCustomer() + ticket.getEventId() + "QRCode.png";
        File qrFile = new File(filePath + fileName);

        try{
            ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", qrFile);
        } catch (IOException e){
            System.out.println("Failed to save QR code :((");
        }
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket("550e8400-e29b-41d4-a716-446655440000", 19, "VIP", "Julian", "julian@mail.dk");
        TicketGenerator ticketGenerator = new TicketGenerator();
        ticketGenerator.createQRCode(ticket);
    }


    public void createImageToSend() {

    }
}
