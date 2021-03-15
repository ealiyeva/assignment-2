package controller;

import utils.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class CourseServlet extends HttpServlet {
    private static final String USER_CABINET_URI = "cabinet.jsp";


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String courseName = request.getParameter("course_name");
        HttpSession session = request.getSession(false);
        String email = (String) session.getAttribute("email");
        try {
            int result = DB.alter("UPDATE users SET course_name = ? WHERE email = ?", new String[]{courseName, email});
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_CABINET_URI);
            requestDispatcher.forward(request, response);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
