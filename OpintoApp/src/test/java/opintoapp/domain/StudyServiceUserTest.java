
package opintoapp.domain;

import java.sql.SQLException;
import opintoapp.dao.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class StudyServiceUserTest {
    
//    private Database db;
    private StudyService studyService;
    private FakeUserDao fakeUserDao;
    private FakeCourseDao fakeCourseDao;
    
//    public StudyServiceUserTest() throws Exception {
//        this.db = new Database("jdbc:sqlite:opintoAppTest.db");
//        
//    }
    
//    @BeforeClass
//    public static void setUpClass() {
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//    }
    
    @Before
    public void setUp() {
        this.fakeUserDao = new FakeUserDao();
        this.fakeCourseDao = new FakeCourseDao();
        this.studyService = new StudyService(fakeUserDao, fakeCourseDao);
        
        
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void createUserTest() throws SQLException {
        User user = new User(2, "mikko", "testertester", "test");
        boolean result = this.studyService.createUser(user);
        assertTrue(result);
    }
    
    @Test
    public void nonExistingUserCannotLogin() throws SQLException {
        boolean result = studyService.login("noOne", "pswd");
        assertFalse(result);
        
        assertEquals(null, studyService.getLoggedIn());
    }
    
    @Test
    public void existingUserCanLogin() throws SQLException {
        boolean result = studyService.login("tester", "test");
        assertTrue(result);
        assertEquals("tester", studyService.getLoggedIn().getUsername());
    }
    
    @Test
    public void loggedInCanLogout() throws SQLException {
        boolean result = studyService.login("tester", "test");
        assertTrue(result);
        studyService.logout();
        assertEquals(null, studyService.getLoggedIn());
    }
    @Test
    public void userCreationFailsIfUsernameNotUnique() throws SQLException {
        User u = new User(2, "antti", "tester", "test");
        boolean result = studyService.createUser(u);
        assertFalse(result);
    }

    
}
