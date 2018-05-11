
package opintoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import opintoapp.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SQLUserDaoTest {
    
    Database db;
    UserDao userDao;
    
    @Before
    public void setUp() throws Exception {
        db = new Database("jdbc:sqlite:opintoAppFake.db");
        userDao = new SQLUserDao(db);
        
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (name, username, password)"
                + "VALUES (?, ?, ?);");
        stmt.setString(1, "testerName");
        stmt.setString(2, "tester");
        stmt.setString(3, "password");
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
    @Test
    public void findsUserByUsernameAndPassword() throws SQLException {
        User user = userDao.findByUsernameAndPassword("tester", "password");
        
        assertEquals("testerName", user.getName());
        assertEquals("tester", user.getUsername());
        assertEquals("password", user.getPassword());
    }
    
    @Test
    public void checkUsernameAvailability() throws SQLException {
        boolean check = userDao.checkUsernameAvailability("tester");
        
        assertFalse(check);
    }
    
    @Test
    public void findsUserById() throws Exception {
        User user = new User(1, "testerName", "tester", "password");
        
        assertEquals(user, userDao.findById(1));
    }
    
    @Test
    public void createUserWorks() throws Exception {
        User user = new User("testerName", "tester", "password");
        
        assertEquals(user, userDao.create(user));
    }
    
    @Test
    public void nonExistingUsernameCanBeAdded() throws SQLException {
        boolean check = userDao.checkUsernameAvailability("mikko");
        assertTrue(check);
    }
    
    @Test
    public void nonExistingUserIsFoundWithFindById() throws SQLException {
        User user = userDao.findById(2);
        assertEquals(null, user);
    }
    
    @Test
    public void nonExistingUserIsFoundWithFindByUsernameAndPassword() throws SQLException {
        User user = userDao.findByUsernameAndPassword("empty", "empty");
        assertEquals(null, user);
    }
    
    @After
    public void tearDown() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DROP TABLE User;");
        stmt.executeUpdate();
        stmt.close();
        conn.close();
        
    }

    
}
