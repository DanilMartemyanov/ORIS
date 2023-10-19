package servlets;

import repositories.User;
import repositories.UserRepository;
import repositories.UserRepositoryJDBC;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/users")
public class UsersServlet extends HttpServlet {

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

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String email = req.getParameter("email");
//        String password = req.getParameter("password");
//        try {
//            userRepository.RecordUser(email,password);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            System.out.println("Отработка");
            System.out.println(userRepository);
           List<User> result  = userRepository.FindAll();
            System.out.println(result);
            req.setAttribute("userForJSP", result);
            req.getRequestDispatcher("/jsp/users.jsp").forward(req,resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
