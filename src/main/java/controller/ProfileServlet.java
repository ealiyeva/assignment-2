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

public class ProfileServlet extends HttpServlet {
    private static final String USER_CABINET_URI = "cabinet.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            if (session.getAttribute("email") != null) {
                String firstname = request.getParameter("firstname");
                String lastname = request.getParameter("lastname");
                String age = request.getParameter("age");
                String phone = request.getParameter("phone");
                String gender = request.getParameter("gender");

                try {
                    int result = DB.alter("UPDATE users SET firstname = ?, lastname = ?, age = ?, phone = ?, gender = ?" +
                            " WHERE email = ? ",
                            new String[]{firstname, lastname, age, phone, gender, (String) session.getAttribute("email")});
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_CABINET_URI);
                    requestDispatcher.forward(request, response);
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }
}
