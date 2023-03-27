package dk.javahandson.dal;
import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class EventDAO {
    DataBaseConnection dbc = DataBaseConnection.getInstance();

    public List<Event> getAllEvents() throws SQLException {
        List<Event> allEvents = new ArrayList<>();
        String sql = "SELECT * FROM Event";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String eventName = rs.getString("event_name");
                String startDate = rs.getString("start_date");
                String startTime = rs.getString("start_time");
                String endDate = rs.getString("end_date");
                String endTime = rs.getString("end_time");
                String location = rs.getString("location");
                String notes = rs.getString("notes");
                int ticketsSold = rs.getInt("tickets_sold");
                int vouchersUsed = rs.getInt("vouchers_used");
                int totalTickets = rs.getInt("total_tickets");
                int totalVouchers = rs.getInt("total_vouchers");
                Event event = new Event(id, eventName, startTime, endTime, location,
                        notes, ticketsSold, vouchersUsed, totalTickets, totalVouchers, startDate, endDate);
                allEvents.add(event);
            }
        }
        return allEvents;
    }

    public List<Event> getCoordinatorEvents(User u) throws SQLException {
        List<Event> coordinatorEvents = new ArrayList<>();
        int userId = u.getId();
        String sql = "SELECT Event.[id], [event_id], [user_id], [event_name], [start_date], [start_time], [end_date], [end_time], " +
                "[location], [notes], [tickets_sold],[vouchers_used], [total_tickets], [total_vouchers]\n" +
                "    FROM Event\n" +
                "    INNER JOIN UserEvent\n" +
                "    ON Event.id = UserEvent.event_id\n" +
                "    INNER JOIN [User]\n" +
                "    ON UserEvent.user_id = [User].id\n" +
                "    WHERE [user_id] = " + userId + ";";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String eventName = rs.getString("event_name");
                String startDate = rs.getString("start_date");
                String startTime = rs.getString("start_time");
                String endDate = rs.getString("end_date");
                String endTime = rs.getString("end_time");
                String location = rs.getString("location");
                String notes = rs.getString("notes");
                int ticketsSold = rs.getInt("tickets_sold");
                int vouchersUsed = rs.getInt("vouchers_used");
                int totalTickets = rs.getInt("total_tickets");
                int totalVouchers = rs.getInt("total_vouchers");
                Event event = new Event(id, eventName, startTime, endTime, location,
                        notes, ticketsSold, vouchersUsed, totalTickets, totalVouchers, startDate, endDate);
                coordinatorEvents.add(event);
            }
            return coordinatorEvents;
        }
    }

    public void createEvent(Event event) throws SQLException {
        String sql = "INSERT INTO Event (event_name, start_date, start_time, end_date, end_time, " +
                "location, notes, tickets_sold, vouchers_used, total_tickets, total_vouchers)" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        String name = event.getName();
        String startDate = event.getStartDate();
        String startTime = event.getStartTime();
        String endDate = event.getEndDate();
        String endTime = event.getStartTime();
        String location = event.getLocation();
        String notes = event.getNotes();
        int ticketsSold = event.getTicketsSold();
        int voucherUsed = event.getVoucherUsed();
        int totalTickets = event.getTotalTickets();
        int totalVouchers = event.getTotalVouchers();


        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, startDate);
            ps.setString(3, startTime);
            ps.setString(4, endDate);
            ps.setString(5, endTime);
            ps.setString(6, location);
            ps.setString(7, notes);
            ps.setInt(8, ticketsSold);
            ps.setInt(9, voucherUsed);
            ps.setInt(10, totalTickets);
            ps.setInt(11, totalVouchers);
            ps.execute();
        }
    }

    public static void main(String[] args) throws SQLException {
        Event event = new Event("", "14:00", "19:00", "Svømmestadion Esbjerg",
                "Bring swimming clothes and a towel. Pizza provided!", 0, 0, 30, 0,
                "01-04-2023", "01-04-2023");
        DataAccessFacade dataAccessFacade = new DataAccessFacade();
        dataAccessFacade.createEvent(event);
    }

    public boolean deleteEvent(Event event) throws SQLException {
        try (Connection con = dbc.getConnection()) {
            int id = event.getId();
            String sql = "DELETE FROM Event WHERE (id=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            int result = ps.executeUpdate();
            if (result > 0)
                return true;
        }
        return false;
    }

    public void updateEvent(Event event) throws SQLException {
        int id = event.getId();
        String name = event.getName();
        String startDate = event.getStartDate();
        String startTime = event.getStartTime();
        String endDate = event.getEndDate();
        String endTime = event.getEndtime();
        String location = event.getLocation();
        String notes = event.getNotes();
        int ticketsSold = event.getTicketsSold();
        int voucherUsed = event.getVoucherUsed();
        int totalTickets = event.getTotalTickets();
        int totalVouchers = event.getTotalVouchers();


        String sql = "UPDATE Event SET event_name = ?, start_date = ?, start_time = ?, end_date = ?, end_time = ?, " +
                "location = ?, notes = ?, tickets_sold = ?, vouchers_used = ? total_tickets = ?, " +
                "total_vouchers = ? WHERE id = ?;";
        try (Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, startDate);
            ps.setString(3, startTime);
            ps.setString(4, endDate);
            ps.setString(5, endTime);
            ps.setString(6, location);
            ps.setString(7, notes);
            ps.setInt(8, ticketsSold);
            ps.setInt(9, voucherUsed);
            ps.setInt(10, totalTickets);
            ps.setInt(11, totalVouchers);
            ps.setInt(12, id);
            ps.execute();

        }
    }
    public int  getEventId(String title) throws SQLException {
        String sql = "SELECT id FROM Event WHERE event_name = ?";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, title);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        }
        return 0;
    }
}
