<%-- 
    Document   : viewCart.jsp
    Created on : 08-03-2022, 12:55:37
    Author     : Xqy
--%>

<%@page import="sample.user.UserDTO"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.shopping.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("US")) {
                response.sendRedirect("login.jsp");
            }
            Cart cart = (Cart) session.getAttribute("CART");
            if (cart != null) {
                if (cart.getCart().size() > 0) {
        %>
        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        </br>

        <table border="1" cellpadding="5" cellspacing="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Import Date</th>
                    <th>Using Date</th>                   
                    <th>Total</th>
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    double total = 0;
                    for (ProductDTO product : cart.getCart().values()) {
                        total += product.getPrice() * product.getQuantity();

                %>
            <form action="MainController">
                <tr>
                    <td><%=count++%></td>
                    <td>
                        <%= product.getProductID()%>
                        <input type="hidden" name="productID" value="<%= product.getProductID()%>" />
                    </td>
                    <td><%= product.getProductName()%></td>
                    <td><img width="60px" height="50px" src="Images/<%= product.getImage()%>" alt="Image can't show"></td>
                    <td><%= product.getPrice()%></td>
                    <td>          
                        <input type="number" name="quantity" value="<%= product.getQuantity()%>" required="" min="1"/>
                    </td>
                    <td> <%= product.getImportDate()%> </td>
                    <td> <%= product.getUsingDate()%> </td>                       
                    <td><%= product.getPrice() * product.getQuantity()%>$</td>
                    <!--edit-->
                    <td> 
                        <input type="submit" name="action" value="Edit" />
                    </td>
                    <!--remove-->
                    <td>
                        <input type="submit" name="action" value="Remove" />
                    </td>
                </tr>  
            </form>

            <%
                }
            %>

        </tbody>

    </table>
    <h1>Total: <%=total%>$</h1>
    </br>
    <form action="MainController">
        <input type="hidden" name="total" value="<%=total%>" />
        <input type="hidden" name="size" value="<%=cart.getCart().size()%>"/>
        <input type="hidden" name="userID" value="<%=loginUser.getUserID()%>" />
        <input style="width: 100px ; height: 30px; background-color: red; color: white" type="submit" name="action" value="CheckOut"/>
    </form>

    <%
            }
        }
    %>
    
    <h2><a href="MainController?action=Search&search=">Add more</a></h2>


</body>
</html>
