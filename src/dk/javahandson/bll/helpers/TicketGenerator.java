package dk.javahandson.bll.helpers;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import dk.javahandson.be.Event;
import dk.javahandson.be.Ticket;
import dk.javahandson.be.Voucher;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;

public class TicketGenerator{

    /**
     * Creates an image of completed ticket
     * @param ticket Ticket with uuid you want to create qqr code for
     * @param event Event that the ticket is being made for
     * @throws SQLException
     */
    public void createCompleteTicket(Ticket ticket, Event event) throws SQLException {
        String uuid = ticket.getUuid();
        String customer = ticket.getCustomer();
        int eventId = ticket.getEventId();
        createImageToSend(createQRCode(uuid, customer, eventId), event, customer);
    }

    /**
     * Creates an image of completed voucher
     * @param voucher
     * @param event
     * @throws SQLException
     */
    public void createCompleteVoucher(Voucher voucher, Event event) throws SQLException {
        Random rd = new Random();
        String uuid = voucher.getUuid();
        String voucherName = voucher.getType() + "_" + event.getName() + "_" + rd.nextInt(10000);
        int eventId = voucher.getEventId();
        createImageToSend(createQRCode(uuid, voucherName, eventId), event, voucherName);
    }

    /**
     *
     * @param uuid unique id used for qr code generation
     * @param custEvent string created from customer and event name to generate file name
     * @param eventID also used for generating file name
     * @return File of qr code
     */
    public File createQRCode(String uuid, String custEvent, int eventID) {
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

        String fileName = custEvent + "_" + eventID + "QRCode.png";
        File qrFile = new File(dir + fileName);

        try{
            ImageIO.write(MatrixToImageWriter.toBufferedImage(bitMatrix), "png", qrFile.getAbsoluteFile());
        } catch (IOException e){
            System.out.println("Failed to save QR code :((");
        }
        return qrFile;
    }

    /**
     * creates final Image of ticket/voucher and puts it into the out folder
     * @param qrFile file with qr code generated from uuid
     * @param event used to fill information out on ticket/voucher image
     * @param customerName name of customer
     */
    public void createImageToSend(File qrFile, Event event, String customerName) {
        String eventName = event.getName();
        String dateStart = event.getStartDate();
        String timeStart = event.getStartTime();
        String dateEnd = event.getEndDate();
        String timeEnd = event.getEndTime();
        int eventID = event.getId();
        BufferedImage qrImage = null;
        BufferedImage easvLogo = null;
        try {
            qrImage = ImageIO.read(qrFile);
            easvLogo = ImageIO.read(new File("resources/Pictures/easvLogo.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int x = 530;
        int y = 740;
        BufferedImage ticketImage = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);

        //places all graphics for image of ticket
        Graphics2D g2d = ticketImage.createGraphics();
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0,0,x,y);
        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 28));
        g2d.drawString(eventName, (x/2)-(eventName.length()*7), 165);
        g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 24));
        g2d.drawString("START: " + dateStart, 150, 244);
        g2d.drawString("     At: " + timeStart, 168, 284);
        g2d.drawString("END: " + dateEnd, 165, 324);
        g2d.drawString("     At: " + timeEnd, 168, 364);
        g2d.drawString("Contact us:", 50, y- qrImage.getHeight());
        g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 16));
        g2d.drawString("Email:", 50, (y-qrImage.getHeight())+35);
        g2d.drawString("Phone:", 50, (y-qrImage.getHeight())+105);
        g2d.setFont(new Font("Arial", Font.CENTER_BASELINE, 12));
        g2d.drawString("+45 65 69 42 01", 50, (y-qrImage.getHeight())+140);
        g2d.drawString("ouremail@mailsource.dk", 50, (y-qrImage.getHeight())+70);
        g2d.setFont(new Font("Arial", Font.ITALIC, 10));
        g2d.drawString("This ticket belongs to...", 50, y-80);
        g2d.drawString(customerName, 50, y-50);

        g2d.drawImage(easvLogo, 0, 0, null);
        g2d.drawImage(qrImage, x-qrImage.getWidth(), y-qrImage.getHeight(), null);


        String dir = getClass().getResource("/").getFile() + "Pictures/Tickets/";
        File dirCheck = new File(dir);
        if(!dirCheck.exists()) dirCheck.mkdirs();

        String fileName = customerName + "_" + eventName + "_" + eventID + "Ticket.png";
        File ticketFile = new File(dir + fileName);
        try {
            ImageIO.write(ticketImage, "png", ticketFile.getAbsoluteFile());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
