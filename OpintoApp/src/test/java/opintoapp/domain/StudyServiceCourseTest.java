package opintoapp.domain;

import java.sql.SQLException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class StudyServiceCourseTest {

    private StudyService studyService;
    private FakeUserDao fakeUserDao;
    private FakeCourseDao fakeCourseDao;
    private User user1;
    private User user2;

    @Before
    public void setUp() throws Exception {
        this.fakeUserDao = new FakeUserDao();
        this.fakeCourseDao = new FakeCourseDao();
        this.studyService = new StudyService(fakeUserDao, fakeCourseDao);
        user1 = new User("tester", "tester", "test");
        user2 = new User("tester2", "tester2", "password");
        fakeUserDao.create(user1);
        fakeUserDao.create(user2);
        fakeCourseDao.create(new Course(1, user1, "OTM", 5, true, 4));
        fakeCourseDao.create(new Course(user1, "testCourse", 5));
        studyService.login("tester", "test");
    }

    @Test
    public void inTheBeginningUndoneListContainsInitializedCourses() throws SQLException {
        List<Course> undoneCourses = studyService.getUndoneCourses();
        assertEquals(1, undoneCourses.size());
        
        Course course = undoneCourses.get(0);
        assertEquals("testCourse", course.getName());
        assertEquals(5, course.getCredits());
        assertEquals("tester", course.getUser().getUsername());

    }

    @Test
    public void inTheBeginningDoneListContainsInitializedCourses() throws SQLException {
        List<Course> doneCourses = studyService.getDoneCourses();
        assertEquals(1, doneCourses.size());
        
        Course course = doneCourses.get(0);
        assertEquals("OTM", course.getName());
        assertEquals(5, course.getCredits());
        assertEquals("tester", course.getUser().getUsername());
    }

    @Test
    public void undoneListEmptyIfNotLoggedIn() throws SQLException {
        studyService.logout();
        List<Course> courses = studyService.getUndoneCourses();
        assertEquals(0, courses.size());
    }
    
    @Test
    public void doneListEmptyIfNotLoggedIn() throws SQLException {
        studyService.logout();
        List<Course> courses = studyService.getDoneCourses();
        assertEquals(0, courses.size());
    }
    
    @Test
    public void loggedUsersUndoneListContainsAddedCourse() throws SQLException {
        createCourse(new Course(user1, "Tira", 5));
        List<Course> courses = studyService.getUndoneCourses();
        assertEquals(2, courses.size());
        
        Course course = courses.get(1);
        assertEquals("Tira", course.getName());
        assertEquals("tester", course.getUser().getUsername());
    }
    
    @Test
    public void addingUndoneCourseDontCountOnTheDoneList() throws SQLException {
        createCourse(new Course(user1, "Tira", 5));
        List<Course> courses = studyService.getDoneCourses();
        assertEquals(1, courses.size());
    }
    
    @Test
    public void loggedUsersCourseListsDoesNotCountCoursesOfOther() throws SQLException {
        createCourse(new Course(user1, "Tira", 5));
        studyService.logout();
        studyService.login("tester2", "password");
        List<Course> undoneCourses = studyService.getUndoneCourses();
        List<Course> doneCourses = studyService.getDoneCourses();
        
        assertEquals(0, undoneCourses.size());
        assertEquals(0, doneCourses.size());
        
    }
    
    @Test
    public void coursesCanBeSetDone() throws SQLException {
        createCourse(new Course(user1, "OTM", 5));
        Course courseToBeSetDone = new Course(4, user1, "course", 5, false, 0);
        createCourse(courseToBeSetDone);
        assertEquals(3, studyService.getUndoneCourses().size());
        studyService.markDone(courseToBeSetDone.getId(), 5);
//        assertEquals(2, studyService.getUndoneCourses().size());
//        assertEquals(2, studyService.getDoneCourses().size());
    }
    
    private void createCourse(Course course) {
        studyService.createCourse(course);
    }

}
