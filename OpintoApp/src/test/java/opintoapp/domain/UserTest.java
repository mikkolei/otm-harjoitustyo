
package opintoapp.domain;

import java.util.ArrayList;
import org.junit.Before;
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
    public void notEqualWhenDifferentObject() {
        User u1 = new User(1, "mikko", "tester", "test");
        Object u2 = new Object();
        assertFalse(u1.equals(u2));
    }
    
    @Test
    public void getUsernameTest() {
        assertEquals("username", u.getUsername());
    }
    
    @Test
    public void getNameTest() {
        assertEquals("name", u.getName());
    }
    
    @Test
    public void getPasswordTest() {
        assertEquals("password", u.getPassword());
    }
    
    @Test
    public void getCoursesTest() {
        assertEquals(new ArrayList<>(), u.getCourses(u));
    }
    
    @Test
    public void toStringTest() {
        assertEquals("username", u.toString());
    }
}
