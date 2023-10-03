package oop;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registration")
public class ServletRegistration extends HttpServlet {
    UserRepository userRepository ;
    private  static final String DB_URL = "jdbc:postgresql://localhost/users";
    private  static final String DB_USER = "postgres";
    private  static final String DB_PASSWORD = "qwerty";
    @Override
    public void init() throws ServletException {
        try{
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Not found data base"); ;
        }
        Connection connection;
        try {
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            userRepository = new UserRepositoryJDBC(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/HTML/Registration.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        try {
            userRepository.RecordUser(email,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("/HTML/hello.html").forward(req,resp);
    }
}

