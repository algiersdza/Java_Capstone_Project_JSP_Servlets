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
<h1>User Page</h1>
Welcome ${UserName}

<div class="container">
    <img src="<c:url value='/Images/logo.png'/>" id="Logo" width = "300" height="200" class="rounded mx-auto d-block" alt="Logo_Index">
</div>


    <p class="text-center">We welcome you to taste our delicious dishes prepared by the Michelin star recipients!</p>
<!-- tawfik-->
<%--<div class="container">--%>
<%--    <img src="<c:url value='/Images/Untitled-3.png'/>" id="star-taw1" width = "500" height="500" class="rounded mx-auto d-block" alt="Logo_Index">--%>
<%--</div>--%>
<%--<br>--%>
<%--<br>--%>
<%--<!-- usama-->--%>
<%--<div class="row">--%>
<%--    <img src="<c:url value='/Images/Untitled-4.png'/>" id="star-usa1" width = "500" height="500" class="rounded mx-auto d-block" alt="Logo_Index">--%>
<%--<!-- mimi-->--%>
<%--    <img src="<c:url value='/Images/Untitled-2.png'/>" id="star-mimi1" width = "500" height="500" class="rounded mx-auto d-block" alt="Logo_Index">--%>
<%--</div>--%>
<%--<br>--%>
<%--<br>--%>
<%--<!-- tawfik-->--%>
<%--<div class="container">--%>
<%--    <img src="<c:url value='/Images/Untitled-1.png'/>" id="star-taw2" width = "500" height="500" class="rounded mx-auto d-block" alt="Logo_Index">--%>
<%--</div>--%>

<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
