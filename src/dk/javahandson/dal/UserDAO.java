package dk.javahandson.dal;


import dk.javahandson.be.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAO {
    DataBaseConnection dbc = new DataBaseConnection();
    private void createUser(User user) {
        String sql = "INSERT INTO Event (name, type, email) VALUES (?,?,?)";
        String name = user.getName();
        String type = user.getType();
        String email = user.getEmail();

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,type);
            ps.setString(3,email);

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteUser(User user) {
        int id = user.getId();
        String sql ="DELETE FROM Songs WHERE id=?";

        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateUser(User user){
        int id = user.getId();
        String name = user.getName();
        String type = user.getType();
        String email = user.getEmail();


        String sql = "UPDATE Songs SET name = ?, type = ?, email = ? WHERE id = ?;";
        try(Connection con = dbc.getConnection();) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,type);
            ps.setString(3,email);
            ps.setInt(4,id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
