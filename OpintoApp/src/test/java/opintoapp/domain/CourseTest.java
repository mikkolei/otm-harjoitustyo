
package opintoapp.domain;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class CourseTest {
    
    private Course c1;
    private Course c2;
    private User u;
    
    @Before
    public void setUp() {
        this.u = new User("tester", "tester", "test");
        this.c1 = new Course(1, u, "OTM", 5, true, 4);
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
    
    @Test 
    public void getIdTest() {
        assertEquals(1, c1.getId());
        assertEquals(0, c2.getId());
    }
    
    @Test
    public void toStringTest() {
        assertEquals("OTM, 5", c1.toString());
        assertEquals("Tira, 10", c2.toString());
    }
    
}
