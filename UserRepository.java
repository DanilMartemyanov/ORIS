package oop;

import java.awt.desktop.UserSessionEvent;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
     List<User> FindAll() throws SQLException;
     void RecordUser(String email , String password) throws SQLException;

     boolean authorization(String email , String password) throws SQLException;
     void generateUUID(String email , String password) throws SQLException;
}
