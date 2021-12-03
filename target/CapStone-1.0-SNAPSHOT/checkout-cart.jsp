<%@ page import="com.group18.capstone.dao.FoodDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.group18.capstone.controller.CheckOut" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DecimalFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: Ibrahim Hermouche
  Date: 12/2/2021
  Time: 10:36 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DecimalFormat dcf = new DecimalFormat("#.##");
    request.setAttribute("dcf",dcf);
    ArrayList<CheckOut> cart_list = (ArrayList<CheckOut>) session.getAttribute("cart-list");
    List<CheckOut> checkOutList = null;
    if (cart_list != null){
        FoodDao foodDao = new FoodDao();
        checkOutList = foodDao.getCartStuff(cart_list);
        float total = foodDao.getTotal(cart_list);
        request.setAttribute("cart_list",cart_list);
        request.setAttribute("total",total);
        //request.setAttribute("CHECKOUT_LIST", cart_list);
    }
%>

<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<title>Your Cart</title>
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
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
                <li>
                    <a href="<%=request.getContextPath()%>/userpage.jsp" class="nav-link">Back to user page</a>
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
    if (session.getAttribute("UserName")== null){
        response.sendRedirect("index.jsp");
    }
%>
<div class="container">
    <div class="row">
        <div class="col-sm-9">
            <h5 class="text-center">Your Cart</h5>
            <hr>
            <table class="table table-borderless">
                <thead>
                <tr>
                    <th>Item</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Cancel</th>
                </tr>
                </thead>
                <tbody>
                        <%
                            if (cart_list != null){
//                                System.out.println("cart_list contents: "+cart_list);
                                for (CheckOut c : checkOutList){
//                                    System.out.println("checkOutList contents: "+checkOutList);
//                                    System.out.println("cart_list contents: "+cart_list);
                        %>
<%--                    <c:forEach var="checkout" items = "${CHECKOUT_LIST}">--%>
                        <tr>
                            <td><%=c.getItemName()%></td>
                            <td><%= dcf.format(c.getItemPrice())%></td>
                            <td>
                                <div class="form-group d-flex justify-content-between">
                                    <a class="btn btn-sm btn-decre" href="QuantityServlet?action=dec&id=<%=c.getFoodID()%>"><i class="fas fa-minus-square"></i></a>
                                    <input type="number" class="form-control" name ="quantity" min="1" value="<%=c.getQuantity()%>" readonly>
                                    <a class="btn bnt-sm btn-incre" href="QuantityServlet?action=inc&id=<%=c.getFoodID()%>"><i class="fas fa-plus-square"></i></a>
                                </div>
                            </td>
                            <td><a href="RemoveFromCartServlet?id=<%=c.getFoodID()%>" class="btn btn-danger">Remove</a></td>
                        </tr>
<%--                    </c:forEach>--%>
                <%
                    }}else {}
                %>
                </tbody>
            </table>
        </div>
        <div class="col-sm-5">
            <h5 class="text-center">Your total</h5>
            <hr>
            <form method="post">
                <table class="table table-sm table-borderless table-dark">
                    <tbody>
                    <tr>
                        <h2>Your total is: $${(total>0)?dcf.format(total):0} </h2>
                    </tr>
                    </tbody>
                </table>
                <table>
                    <button type="submit" class="btn btn-secondary" disabled>Submit Order</button>
                </table>
            </form>
        </div>
    </div>
</div>

<div class="container">
    <img src="<c:url value='/Images/logo.png'/>" id="Logo" width = "300" height="200" class="rounded mx-auto d-block" alt="Logo_Index">
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
<footer><p><small>&copy;2021 copyright. All Right Reserved.</small></p></footer>
</body>
</html>