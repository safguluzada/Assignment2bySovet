package controller;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.mindrot.jbcrypt.BCrypt;
import org.omg.Messaging.SyncScopeHelper;
import utils.DBUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {


    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("registrationPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userEmail = request.getParameter("email");
        String userPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (userEmail != null && userPassword != null && confirmPassword!=null) {
            if (!confirmPassword.equals(userPassword)) {
                request.setAttribute("confirmPasswordError", "Password fields do not match");
                logger.debug("confirmPasswordError","Password fields do not match");
                System.out.println("Logggggg");
                response.sendRedirect("registrationPage.jsp");
                return;
            }
            else{


                try {
                    ResultSet resultSet = DBUtils.query("SELECT * FROM users WHERE email = ", userEmail);
                    if (resultSet != null && resultSet.next()) {
                        String user_email = resultSet.getString("email");
                        if (user_email != null) {
                            logger.warn("This email is already used by another user");
                            request.setAttribute("emailDuplicate", "This email is already used by another user");
                            response.sendRedirect("registrationPage.jsp");
                            return;
                        }
                    }
                    String hash = BCrypt.hashpw(userPassword, BCrypt.gensalt());
                    int result = DBUtils.intqueries("INSERT INTO users (email, password) VALUES (?, ?)", new String[]{userEmail, hash});
                    response.sendRedirect("auth.jsp");
                } catch (SQLException e) {
                    logger.error("you have a problem: " + userEmail);
                    e.printStackTrace();
                }
            }
        }
        else {
            logger.warn("Email and password fields are required!");
            response.sendRedirect("registrationPage.jsp");
        }
    }
}