
package opintoapp.dao;

import java.util.ArrayList;
import java.util.List;
import opintoapp.domain.Course;

public class SQLCourseDao implements CourseDao {
    
    private List<Course> courses;
    private Database db;
    
    public SQLCourseDao(Database db) {
        this.db = db;
        courses = new ArrayList<>();
    }

    @Override
    public Course create(Course course) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Course> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setDone(String name, int grade) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
