package dk.javahandson.dal;

import dk.javahandson.be.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class EventDAO {
<<<<<<< Updated upstream
=======
    DataBaseConnection dbc = DataBaseConnection.getInstance();

    public List<Event> getAllEvents() throws SQLException {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT * FROM Event";
        try(Connection connection = dbc.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String eventName = rs.getString("event_name");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                String location = rs.getString("location");
                String notes = rs.getString("notes");
                int ticketsSold = rs.getInt("tickets_sold");
                int vouchersUsed = rs.getInt("vouchers_used");
                Event event = new Event(id, eventName, startTime, endTime, location, notes, ticketsSold, vouchersUsed);
                allEvents.add(event);
            }
        }
        return allEvents;
    }
    public List<Event> getCoordinatorEvents(User u) throws SQLException {
        List<Event> coordinatorEvents = new ArrayList<>();
        int userId = u.getId();
        String sql = "SELECT Event.[id], [event_id], [user_id], [event_name], [start_time], [end_time], [location], [notes], [tickets_sold],[vouchers_used]\n" +
                "    FROM Event\n" +
                "    INNER JOIN UserEvent\n" +
                "    ON Event.id = UserEvent.event_id\n" +
                "    INNER JOIN [User]\n" +
                "    ON UserEvent.user_id = [User].id\n" +
                "    WHERE [user_id] = " + userId + ";";
        try(Connection connection = dbc.getConnection()){
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String eventName = rs.getString("event_name");
                String startTime = rs.getString("start_time");
                String endTime = rs.getString("end_time");
                String location = rs.getString("location");
                String notes = rs.getString("notes");
                int ticketsSold = rs.getInt("tickets_sold");
                int vouchersUsed = rs.getInt("vouchers_used");
                Event event = new Event(id, eventName, startTime, endTime, location, notes, ticketsSold, vouchersUsed);
                coordinatorEvents.add(event);
            }
            return coordinatorEvents;
        }
    }
    public void createEvent(Event event) {
        String sql = "INSERT INTO Event (event_name, start_time, end_time, location, notes, total_tickets, tickets_sold, total_vouchers, vouchers_used) VALUES (?,?,?,?,?,?,?)";
        String name = event.getName();
        String startTime = event.getStartTime();
        String endTime = event.getStartTime();
        String location = event.getLocation();
        String notes = event.getNotes();
        int ticketsSold = event.getTicketsSold();
        int voucherUsed = event.getVoucherUsed();


        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,startTime);
            ps.setString(3,endTime);
            ps.setString(4,location);
            ps.setString(5,notes);
            ps.setInt(6,ticketsSold);
            ps.setInt(7,voucherUsed);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean deleteEvent(Event event) throws SQLException {
        try(Connection con = dbc.getConnection()) {
            int id = event.getId();
            String sql ="DELETE FROM Event WHERE (id=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int result =ps.executeUpdate();
            if(result > 0)
                return true;
        }
        return false;
    }
    public void updateEvent(Event event) throws SQLException {
        int id = event.getId();
        String name = event.getName();
        String startTime = event.getStartTime();
        String endTime = event.getStartTime();
        String location = event.getLocation();
        String notes = event.getNotes();
        int ticketsSold = event.getTicketsSold();
        int voucherUsed = event.getVoucherUsed();


        String sql = "UPDATE Event SET name = ?, start_time = ?, end_time = ?, location = ?, notes = ?, tickets_sold = ?, voucers_used WHERE id = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,startTime);
            ps.setString(3,endTime);
            ps.setString(4,location);
            ps.setString(5,notes);
            ps.setInt(6,ticketsSold);
            ps.setInt(7,voucherUsed);
            ps.setInt(8,id);
            ps.execute();

        }
    }
>>>>>>> Stashed changes
}
