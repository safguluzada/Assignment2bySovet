package controller;

import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class ProfileCabinetServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("email") != null) {
                String firstname = request.getParameter("firstname");
                String surname = request.getParameter("surname");
                String age = request.getParameter("age");
                String country = request.getParameter("country");
                String gender = request.getParameter("gender");

                try {
                    int result = DBUtils.intqueries("UPDATE users SET firstname = ?, surname = ?, age = ?, country = ?,  gender = ?" +
                            " WHERE email = ? ",
                            new String[]{firstname, surname, age, country, gender, (String) session.getAttribute("email")});
                    response.sendRedirect("profileCabinet.jsp");
                } catch (SQLException e) {
                    System.out.println("User must be careful while entering information!");
                    e.printStackTrace();
                }
            }
        }
    }
