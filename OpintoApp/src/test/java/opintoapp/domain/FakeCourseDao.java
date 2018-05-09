
package opintoapp.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import opintoapp.dao.CourseDao;

public class FakeCourseDao implements CourseDao {
    
    List<Course> courses;

    public FakeCourseDao() {
        courses = new ArrayList<>();
    }

    @Override
    public Course create(Course course) throws SQLException {
        course.setId(courses.size() + 1);
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
