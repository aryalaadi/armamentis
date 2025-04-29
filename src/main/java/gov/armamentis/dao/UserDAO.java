package gov.armamentis.dao;
import java.sql.*;

public class UserDAO {
  public boolean register(UserModel u) throws SQLException {
    String sql = "INSERT INTO User(Name, Role, Password) VALUES(?,?,?)";
    try (Connection c = DBUtil.getConnection();
         PreparedStatement s = c.prepareStatement(sql)) {
      s.setString(1, u.getName());
      s.setString(2, u.getRole());
      s.setString(3, u.getPassword());
      return s.executeUpdate() == 1;
    }
  }

  public User login(String name, String pwd) throws SQLException {
    String sql = "SELECT * FROM User WHERE Name=? AND Password=?";
    try (Connection c = DBUtil.getConnection();
         PreparedStatement s = c.prepareStatement(sql)) {
      s.setString(1, name);
      s.setString(2, pwd);
      try (ResultSet r = s.executeQuery()) {
        if (r.next()) {
          User u = new User();
          u.setUserID(r.getInt("UserID"));
          u.setName(r.getString("Name"));
          u.setRole(r.getString("Role"));
          return u;
        }
      }
    }
    return null;
  }
}