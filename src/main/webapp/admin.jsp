
<%@ page import="com.group18.capstone.controller.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.group18.capstone.dao.UserDao" %><%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/11/2021
  Time: 12:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    UserDao userData = new UserDao();
    List<User> users = userData.selectAllUsers();
    request.setAttribute("USER_LIST", users);
%>
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

<div class="container">
    <div class="row">
        <div class="col-md-3">
            <h3>Create New User</h3>
            <form action="AddUserServlet" method="post">
                <div class="form-group">
                    <label>First Name</label>
                    <input class="form-control" name="FirstName" place-holder="First Name" required>
                </div>
                <div class="form-group">
                    <label>Last Name</label>
                    <input class="form-control" name="LastName" place-holder="Last Name" required>
                </div>
                <div class="form-group">
                    <label>Username</label>
                    <input class="form-control" name="UserName" place-holder="Username" required>
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="Password" place-holder="Password" required>
                </div>
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" class="form-control" name="EmailAddress" place-holder="example@host.domain" required>
                </div>
                <div class="form-group" >
                    <label>Role</label>
                    <select id="inputState" class="form-control" name="Role" required>
                        <option selected disabled>Choose Role</option>
                        <option value="admin">Admin</option>
                        <option value="user">Regular User</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Create</button>
                <button type="reset" class="btn btn-primary">Reset</button>
            </form>
        </div>
        <div class="col-md-9">
        <h3 class="text-center">List of Users</h3>
        <hr>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
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

            <c:forEach var="user" items="${USER_LIST}">
                <tr>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.userName}</td>
                    <td>${user.password}</td>
                    <td>${user.emailAddress}</td>
                    <td>${user.role}</td>
                    <td><a href="editform.jsp?id=${user.userID}">Edit</a>
                        &nbsp;&nbsp;&nbsp;&nbsp; <a
                                href="DeleteUserServlet?id=${user.userID}">Delete</a></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    </div>

</div>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
