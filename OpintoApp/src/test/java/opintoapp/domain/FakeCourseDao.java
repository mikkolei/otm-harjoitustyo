
package opintoapp.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import opintoapp.dao.CourseDao;

public class FakeCourseDao implements CourseDao {
    
    List<Course> courses;
//    User user;

    public FakeCourseDao() {
//        user = new User("tester", "tester", "test");
        courses = new ArrayList<>();
    }

    @Override
    public Course create(Course course) throws SQLException {
        courses.add(course);
        return course;
    }

    @Override
    public List<Course> getAll(User user) throws SQLException {
        return courses;
    }

    @Override
    public void setDone(int id, int grade) throws SQLException {
        for (Course c : courses) {
            if (c.getId() == id) {
                c.setDone();
                c.setGrade(grade);
            }
        }
    }
    
}
