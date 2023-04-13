package dk.javahandson.dal;


import dk.javahandson.be.Event;
import dk.javahandson.be.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    DataBaseConnection dbc = DataBaseConnection.getInstance();

    public List<User> getAllUsers() throws SQLException {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM [User]";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String email = rs.getString("email");
                User user = new User(id, name, type, email);
                allUsers.add(user);
            }
        }
        return allUsers;
    }

    public void createLogin(String username, String password) throws SQLException {
        int id = getUserId(username);
        String sql = "INSERT INTO Login (user_id, username, password) VALUES (?,?,?)";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2,username);
            ps.setString(3,password);

            ps.execute();
        }
    }



    public void createUser(User user) throws SQLException {
        String sql = "INSERT INTO [User] (name, type, email) VALUES (?,?,?)";
        String name = user.getName();
        String type = "EventCoordinator";
        String email = user.getEmail();

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,type);
            ps.setString(3,email);

            ps.execute();

        }
    }

    public boolean deleteUser(User user) throws SQLException {
        int id = user.getId();
        String sql ="DELETE FROM [User] WHERE id=?";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            int result = ps.executeUpdate();
            if(result > 0)
                return true;
        }
        return false;
    }

    public boolean deleteFromLoginUser(User user) throws SQLException {
        int id = user.getId();
        String sql ="DELETE FROM Login WHERE user_id=?";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            int result = ps.executeUpdate();
            if(result > 0)
                return true;
        }
        return false;
    }

    public boolean deleteFromUserEvent(User user) throws SQLException {
        int id = user.getId();
        String sql ="DELETE FROM UserEvent WHERE user_id=?";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
            int result = ps.executeUpdate();
            if(result > 0)
                return true;
        }
        return false;
    }


    public void updateUser(User user) throws SQLException {
        int id = user.getId();
        String name = user.getName();
        String type = user.getType();
        String email = user.getEmail();


        String sql = "UPDATE [User] SET name = ?, type = ?, email = ? WHERE id = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,type);
            ps.setString(3,email);
            ps.setInt(4,id);
            ps.execute();

        }
    }

    private int getNextEventId() {
        try (Connection con = dbc.getConnection()) {
            ResultSet rs = con.createStatement().executeQuery("SELECT TOP 1 * FROM Event ORDER BY id DESC;");
            rs.next();
            int id = rs.getInt("id");
            int nextID = id;
            return nextID;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addUserToEvent(Event event, User user) throws SQLException {
        int eventId = getNextEventId();
        int userId = user.getId();
        String sql = "INSERT INTO UserEvent (event_id, user_id) VALUES (?,?)";
        try(Connection con = dbc.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, eventId);
            ps.setInt(2, userId);
            ps.execute();
        }
    }


    public int getUserId(String email) throws SQLException {
        String sql = "SELECT id FROM [User] WHERE email = ?";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                return id;
            }
        }
        return 0;
    }

    public String getPasswordFromUser(User user) throws SQLException {
        int id = user.getId();
        String password = "";
        String sql = "SELECT password FROM Login WHERE user_id = ?";
        try (Connection connection = dbc.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                password = rs.getString("password");
            }
        }
        return password;
    }

    public void updateLogin(User user, String password) throws SQLException {

        String sql = "UPDATE Login SET username = ?, password = ? WHERE user_id = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, password);
            ps.setInt(3, user.getId());
            ps.execute();
        }
    }

    public boolean validateLogin(String email, String password) throws SQLException {
        String sql = "SELECT * FROM Login WHERE username = '" + email + "';";
        try(Connection con = dbc.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                if(rs.getString("password").equals(password))
                    return true;
            }
        }
        return false;
    }

    public User loginUser(String email) throws SQLException{
        String sql = "SELECT * FROM [User] WHERE email = '" + email + "';";
        try(Connection con = dbc.getConnection()){
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String type = rs.getString("type");
                String userEmail = rs.getString("email");
                if(userEmail.equals(email))
                    return new User(id, name, type, email);
            }
        }
        return null;
    }
}
