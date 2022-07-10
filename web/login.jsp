<%-- 
    Document   : login
    Created on : 03-03-2022, 11:24:12
    Author     : Xqy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/stylelogin.css">
        <title>Login Page</title>
    </head>
    <body>
        <div class="container">
            <h2>Login</h1>

                <form action="MainController" method="POST" id="form">
                    <p>User ID</p>
                    <input type="text" name="userID" required="" placeholder="Input your id"/>
                    <p>Password</p>
                    <input type="password" name="password" required="" placeholder="Input your password"/> </br>
                    </br>
                    <div class="g-recaptcha" data-sitekey="6LenTcYeAAAAABf7F0dMH10RH0tGZqBaPWjOZICv"></div>
                    <div id="error"></div></br>
                    <input type="hidden" name="action" value="Login" />                            
                    <input type="submit" name="Submit"/> 
                    </br> 
                    </br>
                    <input type="reset" name="Reset" /> 
                </form>

                <%
                    String error = (String) request.getAttribute("ERROR");
                    if (error == null) {
                        error = "";
                    }
                %>
                <%= error%>

                <p class="or-login">Or login</p>
                <button class="login-google">               
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8080/SellingManagement/LoginGoogleController&response_type=code
                       &client_id=232049583848-lgpfr9t4gadm8m6mgmu4jpu160ld6khv.apps.googleusercontent.com&approval_prompt=force">Login with google</a>
                </button>


                <script src="https://www.google.com/recaptcha/api.js" async defer></script>
                <script>
                    window.onload = function () {
                        const form = document.getElementById("form");
                        const error = document.getElementById("error");

                        form.addEventListener("submit", function (event) {
                            event.preventDefault();
                            const response = grecaptcha.getResponse();
                            if (response) {
                                form.submit();
                            } else {
                                error.innerHTML = "Please check";
                            }
                        });
                    }
                </script>
        </div>

    </body>
</html>
