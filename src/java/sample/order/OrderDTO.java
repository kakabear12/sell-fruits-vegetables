/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.order;

import java.sql.Date;

/**
 *
 * @author Xqy
 */
public class OrderDTO {
    private String orderID;
    private Date orderDate;
    private double total;
    private String userID;
    private boolean status;

    public OrderDTO() {
        this.orderID = "";
        this.orderDate = null;
        this.total = 0;
        this.userID = "";
        this.status = false;
    }

    public OrderDTO(String orderID, Date orderDate, double total, String userID, boolean status) {
        this.orderID = orderID;
        this.orderDate = orderDate;
        this.total = total;
        this.userID = userID;
        this.status = status;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDare(Date orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
