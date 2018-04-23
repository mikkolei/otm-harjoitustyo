
package opintoapp.domain;

import java.sql.SQLException;
import java.util.List;
import opintoapp.dao.CourseDao;

public class FakeCourseDao implements CourseDao {

    @Override
    public Course create(Course course) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> getAll(User user) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDone(int id, int grade) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
