<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 11/12/2021
  Time: 3:47 PM
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>RestCo Add/Edit Form</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark"
         style="background-color: rgb(1, 1, 24)">
        <div>
            <a class="navbar-brand"> RestCo Admin Management </a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Users</a></li>
        </ul>
        <div class="TOP-HEAD-ADMIN">
            <form action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <input type="submit" value="Sign Out">
            </form>
        </div>
    </nav>
</header>
<br>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${userCurd != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${userCurd == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${userCurd != null}">
                                Edit User
                            </c:if>
                            <c:if test="${userCurd == null}">
                                Add New User
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${userCurd != null}">
                        <input type="hidden" name="UserID" value="<c:out value='${userCurd.UserID}' />" />
                    </c:if>

                    <fieldset class="form-group">
                        <label>First Name</label> <input type="text"
                                                         value="<c:out value='${userCurd.FirstName}' />" class="form-control"
                                                         name="FirstName" required="required">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Last Name</label> <input type="text"
                                                        value="<c:out value='${userCurd.LastName}' />" class="form-control"
                                                        name="LastName">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Username</label> <input type="text"
                                                       value="<c:out value='${userCurd.UserName}' />" class="form-control"
                                                       name="UserName">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Password</label> <input type="password"
                                                       value="<c:out value='${userCurd.Password}' />" class="form-control"
                                                       name="Password">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Email Address</label> <input type="email"
                                                            value="<c:out value='${userCurd.EmailAddress}' />" class="form-control"
                                                            name="EmailAddress">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Role</label> <input type="text"
                                                   value="<c:out value='${userCurd.Role}' />" class="form-control"
                                                   name="Role">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
