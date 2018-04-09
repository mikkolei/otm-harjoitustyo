
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

}
