package controller;

import org.mindrot.jbcrypt.BCrypt;
import org.omg.PortableServer.ServantLocatorPackage.CookieHolder;
import utils.DB;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AuthenticationServlet extends HttpServlet {
    private static final String USER_CABINET_URI = "cabinet.jsp";
    private static final String USER_REGISTER_URI = "register.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session.getAttribute("is_logged_in") != null && session.getAttribute("is_logged_in").equals(true)) {
            response.sendRedirect(USER_CABINET_URI);
        } else {
            response.sendRedirect(USER_REGISTER_URI);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        HttpSession session = request.getSession(false);
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String clientPassword;
        if (email != null && password != null) {
            try {
                ResultSet resultSet = DB.select("SELECT * FROM users WHERE email = ?", email);
                if (resultSet != null && resultSet.next()) {
                    clientPassword = resultSet.getString("password");
                    if (!checkPasswordHash(password, clientPassword)) {
                        System.out.println("Password is not correct");
                        response.sendRedirect(USER_REGISTER_URI);
                    } else {
                        session.setAttribute("is_logged_in", true);
                        session.setAttribute("email", email);
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher(USER_CABINET_URI);
                        requestDispatcher.forward(request, response);
                    }
                }
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    private boolean checkPasswordHash(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }
}
