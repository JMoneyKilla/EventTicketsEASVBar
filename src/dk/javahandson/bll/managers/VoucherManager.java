package dk.javahandson.bll.managers;

import dk.javahandson.be.Voucher;
import dk.javahandson.dal.DataAccessFacade;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.util.List;

public class VoucherManager {
    private DataAccessFacade voucherDAO = new DataAccessFacade();
    public List<Voucher> getAllVouchers() throws SQLException {
        return voucherDAO.getAllVouchers();
    }
    public boolean deleteVoucher(Voucher voucher) throws SQLException {
        return voucherDAO.deleteVoucher(voucher);
    }
    public void createVoucher (Voucher voucher) throws SQLException {
        voucherDAO.createVoucher(voucher);
    }
    public ObservableList getVoucherTypes(int id) {
        return voucherDAO.getVoucherTypes(id);
    }
    }
