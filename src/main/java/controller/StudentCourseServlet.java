package controller;

import utils.DBUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class StudentCourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String course = request.getParameter("course");
        String userEmail = (String) request.getSession(false).getAttribute("email");
        try {
            int result = DBUtils.intqueries("UPDATE users SET course_name = ? WHERE email = ?", new String[]{course, userEmail});
            System.out.println("You have successfully enrolled to the new course");
            response.sendRedirect("profileCabinet.jsp");
        } catch (SQLException e) {
            System.out.println("You can not enroll the course: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
