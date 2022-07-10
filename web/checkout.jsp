<%-- 
    Document   : checkout
    Created on : 11-03-2022, 14:16:12
    Author     : Xqy
--%>

<%@page import="sample.order.OrderDetailDTO"%>
<%@page import="sample.order.OrderDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Check Out Page</title>
    </head>
    <body>


        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
            }
            OrderDTO order = (OrderDTO) session.getAttribute("INSERT_ORDER");
            OrderDetailDTO detail = (OrderDetailDTO) session.getAttribute("INSERT_DETAIL");
        %>
        </br>
        </br>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        </br>    
        <h1> YOUR ORDER</h1>
        </br>
        <div style="font-weight: ">
            User ID:<%=order.getUserID()%></br>
            Order Date:<%=order.getOrderDate()%></br>
            Total money: <%=order.getTotal()%>$</br>
        </div>
        </br>
        </br>
        </br>

        <h2><a href="MainController?action=Search&search=">Happy shopping</a></h2></br>

        <%
            String error_quan = (String) request.getAttribute("ERROR_QUAN");
            if (error_quan == null) {
                error_quan = "";
            }
        %>
        <%=error_quan%>
        <%
            String error_cart_pro = (String) request.getAttribute("ERROR_CART_PRO");
            if (error_cart_pro == null) {
                error_cart_pro = "";
            }
        %>
        <%=error_cart_pro%>
        <%
            String error_cart = (String) request.getAttribute("ERROR_CART");
            if (error_cart == null) {
                error_cart = "";
            }
        %>
        <%=error_cart%>
        <%
            String success = (String) request.getAttribute("SUCCESS");
            if (success == null) {
                success = "";
            }
        %>
        <div style="color: red">
            <h3> <%=success%></h3>
        </div>

    </body>
</html>
