package com.example.webapp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class RegisterServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String state = request.getParameter("state");
        String district = request.getParameter("district");
        String pincode = request.getParameter("pincode");
        String identity = request.getParameter("identity");

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setGender(gender);
        user.setState(state);
        user.setDistrict(district);
        user.setPincode(pincode);
        user.setIdentity(identity);

        UserDAO dao = new UserDAO();
        try {
            if (dao.registerUser(user)) {
                response.sendRedirect("login.jsp");
            } else {
                response.sendRedirect("register.jsp?error=Registration failed");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("register.jsp?error=Database error");
        }
    }
}
