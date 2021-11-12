package com.group18.capstone.servlet;

import com.group18.capstone.controller.User;
import com.group18.capstone.controller.UserCurd;
import com.group18.capstone.dao.CurdDao;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.awt.image.CropImageFilter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "AdminServlet", value = "/")
public class AdminServlet extends HttpServlet {
    private CurdDao curdDao;

    public void init(){
        curdDao = new CurdDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action){
                case "/edit":
                    editForm(request,response);
                    break;
                case "/update":
                    updateUser(request,response);
                    break;
                case "/new":
                    insertForm(request,response);
                    break;
                case "/delete":
                    removeUser(request,response);
                    break;
                default:
                    showUsersInDB(request,response);
                    break;
            }
        } catch (SQLException e2){
            throw new ServletException(e2);
        }
    }

    private void showUsersInDB(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<UserCurd> listUser = new CurdDao().selectAllUsers();
        request.setAttribute("ListUser",listUser);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("admin.jsp");
        requestDispatcher.forward(request,response);

    }
    private void removeUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int UserID = Integer.parseInt(request.getParameter("UserID"));
        curdDao.deleteUser(UserID);
        response.sendRedirect("admin.jsp");
    }
    private void insertForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String FirstName = request.getParameter("FirstName");
        String LastName = request.getParameter("LastName");;
        String UserName = request.getParameter("UserName");;
        String Password = request.getParameter("Password");;
        String EmailAddress = request.getParameter("EmailAddress");
        String Role = request.getParameter("Role");
        UserCurd userCurd = new UserCurd(FirstName,LastName,UserName,Password,EmailAddress,Role);
        curdDao.insertUser(userCurd);
        response.sendRedirect("admin.jsp");
    }
    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int UserID = Integer.parseInt(request.getParameter("UserID"));
        String Role = request.getParameter("Role");
        UserCurd userCurd = new UserCurd(UserID,Role);
        curdDao.updateRole(userCurd);
        response.sendRedirect("admin.jsp");
    }
    private void editForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int UserID = Integer.parseInt(request.getParameter("UserID"));
        UserCurd existingUser = curdDao.selectUser(UserID);
        RequestDispatcher dispatcher = request.getRequestDispatcher("editform.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);
    }





}
