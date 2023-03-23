package dk.javahandson.dal;

import dk.javahandson.be.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    DataBaseConnection dbc = DataBaseConnection.getInstance();

    //might need more params for price, type and redeemable
    public void createTicket(Ticket ticket) throws SQLException {

        try(Connection con = dbc.getConnection();) {
            String sql = "INSERT INTO Ticket (uuid, event_id, type, customer_name, customer_email, redeemable) VALUES (?,?,?,?,?,?)";
            String uuid = "0000-aaaa-1111-bbbb"; //this will be generated
            String customer = ticket.getCustomer();
            String customerEmail = ticket.getCustomerEmail();
            String type = "VIP";
            int eventID = ticket.getEventId();
            Boolean redeemable = true;
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,uuid);
            ps.setInt(2,eventID);
            ps.setString(3,type);
            ps.setString(4,customer);
            ps.setString(5,customerEmail);
            ps.setBoolean(6,redeemable);

            ps.execute();

        }
    }


    public boolean deleteTicket(Ticket ticket) throws SQLException {
        String uuid = ticket.getUuid();
        String sql ="DELETE FROM Ticket WHERE uuid=?";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,uuid);
            int result =ps.executeUpdate();
            if(result > 0)
                return true;
        }
        return false;
    }
    public void updateTicket(Ticket ticket) throws SQLException {
        String uuid = ticket.getUuid();
        String customer = ticket.getCustomer();
        String customerEmail = ticket.getCustomerEmail();
        int eventID = ticket.getEventId();
        String sql = "UPDATE Ticket SET event_id = ?, customer_name = ?, customer_email = ? WHERE uuid = ?;";    
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,eventID);
            ps.setString(2,customer);
            ps.setString(3,customerEmail);
            ps.setString(4,uuid);
            ps.execute();

        }
    }
    
    public boolean redeemTicket(Ticket ticket) throws SQLException {
        String uuid = ticket.getUuid();

        String sql = "UPDATE Ticket SET redeemable = ? WHERE uuid = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,false);
            ps.setString(2,uuid);
            ps.execute();
            return true;

        }

    }
    public List<Ticket> getAllTickets(){
        Ticket ticket;
        List<Ticket> allTickets = new ArrayList<>();

        String sql = "SELECT * FROM Ticket";
        try(Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String uuid = rs.getString("uuid");
                int eventId = rs.getInt("event_id");
                String type = rs.getString("type");
                String customer = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");
                ticket = new Ticket(uuid, eventId, type, customer, customerEmail);
                allTickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allTickets;
    }
    public List<Ticket> getTicketsByEventId(int id){
        Ticket ticket;
        List<Ticket> allTickets = new ArrayList<>();

        String sql = "SELECT * FROM Ticket WHERE event_id = ? AND customer_name = null";
        try(Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String uuid = rs.getString("uuid");
                int eventId = rs.getInt("event_id");
                String type = rs.getString("type");
                String customer = rs.getString("customer_name");
                String customerEmail = rs.getString("customer_email");
                ticket = new Ticket(uuid, eventId, type, customer, customerEmail);
                allTickets.add(ticket);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allTickets;
    }


   
}
