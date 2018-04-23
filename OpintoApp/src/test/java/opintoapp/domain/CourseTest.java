
package opintoapp.domain;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class CourseTest {
    
    private Course c1;
    private Course c2;
    private User u;
    
    @Before
    public void setUp() {
        this.u = new User("tester", "tester", "test");
        this.c1 = new Course(1, "OTM", 5, true, 4, u);
        this.c2 = new Course(u, "Tira", 10);
    }
    
    @Test
    public void getCourseNameTest() {
        assertEquals("OTM", c1.getName());
        assertEquals("Tira", c2.getName());
    }
    
    @Test
    public void getCreditsTest() {
        assertEquals(5, c1.getCredits());
        assertEquals(10, c2.getCredits());
    }
    
    @Test
    public void getDoneStatusTest() {
        assertEquals(true, c1.isDone());
        assertEquals(false, c2.isDone());
    }
    
    @Test
    public void getGradeTest() {
        assertEquals(4, c1.getGrade());
        assertEquals(0, c2.getGrade());
    }
    
    @Test
    public void getUserTest() {
        assertEquals(u, c1.getUser());
        assertEquals(u, c2.getUser());
    }
    
//    @After
//    public void tearDown() {
//    }

    
}
