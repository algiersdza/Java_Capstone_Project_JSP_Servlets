package com.group18.capstone.servlet;

import com.group18.capstone.controller.UserLogin;
import com.group18.capstone.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private UserDao userDao;

    public void init(){
        userDao = new UserDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String UserName = request.getParameter("UserName");
        String Password = request.getParameter("Password");
        UserLogin userLogin = new UserLogin();
        userLogin.setUserName(UserName);
        userLogin.setPassword(Password);
        String currentUser = userLogin.getUserName();
        try {
            if (userDao.isLoginCorrect(userLogin)){
                HttpSession session = request.getSession();
                session.setAttribute("UserName",currentUser);
                response.sendRedirect("admin.jsp");
            }else {
                response.sendRedirect("noaccount.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
