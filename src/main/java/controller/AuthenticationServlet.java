package controller;

import org.mindrot.jbcrypt.BCrypt;
import utils.DBUtils;

import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Sovet refresh the page!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("confirmPassword");
        //System.out.println(userPassword + " Success");
        if (userEmail != null && userPassword != null && userPassword.length() > 5) {
            try {
                ResultSet veriTabani = DBUtils.query("SELECT * FROM users WHERE email = '" + userEmail + "';");
//                                                          "SELECT * FROM users WHERE email = 'ssafguluzada2021@ada.edu.az';"
                if (veriTabani != null && veriTabani.next()) {
                    if (!BCrypt.checkpw(userPassword, veriTabani.getString("password"))) {
                        System.out.println("Invalid password");
                        response.sendRedirect("registrationPage.jsp");
                    } else {
                        System.out.println("Logined with email: " + userEmail);
                        HttpSession session = request.getSession(false);
                        session.setAttribute("email", userEmail);
                        response.sendRedirect("profileCabinet.jsp");
                    }
                } else {
                    System.out.println("Your information is not true!");
                    response.sendRedirect("registrationPage.jsp");
                }
            } catch (SQLException e) {
                System.out.println("you have an exception: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("Enter the valid user information");
            response.sendRedirect("auth.jsp");
        }
    }
}
