/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sample.order.OrderDAO;
import sample.order.OrderDTO;
import sample.order.OrderDetailDAO;
import sample.order.OrderDetailDTO;
import sample.product.ProductDAO;
import sample.product.ProductDTO;
import sample.shopping.Cart;
import sample.user.UserDTO;

/**
 *
 * @author Xqy
 */
@WebServlet(name = "CheckOutController", urlPatterns = {"/CheckOutController"})
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "checkout.jsp";
    private static final String SUCCESS = "checkout.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            //Data of order
            int max = 999999, min = 1;
            int od = (int) (Math.random() * (max - min + 1) + min);
            String orderID = Integer.toString(od);
            OrderDAO dao = new OrderDAO();
            boolean checkDuplicate = dao.checkDuplicate_OR(orderID);
            while (checkDuplicate) {
                od = (int) (Math.random() * (max - min + 1) + min);
                orderID = Integer.toString(od);
            }
            long millis = System.currentTimeMillis();
            Date orderDate = new java.sql.Date(millis);
            double total = Double.parseDouble(request.getParameter("total"));
            String userID = request.getParameter("userID");
            boolean status = true;
            OrderDTO order = new OrderDTO(orderID, orderDate, total, userID, status);

            int numberProduct = Integer.parseInt(request.getParameter("size"));
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            if (session != null) {
                if (cart != null) {
                    if (numberProduct == cart.getCart().size()) {
                        boolean checkOrder = dao.insertOrder(order);
                        for (ProductDTO product : cart.getCart().values()) {
                            //Data of detail
                            int dt = (int) (Math.random() * (max - min + 1) + min);
                            String detailID = Integer.toString(dt);
                            OrderDetailDAO daoD = new OrderDetailDAO();
                            boolean checkDuplicateDT = daoD.checkDuplicate_DT(detailID);
                            while (checkDuplicateDT) {
                                dt = (int) (Math.random() * (max - min + 1) + min);
                                detailID = Integer.toString(dt);
                            }

                            int price = product.getPrice();
                            int quantity = product.getQuantity();
                            String productID = product.getProductID();
                            boolean statusDT = true;
                            OrderDetailDTO detail = new OrderDetailDTO(detailID, price, quantity, orderID, productID, statusDT);
                            if (cart.getCart().containsKey(productID)) {
                                boolean checkDetail = daoD.insertOrderDT(detail);
                                ProductDAO daoP = new ProductDAO();
                                boolean checkquantity = daoP.checkQuantity(productID, quantity);
                                if (checkquantity) {
                                    if (checkOrder && checkDetail) {
                                        session.setAttribute("INSERT_ORDER", order);
                                        session.setAttribute("INSERT_DETAIL", detail);
                                        int oldQuantity = daoP.updateQuantity(product, productID);
                                        product = new ProductDTO(productID, product.getProductName(), product.getImage(), price, oldQuantity - quantity, product.getCategoryID(), product.getImportDate(), product.getUsingDate(), product.isStatus());
                                        boolean checkUpdate = daoP.update(product);
                                        if (checkUpdate) {
                                            session.removeAttribute("CART");
                                        }
                                    }
                                } else {
                                    request.setAttribute("ERROR_QUAN", "Out of stock or Quantity is more than allowed!!!");
                                }
                                request.setAttribute("SUCCESS", "Your order has been successfully placed!");
                                url = SUCCESS;
                            }
                        }

                    } else {
                        request.setAttribute("ERROR_CART_Pro", "The cart hasn't product!!!");
                    }
                } else {
                    request.setAttribute("ERROR_CART", "The cart doesn't exist!!!");
                }
            }
        } catch (Exception e) {
            log("Error at CheckOutController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
