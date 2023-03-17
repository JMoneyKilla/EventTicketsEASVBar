package dk.javahandson.dal;

import dk.javahandson.be.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EventDAO {
    DataBaseConnection dbc = new DataBaseConnection();
    private void createEvent(Event event) {
        String sql = "INSERT INTO Event (id, name, time, location, notes, total_tickets, tickets_sold, total_vouchers, voucher_used) VALUES (?,?,?,?,?,?,?,?)";
        String name = event.getName();
        String time = event.getTime();
        String location = event.getLocation();
        String notes = event.getNotes();
        int totalTickets = event.getTotalTickets();
        int ticketsSold = event.getTicketsSold();
        int totalVouchers = event.getTotalVouchers();
        int voucherUsed = event.getVoucherUsed();


        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,time);
            ps.setString(3,location);
            ps.setString(4,notes);
            ps.setInt(5,totalTickets);
            ps.setInt(6,ticketsSold);
            ps.setInt(7,totalVouchers);
            ps.setInt(8,voucherUsed);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteEvent(Event event) {
        int id = event.getId();
        String sql ="DELETE FROM Songs WHERE id=?";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateEvent(Event event){
        int id = event.getId();
        String name = event.getName();
        String time = event.getTime();
        String location = event.getLocation();
        String notes = event.getNotes();
        int totalTickets = event.getTotalTickets();
        int ticketsSold = event.getTicketsSold();
        int totalVouchers = event.getTotalVouchers();
        int voucherUsed = event.getVoucherUsed();


        String sql = "UPDATE Songs SET name = ?, time = ?, location = ?, notes = ?, total_tickets = ?,tickets_sold = ?, total_vouchers = ?, voucers_used WHERE id = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,time);
            ps.setString(3,location);
            ps.setString(4,notes);
            ps.setInt(5,totalTickets);
            ps.setInt(6,ticketsSold);
            ps.setInt(7,totalVouchers);
            ps.setInt(8,voucherUsed);
            ps.setInt(9,id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
