package com.group18.capstone.servlet;

import com.group18.capstone.controller.CheckOut;
import com.group18.capstone.controller.CheckOutBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

@WebServlet(name = "QuantityServlet", value = "/QuantityServlet")
public class QuantityServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String action = request.getParameter("action");
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<CheckOut> cart_list = (ArrayList<CheckOut>) request.getSession().getAttribute("cart-list");

            if (action != null && id >= 1) {
                if (action.equals("inc")) {
                    for (CheckOut c : cart_list) {
                        if (c.getFoodID() == id) {
                            int quantity = c.getQuantity();
                            quantity++;
                            c.setQuantity(quantity);
                            response.sendRedirect("checkout-cart.jsp");
                        }
                    }
                }

                if (action.equals("dec")) {
                    for (CheckOut c : cart_list) {
                        if (c.getFoodID() == id && c.getQuantity() > 1) {
                            int quantity = c.getQuantity();
                            quantity--;
                            c.setQuantity(quantity);
                            break;
                        }
                    }
                    response.sendRedirect("checkout-cart.jsp");
                }
            } else {
                response.sendRedirect("checkout-cart.jsp");
            }
        }
    }
}
