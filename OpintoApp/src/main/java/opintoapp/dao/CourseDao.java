
package opintoapp.dao;

import java.sql.SQLException;
import java.util.List;
import opintoapp.domain.Course;
import opintoapp.domain.User;

public interface CourseDao {
    
    Course create(Course course) throws SQLException;
    
    List<Course> getAll(User user) throws SQLException;
    
    void setDone(int id, int grade) throws SQLException;
    
}
