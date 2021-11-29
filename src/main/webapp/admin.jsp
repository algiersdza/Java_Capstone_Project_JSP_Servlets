<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/11/2021
  Time: 12:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Admin Page</title>
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
            <a href="https://www.xadmin.net" class="navbar-brand"> Restco
                Admin Management</a>
        </div>

<%--        <ul class="navbar-nav">--%>
<%--            <li><a href="<%=request.getContextPath()%>/admin.jsp"--%>
<%--                   class="nav-link">Users</a></li>--%>
<%--        </ul>--%>

        <div class="TOP-HEAD-ADMIN">
            <form action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <input type="submit" value="Sign Out">
            </form>
        </div>
    </nav>
</header>
<br>


<%
    if (session.getAttribute("UserName")== null){
        response.sendRedirect("index.jsp");
    }
%>
<h1>Login Successful!</h1>
Welcome ${UserName}
<!-- <h2>Welcome</h2> -->
<p>Below you can display, add and edit users</p>

<div class="row">
    <div class="container">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <div class="container text-left">

            <a href="<%=request.getContextPath()%>/new" class="btn btn-success">Add
                New User</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>UserID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>User Name</th>
                <th>Password</th>
                <th>Email Address</th>
                <th>Role</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>

            <c:forEach var="user" items="${listUser}">

                <tr>
                    <td><c:out value="${user.UserID}" /></td>
                    <td><c:out value="${user.FirstName}" /></td>
                    <td><c:out value="${user.LastName}" /></td>
                    <td><c:out value="${user.UserName}" /></td>
                    <td><c:out value="${user.Password}" /></td>
                    <td><c:out value="${user.EmailAddress}" /></td>
                    <td><c:out value="${user.Role}" /></td>
                    <td><a href="edit?id=<c:out value='${user.UserID}' />">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="delete?id=<c:out value='${user.UserID}' />">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
