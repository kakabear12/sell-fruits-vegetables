/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.product;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sample.utils.DBUtils;

/**
 *
 * @author Xqy
 */
public class ProductDAO {

    private static final String SEARCH = "SELECT productID,productName,image,price,quantity,categoryID,importDate, usingDate, status FROM tblProduct WHERE productName like ? ";
    private static final String DELETE = "DELETE tblProduct WHERE productID=? ";
    private static final String UPDATE = "UPDATE tblProduct SET productName=?, image=?, price=?, quantity=?, importDate=?, usingDate=?, status=?  WHERE productID=?";
    private static final String CREATE = "INSERT INTO tblProduct(productID, productName, image, price, quantity, categoryID, importDate, usingDate, status) VALUES(?,?,?,?,?,?,?,?,?)";
    private static final String SHOWALL = "SELECT productID,productName,image,price,quantity,categoryID,importDate, usingDate, status FROM tblProduct WHERE productName like ? AND importDate <= ? AND usingDate >= ? AND quantity > 0 AND status = 1";
    private static final String CHECK_QUANTITY = "SELECT productName FROM tblProduct WHERE productID = ? AND quantity >= ?";
    private static final String GET_QUANTITY = "SELECT productID,productName,image,price,quantity,categoryID,importDate, usingDate, status FROM tblProduct WHERE productID = ? ";

    public List<ProductDTO> getListProduct(String search) throws SQLException, ClassNotFoundException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SEARCH);
                ptm.setString(1, "%" + search + "%");
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("categoryID");
                    Date importDate = rs.getDate("importDate");
                    Date usingDate = rs.getDate("usingDate");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, image, price, quantity, category, importDate, usingDate, status));
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
        return list;
    }

    public boolean delete(String productID) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(DELETE);
                ptm.setString(1, productID);
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean update(ProductDTO product) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(UPDATE);
                ptm.setString(1, product.getProductName());
                ptm.setString(2, product.getImage());
                ptm.setInt(3, product.getPrice());
                ptm.setInt(4, product.getQuantity());
                ptm.setDate(5, product.getImportDate());
                ptm.setDate(6, product.getUsingDate());
                ptm.setBoolean(7, product.isStatus());
                ptm.setString(8, product.getProductID());
                check = ptm.executeUpdate() > 0 ? true : false;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public boolean create(ProductDTO product) throws SQLException, ClassNotFoundException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CREATE);
                ptm.setString(1, product.getProductID());
                ptm.setString(2, product.getProductName());
                ptm.setString(3, product.getImage());
                ptm.setInt(4, product.getPrice());
                ptm.setInt(5, product.getQuantity());
                ptm.setString(6, product.getCategoryID());
                ptm.setDate(7, product.getImportDate());
                ptm.setDate(8, product.getUsingDate());
                ptm.setBoolean(9, product.isStatus());
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

    public List<ProductDTO> showListProduct(String search, Date curDate) throws SQLException, ClassNotFoundException {
        List<ProductDTO> list = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(SHOWALL);
                ptm.setString(1, "%" + search + "%");
                ptm.setDate(2, curDate);
                ptm.setDate(3, curDate);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productID = rs.getString("productID");
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("categoryID");
                    Date importDate = rs.getDate("importDate");
                    Date usingDate = rs.getDate("usingDate");
                    boolean status = rs.getBoolean("status");
                    list.add(new ProductDTO(productID, productName, image, price, quantity, category, importDate, usingDate, status));
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
        return list;
    }

    public boolean checkQuantity(String productID, int quantity) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(CHECK_QUANTITY);
                ptm.setString(1, productID);
                ptm.setInt(2, quantity);
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

    public int updateQuantity(ProductDTO product, String productID) throws SQLException {
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_QUANTITY);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                while (rs.next()) {
                    String productName = rs.getString("productName");
                    String image = rs.getString("image");
                    int price = rs.getInt("price");
                    int quantity = rs.getInt("quantity");
                    String category = rs.getString("categoryID");
                    Date importDate = rs.getDate("importDate");
                    Date usingDate = rs.getDate("usingDate");
                    boolean status = rs.getBoolean("status");
                    product = new ProductDTO(productID, productName, image, price, quantity, category, importDate, usingDate, status);
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
            return product.getQuantity();
        }

    }
}
