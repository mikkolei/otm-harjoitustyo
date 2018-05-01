package opintoapp.dao;

/**
 * Class that implements UserDao and is responsible of the SQL commands of the User class
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.User;

public class SQLUserDao implements UserDao {

    private Database db;

    public SQLUserDao(Database db) {
        this.db = db;
    }

    @Override
    public User create(User user) throws Exception {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User "
                    + "(name, username, password)"
                    + " VALUES (?, ?, ?)");
//            stmt.setInt(1, user.getId());
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            return user;
        } catch (SQLException e) {

        }
        return null;

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
        stmt.setString(1, username);
        stmt.setString(2, password);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String username1 = rs.getString("username");
        String password1 = rs.getString("password");
        User user = new User(id, name, username1, password1);

        stmt.close();
        conn.close();

        return user;
    }
    
    @Override
    public User findByUsername(String username) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, username);
        
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"));
        rs.close();
        stmt.close();
        conn.close();
        return user;
    }
    
    @Override
    public User findById(int id) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return null;
        }
        User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"));
        rs.close();
        stmt.close();
        conn.close();
        return user;
    }

//    @Override
//    public List<User> getAll() throws SQLException {
//        Connection conn = db.getConnection();
//        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User;");
//        
//        return users;
//    }

}
