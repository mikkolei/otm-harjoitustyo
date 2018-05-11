package opintoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import opintoapp.domain.User;

/**
 * Class that implements UserDao and is responsible of the SQL commands to User class
 */
public class SQLUserDao implements UserDao {

    private Database db;
    
    /**
     * 
     * @param db database to be used for saving information
     */
    public SQLUserDao(Database db) {
        this.db = db;
    }
    
    /**
     * Creates new user to the database
     * @param user User to be created
     * @return Created user
     * @throws Exception 
     */
    @Override
    public User create(User user) throws Exception {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO User "
                    + "(name, username, password)"
                    + " VALUES (?, ?, ?)");
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
    
    /**
     * Finds user from the database by username and password
     * @param username Username
     * @param password Password
     * @return User
     * @throws SQLException 
     */
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
    
    /**
     * Checks whether the username is already created or not
     * @param username Username
     * @return true if username is available, false if username is taken
     * @throws SQLException 
     */
    @Override
    public boolean checkUsernameAvailability(String username) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, username);
        
        ResultSet rs = stmt.executeQuery();
        if (!rs.next()) {
            return true;
        }
        User user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password"));
        rs.close();
        stmt.close();
        conn.close();
        return false;
    }
    
    /**
     * Finds user from the database by user id
     * @param id Id of the user
     * @return User
     * @throws SQLException 
     */
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

}
