<%-- 
    Document   : createproduct
    Created on : 07-03-2022, 19:49:19
    Author     : Xqy
--%>

<%@page import="sample.product.ProductError"%>
<%@page import="sample.product.ProductError"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Product Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                response.sendRedirect("login.jsp");
            }

            ProductError productError = (ProductError) request.getAttribute("PRODUCT_ERROR");
            if (productError == null) {
                productError = new ProductError();
            }
        %>
        <form action="MainController" method="POST">
            Product ID: <input type="text" name="productID" required=""/>
            <%= productError.getProductIDError()%></br>
            Product Name: <input type="text" name="productName" required=""/>
            <%= productError.getProductNameError()%></br>
            Image: <input type="text" name="image"  required=""/></br>
            Price: <input type="number" name="price"  required="" min="1"/></br>
            Quantity: <input type="number" name="quantity" required="" min="1"/></br>
            Category: <input type="text" name="categoryID" required="" /></br>
            Import Date: <input type="date" name="importDate" required=""/></br>
            Using Date: <input type="date" name="usingDate" required=""/></br>
            Status: <input type="text" name="status" value="True" readonly=""/></br>
            <input type="submit" name="action" value="CreateProduct" />
            <input type="reset" value="Reset" />
        </form>
    </body>
</body>
</html>
