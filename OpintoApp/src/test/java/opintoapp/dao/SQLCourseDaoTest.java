package opintoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import opintoapp.domain.Course;
import opintoapp.domain.User;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SQLCourseDaoTest {

    Database db;
    CourseDao cdao;
    UserDao udao;
    User user;

    @Before
    public void setUp() throws Exception {
        db = new Database("jdbc:sqlite:opintoAppFake.db");
        udao = new SQLUserDao(db);
        cdao = new SQLCourseDao(db, udao);
        user = new User(1, "testName", "tester", "password");
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course "
                + "(user_id, name, credits, done)"
                + "VALUES (?, ?, ?, ?)");
        stmt.setInt(1, user.getId());
        stmt.setString(2, "testCourse");
        stmt.setInt(3, 5);
        stmt.setBoolean(4, false);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
//    @Test
//    public void createCourseWorks() throws SQLException {
//        User user1 = new User(2, "name", "username", "pass");
//        Course course = new Course(user1, "testing", 5);
//        cdao.create(course);
//        List<Course> courses = cdao.getAll(user1);
////        for (Course c : courses) {
////            System.out.println(c.getUser());
////        }
//        assertEquals(1, courses.size());
//        assertEquals("testing", courses.get(0).getName());
//        assertFalse(courses.get(0).isDone());
//        assertEquals(1, courses.get(1).getUser().getId());
//    }
    
//    @Test
//    public void coursesCanBeSetDone() throws SQLException {
//        Course course = new Course(3, user, "testing", 5, false, 0);
//        cdao.setDone(3, 5);
//        assertEquals(true, course.isDone());
//    }

    @After
    public void tearDown() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DROP TABLE Course;");
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }

}
