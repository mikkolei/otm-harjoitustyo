
package opintoapp.domain;

import java.sql.SQLException;
import opintoapp.dao.*;

public class StudyService {
    
    private CourseDao courseDao;
    private UserDao userDao;
    private User loggedIn; 

    public StudyService(UserDao userDao, CourseDao courseDao) {
        this.userDao = userDao;
        this.courseDao = courseDao;
    }
    
    public boolean createCourse(String content, int credit) {
        boolean notReady = false;
        Course course = new Course(content, credit, loggedIn);
        return false;
    }
    
    public boolean login(String username, String password) throws SQLException {
        User user = userDao.findByUsernameAndPassword(username, password);
        if (user == null)
            return false;
        loggedIn = user;
        return true;
    }
    
    public boolean createUser(String name, String username, String password) throws SQLException {
        if(userDao.findByUsernameAndPassword(username, password) != null) {
            return false;
        }
        User user = new User(name, username, password);
        try {
            userDao.create(user);
            return true;
        } catch(Exception ex) {
            return false;
        }
        
    }
    
}
