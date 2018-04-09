
package opintoapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class UserTest {
    
    @Test
    public void equalWhenSameUsername() {
        User u1 = new User("mikko", "tester", "test");
        User u2 = new User("mikko", "tester", "test");
        assertTrue(u1.equals(u2));
    }
    
    @Test
    public void settingUsername() {
        User u1 = new User("test", "tester", "test");
        u1.setUsername("username");
        assertEquals("username", u1.getUsername());
    }
}
