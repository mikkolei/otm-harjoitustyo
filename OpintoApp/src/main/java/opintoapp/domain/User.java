
package opintoapp.domain;

/**
 * Class to represent the user of the system
 */

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private int id;
    private String name;
    private String username;
    private String password;
    private List<Course> courses;

    public User(int id, String name, String username, String password) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        courses = new ArrayList<>();
    }
    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        courses = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    
    public List<Course> getCourses(User user) {
        return user.courses;
    }
    
    public void addCourse(Course c) {
        courses.add(c);
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        
        User other = (User) object;
        return username.equals(other.username);
    }
    
    @Override
    public String toString() {
        return this.username;
    }
}
