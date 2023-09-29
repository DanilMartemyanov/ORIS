package HomeWork2;

import java.sql.SQLException;

public interface UserRepository {
    String findByLogin(String login) throws SQLException;
}
