package controller;

import org.mindrot.jbcrypt.BCrypt;
import utils.DB;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegistrationServlet extends HttpServlet {
    private static final String USER_CABINET_URI = "cabinet.jsp";
    private static final String USER_REGISTER_URI = "register.jsp";
    private static final String USER_LOGIN_URI = "login.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("is_logged_in") != null && session.getAttribute("is_logged_in").equals(true)) {
            response.sendRedirect(USER_CABINET_URI);
        }

        response.sendRedirect(USER_REGISTER_URI);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String confirmation = request.getParameter("confirm_password");

        if (!password.equals(confirmation)) {
            System.out.println("Password confirmation must be equal to password!");
            response.sendRedirect(USER_REGISTER_URI);
        }
        try {
            ResultSet resultSet = DB.select("SELECT id FROM users WHERE email = ?", email);
            if (resultSet != null) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt("id");

                    if (userId != 0) {
                        System.out.println("User already exists");
                        response.sendRedirect(USER_LOGIN_URI);
                        return;
                    }
                }
            }
            if (email != null && !password.equals("")) {
                String hash = generatePasswordHash(password);
                int result = DB.alter("INSERT INTO users (email, password) VALUES (?, ?)", new String[]{email, hash});
                response.sendRedirect(USER_LOGIN_URI);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    
    private String generatePasswordHash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
