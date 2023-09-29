package HomeWork2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MainRepository {
    private static  final  String DB_URL = "jdbc:postgresql://localhost/users";
    private static  final  String DB_USER = "postgres";
    private static  final  String DB_PASSWORD = "qwerty";
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
        UserRepository userRepository = new UserRepositoryJDBC(connection);
        System.out.println(userRepository.findByLogin("maratik"));


    }
}
