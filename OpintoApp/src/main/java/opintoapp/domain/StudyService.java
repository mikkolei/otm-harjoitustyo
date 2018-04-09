
package opintoapp.domain;

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
    
    public boolean login(String username, String password) {
//        User user = userDao.findByUsername(username);
          return false;
    }
    
    public boolean createUser(String name, String username, String password) {
        if(userDao.findByName(username) != null) {
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
