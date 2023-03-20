package dk.javahandson.dal;

import dk.javahandson.be.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TicketDAO {
    DataBaseConnection dbc = new DataBaseConnection();

    public static void main(String[] args) {
        Ticket ticket = new Ticket(2,2, "VIP","" ,"");
        TicketDAO dao = new TicketDAO();
        dao.deleteTicket(ticket);
    }
    //might need more params for price, type and redeemable
    private void createTicket(Ticket ticket) {

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

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTicket(Ticket ticket) {
        int id = ticket.getId();
        String sql ="DELETE FROM Ticket WHERE event_id=?";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateTicket(Ticket ticket){
        int id = ticket.getId();
        String customer = ticket.getCustomer();
        String customerEmail = ticket.getCustomerEmail();
        int eventID = ticket.getEventId();


        String sql = "UPDATE Ticket SET event_id = ?, customer_name = ?, customer_email = ? WHERE id = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,eventID);
            ps.setString(2,customer);
            ps.setString(3,customerEmail);
            ps.setInt(4,id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void redemtionArcTicket(Ticket ticket){
        int id = ticket.getId();

        String sql = "UPDATE Ticket SET redeemable = ? WHERE id = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setBoolean(1,false);
            ps.setInt(2,id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
