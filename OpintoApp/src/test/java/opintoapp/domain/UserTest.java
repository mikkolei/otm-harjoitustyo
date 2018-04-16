
package opintoapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    private User u;
    
    @Before
    public void setUp() {
        this.u = new User(1, "name", "username", "password");
    }
    
    @Test
    public void equalWhenSameUsername() {
        User u1 = new User(1, "mikko", "tester", "test");
        User u2 = new User(2, "antti", "tester", "test");
        assertTrue(u1.equals(u2));
    }
    
    @Test
    public void notEqualWhenDifferentUsername() {
        User u1 = new User(1, "mikko", "tester", "test");
        User u2 = new User(2, "mikko", "testertester", "test");
        assertFalse(u1.equals(u2));
    }
    
    @Test
    public void settingUsername() {
        u.setUsername("username");
        assertEquals("username", u.getUsername());
    }
    
    @Test
    public void getUsername() {
        assertEquals("username", u.getUsername());
    }
    
    @Test
    public void getName() {
        assertEquals("name", u.getName());
    }
    
    @Test
    public void getPassword() {
        assertEquals("password", u.getPassword());
    }
}
