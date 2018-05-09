package opintoapp.domain;

/**
 * Class that is responsible for the applications's logic
 */

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
    
    /**
     * Creating a new course to logged in user
     * @param course course to be added for the user
     * @return true if adding a course is successful, otherwise false
     */

    public boolean createCourse(Course course) {
        Course c = new Course(getLoggedIn(), course.getName(), course.getCredits());
        try {
            courseDao.create(c);
            loggedIn.addCourse(c);
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
        
    }
    
    /**
     * Unfinished courses of the logged in user
     * @return returns a list of the unfinished courses 
     * @throws SQLException if getting the courses fails, throws SQL Exception
     */

    public List<Course> getUndoneCourses() throws SQLException {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        return courseDao.getAll(loggedIn).stream()
                .filter(c -> c.getUser().equals(loggedIn))
                .filter(c -> !c.isDone())
                .collect(Collectors.toList());
    }
    
    /**
     * Finished courses of the logged in user
     * @return returns a list of finished courses
     * @throws SQLException if getting the courses fails, throws SQL Exception
     */

    public List<Course> getDoneCourses() throws SQLException {
        if (loggedIn == null) {
            return new ArrayList<>();
        }
        return courseDao.getAll(loggedIn).stream()
                .filter(c -> c.getUser().equals(loggedIn))
                .filter(c -> c.isDone())
                .collect(Collectors.toList());
    }
    
    /**
     * Sets the course as finished
     * @param id the id of the course that is about to be marked finished
     * @param grade the grade of the finished course 
     */

    public void markDone(Course course, int grade) throws SQLException {
        Course c = new Course(course.getId(), getLoggedIn(), course.getName(), course.getCredits(), false, grade);
        
        courseDao.setDone(c.getId(), grade);

    }
    
    /**
     * Login
     * @param username username
     * @param password password
     * @return true if username exists, otherwise false
     * @throws SQLException if getting the user fails, throws SQL Exception
     */

    public boolean login(String username, String password) throws SQLException {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null) {
            return false;
        }
        loggedIn = user;
        return true;
    }
    
    /**
     * Logged in user
     * @return Logged in user
     */
    
    public User getLoggedIn() {
        return loggedIn;
    }
    
    /**
     * Logout
     */
    
    public void logout() {
        this.loggedIn = null;
    }
    
    /**
     * Creating new user 
     * @param user new user to be created
     * @return true if user is created successfully, otherwise false
     * @throws SQLException if the user creation fails, throws SQL Exception
     */

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
