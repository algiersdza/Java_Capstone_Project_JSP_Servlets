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
    <nav class="navbar navbar-expand-md navbar-dark bg-dark">
        <div>
            <a href="<%=request.getContextPath()%>/index.jsp" class="navbar-brand">
                <img src="<c:url value='/Images/r-header.png'/>"
                     width="30" height="30" class="d-inline-block align-top" alt=""> Restco
                Admin Management
            </a>
        </div>
        <div class="TOP-HEAD-ADMIN">
            <form class="form-inline my-2 my-lg-0" action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Sign Out</button>
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
