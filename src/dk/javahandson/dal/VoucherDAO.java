package dk.javahandson.dal;

import dk.javahandson.be.Voucher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VoucherDAO {
    DataBaseConnection dbc = DataBaseConnection.getInstance();

    public List<Voucher> getAllVouchers() throws SQLException {
        List<Voucher> allVouchers = new ArrayList<>();
        String sql = "SELECT * FROM Voucher";
        try (Connection con = dbc.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("uuid");
                int eventId = rs.getInt("event_id");
                String type = rs.getString("type");
                Voucher voucher = new Voucher(uuid, eventId, type);
                allVouchers.add(voucher);
            }
        }
        return allVouchers;
    }

    public boolean deleteVoucher(Voucher voucher) throws SQLException {
        try (Connection con = dbc.getConnection()) {
            String uuid = voucher.getUuid();
            String sql = "DELETE FROM Voucher WHERE (uuid=?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uuid);
            int result = ps.executeUpdate();
            if (result > 0)
                return true;
        }
        return false;
    }

    public void createVoucher (Voucher voucher) throws SQLException {
        String sql = "INSERT INTO Voucher (uuid, event_id, type, redeemable) VALUES (?,?,?,?)";
        String uuid = voucher.getUuid();
        int eventId = voucher.getEventId();
        String type = voucher.getType();
        try (Connection con = dbc.getConnection()) {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, uuid);
            ps.setInt(2, eventId);
            ps.setString(3, type);
            ps.setBoolean(4, true);
            }
        }
    }
