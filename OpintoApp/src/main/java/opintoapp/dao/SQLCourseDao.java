
package opintoapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.*;

/**
 * Class that implements CourseDao and is responsible of the SQL commands to Course class
 */
public class SQLCourseDao implements CourseDao {
    
    private Database db;
    private UserDao userDao;
    
    /**
     * Constructor
     * @param db database to be used for saving information
     * @param userDao userDao to be used for finding correct user
     */
    public SQLCourseDao(Database db, UserDao userDao) {
        this.db = db;
        this.userDao = userDao;
    }
    
    /**
     * Creates new course to the database
     * @param course Course to be created
     * @return Created course
     * @throws SQLException SQLEXception if creation fails
     */
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
            return course;
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Gets all courses of the user from the database
     * @param user User whose courses are listed
     * @return List of courses
     * @throws SQLException SQLException
     */
    @Override
    public List<Course> getAll(User user) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM Course WHERE user_id = ?");
        stmt.setInt(1, user.getId());
        
        ResultSet rs = stmt.executeQuery();
        List<Course> returnCourses = new ArrayList<>();
        
        while (rs.next()) {
            returnCourses.add(new Course(rs.getInt("id"), userDao.findById(rs.getInt("user_id")), rs.getString("name"), rs.getInt("credits"), rs.getBoolean("done"), rs.getInt("grade")));
        }
        rs.close();
        stmt.close();
        conn.close();
        return returnCourses;
    }
    
    /**
     * Marks course as done and sets grade
     * @param id Id of the course
     * @param grade Grade of the course
     * @throws SQLException SQLException if the operation fails
     */
    @Override
    public void setDone(int id, int grade) throws SQLException {
        Connection conn = db.getConnection();
        PreparedStatement stmt = conn.prepareStatement("UPDATE Course SET done = 1, grade = ? WHERE id = ?");
        stmt.setInt(1, grade);
        stmt.setInt(2, id);
        stmt.executeUpdate();
        stmt.close();
        conn.close();
    }
    
}
