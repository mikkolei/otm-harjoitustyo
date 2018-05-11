
package opintoapp.dao;

import java.sql.SQLException;
import java.util.List;
import opintoapp.domain.Course;
import opintoapp.domain.User;

/**
 * Interface class for DAO implementation of Course class
 */
public interface CourseDao {
    
    /**
     * Create new course
     * @param course course to be created
     * @return course that is created
     * @throws SQLException Exception if the operation fails
     */
    Course create(Course course) throws SQLException;
    
    /**
     * Gets all courses of the user
     * @param user user whose courses are listed
     * @return list of courses
     * @throws SQLException Exception if the operation fails
     */
    List<Course> getAll(User user) throws SQLException;
    
    /**
     * Sets course as done
     * @param id id of the course
     * @param grade grade for the course
     * @throws SQLException Exception if the operation fails
     */
    void setDone(int id, int grade) throws SQLException;
    
}
