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
        user = new User(1, "testName", "testertester", "password");

        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO User (name, username, password)"
                + "VALUES (?, ?, ?);");
        stmt.setString(1, "testName");
        stmt.setString(2, "testertester");
        stmt.setString(3, "password");
        stmt.executeUpdate();
        stmt.close();
        PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO Course "
                + "(user_id, name, credits, done)"
                + "VALUES (?, ?, ?, ?)");
        stmt2.setInt(1, user.getId());
        stmt2.setString(2, "testCourse");
        stmt2.setInt(3, 5);
        stmt2.setBoolean(4, false);
        stmt2.executeUpdate();
        stmt2.close();
        conn.close();
    }
    
    @Test
    public void getAllCoursesWorks() throws SQLException {
        List<Course> courses = cdao.getAll(user);
        assertEquals(1, courses.size());
    }
    
    @Test
    public void createCourseWorks() throws SQLException {
        cdao.create(new Course(user, "testing", 5));
        List<Course> courses = cdao.getAll(user);
        assertEquals(2, courses.size());
        assertEquals("testing", courses.get(1).getName());
        assertFalse(courses.get(1).isDone());
        assertEquals("testertester", courses.get(1).getUser().getUsername());
    }

    @Test
    public void coursesCanBeSetDone() throws SQLException {
        List<Course> courses = cdao.getAll(user);
        Course c = cdao.create(new Course(2, user, "course", 5, false, 0));
        System.out.println(c.getId());
        cdao.setDone(c.getId(), 5);
        System.out.println(c.getGrade());
//        assertEquals(true, c.isDone());
        assertEquals(5, c.getGrade());
    }
    
    @After
    public void tearDown() throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("DROP TABLE Course;");
        stmt.executeUpdate();
        stmt.close();
        PreparedStatement stmt2 = conn.prepareStatement("DROP TABLE User;");
        stmt2.executeUpdate();
        stmt2.close();
        conn.close();
    }

}
