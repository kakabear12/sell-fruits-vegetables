/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import sample.utils.DBUtils;

/**
 *
 * @author Xqy
 */
public class OrderDetailDAO {

    private static final String INSERT_OR_DETAIL = "INSERT INTO tblOrderDetail(detailID, price, quantity, orderID, productID, status) VALUES(?,?,?,?,?,?)";
    private static final String CHECK_DUPLICATE_ORDT = "SELECT productID FROM tblOrderDetail WHERE detailID =?";

    public boolean insertOrderDT(OrderDetailDTO orderDT) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_OR_DETAIL);
                ptm.setString(1, orderDT.getDetailID());
                ptm.setInt(2, orderDT.getPrice());
                ptm.setInt(3, orderDT.getQuantity());
                ptm.setString(4, orderDT.getOrderID());
                ptm.setString(5, orderDT.getProductID());
                ptm.setBoolean(6, orderDT.isStatus());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
    public boolean checkDuplicate_DT(String detailID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_DUPLICATE_ORDT);
                ptm.setString(1, detailID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    check = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }
        return check;
    }
}
