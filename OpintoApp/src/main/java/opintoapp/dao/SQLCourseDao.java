
package opintoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.Course;
import opintoapp.domain.StudyService;
import opintoapp.domain.User;

public class SQLCourseDao implements CourseDao {
    
    private List<Course> courses;
    private Database db;
    private StudyService studyService;
    private UserDao userDao;
    private CourseDao courseDao;
    
    public SQLCourseDao(Database db) {
        this.db = db;
        courses = new ArrayList<>();
//        this.studyService = ss;
//        studyService = new StudyService(userDao, courseDao);
    }

    @Override
    public Course create(Course course) throws SQLException {
        try {
            Connection conn = db.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO Course "
                        + "(user_id, name, credits, done)"
                        + "VALUES (?, ?, ?, ?)");
            stmt.setInt(1, course.getUser().getId());
            stmt.setString(2, course.getName());
            stmt.setInt(3, course.getCredits());
            stmt.setBoolean(4, course.isDone());

            stmt.executeUpdate();
            stmt.close();
            conn.close();
            courses.add(course);
            return course;
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Course> getAll(User user) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course WHERE user_id = ?");
        stmt.setInt(1, user.getId());
        
        ResultSet rs = stmt.executeQuery();
        List<Course> returnCourses = new ArrayList<>();
        
        while (rs.next()) {
            Integer userId = rs.getInt("user_id");
            
            returnCourses.add(new Course(userDao.findById(userId), rs.getString("name"), rs.getInt("credits")));
        }
        rs.close();
        stmt.close();
        conn.close();
        return returnCourses;
    }

    @Override
    public void setDone(int id, int grade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
