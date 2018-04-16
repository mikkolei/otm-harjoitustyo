
package opintoapp.dao;

import java.util.List;
import opintoapp.domain.Course;

public interface CourseDao {
    
    Course create(Course course) throws Exception;
    
    List<Course> getAll();
    
    void setDone(int id, int grade) throws Exception;
    
}
