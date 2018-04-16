package opintoapp.domain;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import opintoapp.dao.*;

public class StudyService {

    private CourseDao courseDao;
    private UserDao userDao;
    private User loggedIn;

    public StudyService(UserDao userDao, CourseDao courseDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
    }

    public boolean createCourse(int id, String content, int credit) {
        Course course = new Course(id, content, credit, loggedIn);
        return false;
    }

    public List<Course> getUndoneCourses() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        return courseDao.getAll().stream()
                .filter(c -> c.getUser().equals(loggedIn))
                .filter(c -> !c.isDone())
                .collect(Collectors.toList());
    }

    public List<Course> getDoneCourses() {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        return courseDao.getAll().stream()
                .filter(c -> c.getUser().equals(loggedIn))
                .filter(c -> c.isDone())
                .collect(Collectors.toList());
    }

    public void markDone(int id, int grade) {
        try {
            courseDao.setDone(id, grade);
        } catch (Exception e) {

        }
    }

    public boolean login(String username, String password) throws SQLException {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }
    
    public User getLoggedIn() {
        return loggedIn;
    }
    
    public void logout() {
        this.loggedIn = null;
    }

    public boolean createUser(User user) throws SQLException {
        if (userDao.findByUsername(user.getUsername()) != null) {
            return false;
        }
        user = new User(user.getName(), user.getUsername(), user.getPassword());
        try {
            userDao.create(user);
            return true;
        } catch (Exception ex) {
            return false;
        }

    }

}
