package com.group18.capstone.servlet;

import com.group18.capstone.controller.User;

import com.group18.capstone.controller.UserBuilder;
import com.group18.capstone.dao.UserDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
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
//        UserLogin userLogin = new UserLogin();
        User userLogin = new UserBuilder().setUserName(UserName).setPassword(Password).createUser();
//        userLogin.setUserName(UserName);
//        userLogin.setPassword(Password);
        String currentUser = userLogin.getUserName();
        String currentUserRole = userLogin.getRole();
        try {
            if (userDao.isLoginCorrect(userLogin)){
                if (userDao.isAdmin(userLogin)){
                    //return normal page
                    HttpSession session = request.getSession();
                    session.setAttribute("UserName",currentUser);
                    session.setAttribute("Role",currentUserRole);
                    response.sendRedirect("userpage.jsp");
                }else {
                    //return admin page
                    HttpSession session = request.getSession();
                    session.setAttribute("UserName",currentUser);
                    session.setAttribute("Role",currentUserRole);
                    response.sendRedirect("admin.jsp");
                }

            }else {
                response.sendRedirect("noaccount.jsp");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
