<%@ page import="com.group18.capstone.controller.Food" %>
<%@ page import="com.group18.capstone.dao.FoodDao" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="com.group18.capstone.controller.CheckOut" %>
<%--Created by IntelliJ IDEA.--%>
<%--  User: Ibrahim Hermouche--%>
<%--  Date: 11/12/2021--%>
<%--  Time: 1:02 PM--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    FoodDao foodData = new FoodDao();
    List<Food> foods = foodData.selectAllItems();
    ArrayList<CheckOut> cart_list = (ArrayList<CheckOut>) session.getAttribute("cart-list");
    if (cart_list != null){
        request.setAttribute("cart_list",cart_list);
    }
    request.setAttribute("FOOD_LIST", foods);

%>
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
        <div>
            <ul class="navbar-nav">
                <li class="nav-link">
                    <a href="<%=request.getContextPath()%>/checkout-cart.jsp" class="badge badge-pill badge-dark">Peek into Oven <span class="badge badge-danger">${cart_list.size()}</span></a>
                </li>
            </ul>
        </div>
        <div class="TOP-HEAD-ADMIN">
            <form class="form-inline my-2 my-lg-0" action="<%= request.getContextPath()%>/LogoutServlet" method="post">
                <button class="btn btn-secondary my-2 my-sm-0" type="submit">Sign Out</button>
            </form>
        </div>
    </nav>
</header>
<%
    if (session.getAttribute("UserName")== null && session.getAttribute("Role") != "admin"){
        response.sendRedirect("index.jsp");
    }
%>
<h1>User Page</h1>
Welcome ${UserName}

<p class="text-center">We welcome you to taste our delicious dishes prepared by the Michelin star recipients!</p>
<p class="text-center">After you pick your item(s) you can peek into oven to see your total and adjust item amount and total!</p>
<div class="container">
    <div class="row">
        <div class="col-md-11">
            <h5 class="text-center">Select your order</h5>
            <hr>
            <br>
            <form action="AddToCartServlet" method="get">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Item</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        if (!foods.isEmpty()){
                            for (Food food : foods){

                    %>
<%--                    <c:forEach var="food" items = "${FOOD_LIST}">--%>
                        <tr>
                            <td><%=food.getItemName()%></td>
                            <td><%=food.getItemDesc()%></td>
                            <td>$<%=food.getItemPrice()%></td>
                            <td><a href="AddToCartServlet?id=<%=food.getFoodID()%>">Add to cart</a></td>
<%--                            <td>${food.itemName}</td>--%>
<%--                            <td>${food.itemDesc}</td>--%>
<%--                            <td>$${food.itemPrice}</td>--%>
<%--                            <td><a href="AddToCartServlet?id=<%=%>">Add to cart</a></td>--%>
                        </tr>
                        <%
                            }}
                        %>
<%--                    </c:forEach>--%>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>
<br>
<br>
<br>


<div class="container">
    <img src="<c:url value='/Images/logo.png'/>" id="Logo" width = "300" height="200" class="rounded mx-auto d-block" alt="Logo_Index">
</div>

<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>
