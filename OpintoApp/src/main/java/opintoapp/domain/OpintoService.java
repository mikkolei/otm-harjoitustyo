
package opintoapp.domain;

public class OpintoService {
    
//    private CourseDao opintoDao;
//    private UserDao userDao;
    private User loggedIn; 

    public OpintoService() {
    }
    
    public boolean createCourse(String content, int credit) {
        boolean notReady = false;
        Course course = new Course(content, credit, notReady, loggedIn);
        return false;
    }
    
    public boolean login(String username, String password) {
//        User user = userDao.findByUsername(username);
          return false;
    }
    
    public boolean createUser(String name, String username, String password) {
        
        return false;
    }
    
}
