package oop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRepositoryJDBC implements UserRepository {
    PreparedStatement preparedStatementRU;
    private Connection connection;


    //language=sql
    private static final String RECORD = "insert into users (email , password) VALUES (?,?)";
    //language=sql
    private static final String SELECT = "select * from users";
    //language=sql
    private static final String LOGIN = "select * from users where email = (?)";
    //language=sql
    private static final String GEN_UUID = "insert into users_uuid(users_id,uuid) values (? , ?)";
    //language=sql
    private static final String GET_ID = "select id from users ";

    public UserRepositoryJDBC(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<User> FindAll() throws SQLException {
        List<User> resultFind = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SELECT);
            while (resultSet.next()) {
                User user = User.builder()
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password"))
                        .build();

                resultFind.add(user);
            }
            return resultFind;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void RecordUser(String email, String password) throws SQLException {
        preparedStatementRU = connection.prepareStatement(RECORD);
        preparedStatementRU.setString(1, email);
        preparedStatementRU.setString(2, password);
        preparedStatementRU.executeUpdate();
    }

    @Override
    public boolean authorization(String email, String password) throws SQLException {
        preparedStatementRU = connection.prepareStatement(LOGIN);
        preparedStatementRU.setString(1, email);
        ResultSet set = preparedStatementRU.executeQuery();
        if (set.next()) {
            return set.getString("password").equals(password);
        }
        return false;
    }

    @Override
    public boolean generateUUID(String email, String password) throws SQLException {
        preparedStatementRU = connection.prepareStatement(LOGIN);
        preparedStatementRU.setString(1, email);
        ResultSet set = preparedStatementRU.executeQuery();
        if (set.next()) {
            if (set.getString("password").equals(password)) {
                int id = set.getInt("id");
                if (id != 0) {
                    UUID uuid = UUID.randomUUID();
                    PreparedStatement preparedStatementRU2 = connection.prepareStatement(GEN_UUID);
                    preparedStatementRU2.setInt(1,id);
                    preparedStatementRU2.setObject(2, uuid);
                    preparedStatementRU2.executeUpdate();
                    return true;
                }
            }


        }
        return false;
    }
}
