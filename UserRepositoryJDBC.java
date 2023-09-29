package HomeWork2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepositoryJDBC implements  UserRepository{

//   language=sql
    private static final String LOGIN = "select email from users";
    Connection connection;

    public UserRepositoryJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String findByLogin(String login) throws SQLException {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet =  statement.executeQuery(LOGIN);
            while (resultSet.next()){
                User user  = User.builder()
                        .login(resultSet.getString("email"))
                        .build();
                if(user.getLogin().equals(login)){
                    return "Успешно";
                }
            }
            return "Провал";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
