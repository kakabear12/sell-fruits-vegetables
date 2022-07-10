<%-- 
    Document   : admin
    Created on : 03-03-2022, 11:38:56
    Author     : Xqy
--%>

<%@page import="sample.product.ProductDTO"%>
<%@page import="java.util.List"%>
<%@page import="sample.user.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <%
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser == null || !loginUser.getRoleID().equals("AD")) {
                response.sendRedirect("login.jsp");
            }
            String search = request.getParameter("search");
            if (search == null) {
                search = "";
            }
        %>
        Welcome: <h1> <%= loginUser.getFullName()%>  </h1>

        <form action="MainController">
            <input type="submit" name="action" value="Logout"/>
        </form>
        <h2><a href="createProduct.jsp">Add new product</a></br></h2>

        <form action="MainController" >
            Search <input type="text" name="search"  value="<%= search%>" placeholder="input your text"/>
            <input type="submit" name="action"  value="SearchAtAD"/>
        </form>

        <%
            List<ProductDTO> listProduct = (List<ProductDTO>) request.getAttribute("LIST_PRODUCT");
            if (listProduct != null) {
                if (listProduct.size() > 0) {
        %>
        <table border="1" cellpadding="5" cellspacing="1">
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
                    <th>Status </th>
                    <th>Delete</th>
                    <th>Update</th>
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
                    <td>
                        <input type="text" name="productID" value="<%= product.getProductID()%>" readonly="" />
                    </td>
                    <td>
                        <input type="text" name="productName" value="<%= product.getProductName()%>" required="" />                   
                    </td>
                    <td>
                        <img width="60px" height="50px" src="Images/<%= product.getImage()%>" alt="Image can't show">
                        <input type="text" name="image" value="<%= product.getImage()%>" required="" />
                    </td>
                    <td> <input type="number" name="price" value="<%= product.getPrice()%>" required="" /> </td>                   
                    <td> <input type="number" name="quantity" value="<%= product.getQuantity()%>" required="" />  </td>
                    <td> <input type="text" name="categoryID" value="<%= product.getCategoryID()%>" required="" />  </td>
                    <td> <input type="date" name="importDate" value="<%= product.getImportDate()%>" required="" />   </td>
                    <td> <input type="date" name="usingDate" value="<%= product.getUsingDate()%>" required="" />  </td>
                    <td> 
                        <%= product.isStatus()%><br>
                        <input checked="checked" type="radio" name="status" value="True">True<br>
                        <input type="radio" name="status" value="False">False 
                    </td>
                    <!-- delete -->
                    <td>
                        <a href="MainController?action=Delete&productID=<%= product.getProductID()%>&search=<%= search%>" >Delete</a>
                    </td>
                    <!--upadte-->
                    <td>
                        <input type="submit" name="action" value="Update" />
                        <input type="hidden" name="search" value="<%= search%>" />                      
                    </td>
                </tr>
            </form>

            <%
                }
            %>


        </tbody>
    </table>
    <%
        String error = (String) request.getAttribute("ERROR");
        if (error == null) {
            error = "";
        }
    %>
    <%=error%>

    <%
            }
        }
    %>
</body>
</html>
