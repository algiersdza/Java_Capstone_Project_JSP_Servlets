package com.group18.capstone.model;

import javax.servlet.annotation.WebServlet;;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


//@WebServlet(name = "signUpServlet", value = "/sign-up")
public class SignUp extends HttpServlet {

    private String welcomeMessage = "Successful Login! Welcome!";
    private String adminDesc = "Below you can display, add, modify and edit users";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + welcomeMessage + "</h1>");
        out.println("<p>" + adminDesc + "</p>");
        out.println("</body></html>");
    }


}
