package dk.javahandson.bll.helpers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dk.javahandson.be.Ticket;
import dk.javahandson.be.Voucher;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.util.Random;

public class TicketGenerator{


    public void createCompleteTicket(Ticket ticket){
        String uuid = ticket.getUuid();
        String customer = ticket.getCustomer();
        int eventId = ticket.getEventId();
        createImageToSend(createQRCode(uuid, customer, eventId), customer, eventId);
    }

    public void createCompleteVoucher(Voucher voucher){
        Random rd = new Random();
        int rdInt = rd.nextInt(5000);
        String uuid = voucher.getUuid();
        String voucherName = rdInt + voucher.getType();
        int eventId = voucher.getEventId();
        createImageToSend(createQRCode(uuid, voucherName, eventId), voucherName, eventId);
    }

    public File createQRCode(String uuid, String customerName, int eventID) {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = null;

        try {
            bitMatrix = qrCodeWriter.encode(uuid, BarcodeFormat.QR_CODE, 300, 300);
        } catch (WriterException e){
            System.out.println("Failed to generate QR code :(");
        }

        String dir = getClass().getResource("/").getFile() + "Pictures/QRCodes/";
        File dirCheck = new File(dir);
        if(!dirCheck.exists()) dirCheck.mkdirs();

        String fileName = customerName + eventID + "QRCode.png";
        File qrFile = new File(dir + fileName);
        System.out.println(qrFile.getAbsolutePath());

        try{
            ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", qrFile.getAbsoluteFile());
        } catch (IOException e){
            System.out.println("Failed to save QR code :((");
        }
        return qrFile;
    }

    public static void main(String[] args) {
        Ticket ticket = new Ticket("550e8400-e29b-41d4-a716-446655440000", 19, "VIP", "Julian", "julian@mail.dk");
        TicketGenerator ticketGenerator = new TicketGenerator();
        ticketGenerator.createCompleteTicket(ticket);
    }


    public void createImageToSend(File qrFile, String customerName, int eventID) {
        BufferedImage qrImage = null;
        try {
            qrImage = ImageIO.read(qrFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        BufferedImage ticketImage = new BufferedImage(300, 600, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = ticketImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,300,600);

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        String eventInfo = "Ticket: \n" + customerName + "\n" + eventID;
        g2d.drawString(eventInfo, 50, 50);

        g2d.drawImage(qrImage, 300-qrImage.getWidth(), 600-qrImage.getHeight(), null);


        String dir1 = getClass().getResource("/").getFile() + "Pictures/Tickets/";
        File dirCheck1 = new File(dir1);
        if(!dirCheck1.exists()) dirCheck1.mkdirs();

        String fileName1 = customerName + eventID + "Ticket.png";
        File qrFile1 = new File(dir1 + fileName1);
        try {
            ImageIO.write(ticketImage, "png", qrFile1.getAbsoluteFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
