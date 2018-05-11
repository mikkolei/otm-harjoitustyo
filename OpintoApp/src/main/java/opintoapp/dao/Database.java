
package opintoapp.dao;

import java.sql.*;

/**
 * Class to establish the database for the system
 */

public class Database {
    
    private String databaseAddress;
    
    /**
     * Sets the database with correct database address
     * @param databaseAddress database address to be used
     * @throws ClassNotFoundException 
     */
    public Database(String databaseAddress) throws ClassNotFoundException {
        this.databaseAddress = databaseAddress;
        try {
            Connection conn = DriverManager.getConnection(databaseAddress);
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS User"
                    + "(id integer PRIMARY KEY,"
                    + "name varchar(50),"
                    + "username varchar(50),"
                    + "password varchar(50)"
                    + ");");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS Course"
                    + "(id integer PRIMARY KEY,"
                    + "user_id integer,"
                    + "name varchar(100),"
                    + "credits integer,"
                    + "done boolean,"
                    + "grade integer,"
                    + "FOREIGN KEY (user_id) REFERENCES User(id)"
                    + ");");
        } catch (Exception e) {
            
        }
    }
    
    /**
     * Creates DriverManager connection with correct database address
     * @return Connection
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseAddress);
    }
     
}
