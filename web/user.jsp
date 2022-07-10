<%-- 
    Document   : user
    Created on : 03-03-2022, 11:32:33
    Author     : Xqy
--%>

<%@page import="sample.user.UserGoogleDTO"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User Page</title>
    </head>
    <body>

        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        <h1 >Welcome: <%= loginUser.getUserID()%>      </h1>              
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        </br>

        <h2><a href="MainController?action=Search&search=">Happy shopping</a></h2></br>

    </body>
</html>
