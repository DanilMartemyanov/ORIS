package servlets;

import repositories.UserRepository;
import repositories.UserRepositoryJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@WebServlet("/authorization")
public class ServletAuthorization extends HttpServlet {
    UserRepository userRepository;
    private static final String DB_URL = "jdbc:postgresql://localhost/users";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty";

    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            userRepository = new UserRepositoryJDBC(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/jsp/entrance.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (userRepository.authorization(email , password)){
                HttpSession httpSession = request.getSession(true);
                httpSession.setAttribute("authenticated" , true);
                response.sendRedirect("/users");
             }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
