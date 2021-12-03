package com.group18.capstone.servlet;

import com.group18.capstone.controller.CheckOut;
import com.group18.capstone.controller.CheckOutBuilder;
import com.group18.capstone.controller.Food;
import com.group18.capstone.dao.FoodDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "AddToCartServlet", value = "/AddToCartServlet")
public class AddToCartServlet extends HttpServlet {
FoodDao foodDao;

    public AddToCartServlet(){}

    @Override
    public void init(){
        foodDao = new FoodDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       response.setContentType("text/html;charset=UTF-8");

       try (PrintWriter out = response.getWriter()){
           ArrayList<CheckOut> cartList = new ArrayList<>();
           int fid = Integer.parseInt(request.getParameter("id"));
           HttpSession session = request.getSession();

           ArrayList<CheckOut> cart_list = (ArrayList<CheckOut>) session.getAttribute("cart-list");
           if (cart_list == null){
               cartList.add(foodDao.getSingleFood(fid));
               session.setAttribute("cart-list",cartList);
               response.sendRedirect("userpage.jsp");
           }else {
               cartList = cart_list;
               boolean exist = false;
               //check if add to cart was clicked on the same item
               for (CheckOut c : cart_list) {
                   if (c.getFoodID() == fid ) {
                       exist = true;
                       out.println("<script type=\"text/javascript\">");
                       out.println("alert('Item Already in Cart! Click on \'peek into oven\'');");
                       out.println("location='userpage.jsp';");
                       out.println("</script>");
                       response.sendRedirect("userpage.jsp");
                   }
               }
               if (!exist){
               cartList.add(foodDao.getSingleFood(fid));
               response.sendRedirect("userpage.jsp");
               }
           }

       } catch (IOException | NumberFormatException e) {
           e.printStackTrace();
       }

    }
}
