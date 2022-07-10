<%-- 
    Document   : shopping
    Created on : 12-03-2022, 12:30:51
    Author     : Xqy
--%>

<%@page import="java.util.List"%>
<%@page import="sample.product.ProductDTO"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">       
        <title>Shopping Page</title>
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
        <form action="MainController" >
            Search<input type="text" name="search"  value="<%=search%>" placeholder="input your text"/>
            <input type="submit" name="action"  value="Search"/>
        </form>

        </br>
        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (listProduct != null) {
                if (listProduct.size() > 0) {
        %>
        <table  border="1" cellpadding="5" cellspacing="1">
            <thead>
                <tr>
                    <th>No</th>
                    <th>Product ID</th>
                    <th>Name</th>
                    <th>Image</th>
                    <th>Price</th>
                    <th>Quantity</th>
                    <th>Category</th>
                    <th>Import Date</th>
                    <th>Using Date</th>                    
                    <th>ADD To Cart</th>                
                </tr>
            </thead>
            <tbody>
                <%
                    int count = 1;
                    for (ProductDTO product : listProduct) {
                %>

            <form action="MainController">
                <tr>
                    <td><%= count++%></td>                 
                    <td> <input type="hidden" name="productID" value="<%= product.getProductID()%>"/>
                        <%= product.getProductID()%> 
                    </td>
                    <td>
                        <input type="hidden" name="productName" value="<%= product.getProductName()%>"/>
                        <%= product.getProductName()%>
                    </td>
                    <td>
                        <input type="hidden" name="image" value="<%= product.getImage()%>"/>
                        <img width="60px" height="50px" src="Images/<%= product.getImage()%>" alt="Image can't show">
                    </td>
                    <td> 
                        <input type="hidden" name="price" value="<%= product.getPrice()%>"/>
                        <%= product.getPrice()%>$
                    </td>
                    <td>
                        <input type="hidden" name="quantity" value="<%= product.getQuantity()%>"/>
                        <%= product.getQuantity()%>
                    </td>
                    <td>  
                        <input type="hidden" name="categoryID" value="<%= product.getCategoryID()%>"/>
                        <%= product.getCategoryID()%> 
                    </td>                   
                    <td>  
                        <input type="hidden" name="importDate" value="<%= product.getImportDate()%>"/>
                        <%= product.getImportDate()%> 
                    </td>
                    <td>  
                        <input type="hidden" name="usingDate" value="<%= product.getUsingDate()%>"/>
                        <%= product.getUsingDate()%> 
                    </td>
                <input type="hidden"  name="status" value="<%= product.isStatus()%>"/>                      
                <td> 
                    <input type="submit" name="action" value="Add" /> 
                    <input type="hidden" name="search" value="<%=search%>" />     
                </td>
                </tr>
            </form>  

            <%
                }
            %>
        </tbody>
    </table>
    </br>
    <div>
        <form action="MainController" >
            <input style="width: 100px ; height: 30px; background-color: red; color: white" type="submit" name="action"  value="View"/>
        </form>
    </div>

    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%=error%>
    <%
        String message = (String) request.getAttribute("MESSAGE");
        if (message == null) {
            message = "";
        }
    %>
    <h3><%=message%></h3>

    <%
            }
        }
    %>
</body>
</html>
