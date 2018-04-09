
package opintoapp.domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    
    private String name;
    private String username;
    private String password;
    private List<Course> courses;

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        courses = new ArrayList<>();
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

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Course> getCourses(User user) {
        return user.courses;
    }
    
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof User)) {
            return false;
        }
        
        User other = (User) object;
        return username.equals(other.username);
    }
    
}
