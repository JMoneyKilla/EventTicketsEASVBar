package dk.javahandson.dal;

import dk.javahandson.be.Ticket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TicketDAO {
    DataBaseConnection dbc = new DataBaseConnection();

    //might need more params for price, type and redeemable
    private void createTicket(Ticket ticket) {
        String sql = "INSERT INTO Ticket (event_id, customer_name, customer_email) VALUES (?,?,?)";
        String customer = ticket.getCustomer();
        String customerEmail = ticket.getCustomerEmail();
        int eventID = ticket.getEventId();


        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,eventID);
            ps.setString(2,customer);
            ps.setString(3,customerEmail);

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteTicket(Ticket ticket) {
        int id = ticket.getId();
        String sql ="DELETE FROM Ticket WHERE id=?";

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
