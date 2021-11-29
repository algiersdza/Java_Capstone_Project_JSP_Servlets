<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/12/2021
  Time: 1:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>User Page</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
            crossorigin="anonymous">
<body>
<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: rgb(1, 1, 24)">
        <div>
            <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand"> Restco
                Admin Management</a>
        </div>
        <div class="TOP-HEAD-USER">
            <form action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <input type="submit" value="Sign Out">
            </form>
        </div>
    </nav>
</header>


    <%
        if (session.getAttribute("UserName")== null){
            response.sendRedirect("index.jsp");
        }
    %>
    <h1>Login Successful!</h1>
    Welcome ${UserName}
    <!-- <h2>Welcome</h2> -->
    <p>You are a user role, not much to do here</p>
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
