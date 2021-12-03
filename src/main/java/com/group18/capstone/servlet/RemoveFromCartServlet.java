package com.group18.capstone.servlet;

import com.group18.capstone.controller.CheckOut;
import com.group18.capstone.controller.CheckOutBuilder;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "RemoveFromCartServlet", value = "/RemoveFromCartServlet")
public class RemoveFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            String fid = request.getParameter("id");
            if (fid != null) {
                ArrayList<CheckOut> cart_list = (ArrayList<CheckOut>) request.getSession().getAttribute("cart-list");
                if (cart_list != null) {
                    for (CheckOut c : cart_list) {
                        if (c.getFoodID() == Integer.parseInt(fid)) {
                            cart_list.remove(cart_list.indexOf(c));
                            break;
                        }
                    }
                }
                response.sendRedirect("checkout-cart.jsp");
            } else {
                response.sendRedirect("checkout-cart.jsp");
            }

        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}