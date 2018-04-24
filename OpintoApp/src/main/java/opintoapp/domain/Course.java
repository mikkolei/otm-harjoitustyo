
package opintoapp.domain;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Course {
    
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty("");
    private int credits;
    private boolean done;
    private int grade;
    private User user;

    public Course(User user, String name, int credits, boolean done, int grade) {
//        this.id = id;
        this.name.set(name);
        this.credits = credits;
        this.done = done;
        this.grade = grade;
        this.user = user;
    }
    
    public Course(User user, String name, int credits) {
//        this.id = id;
        this.user = user;
        this.name.set(name);
        this.credits = credits;
        this.done = false;
    }

    public String getName() {
        return this.name.getValue();
    }
    

    public int getCredits() {
        return credits;
    }

    public boolean isDone() {
        return done;
    }

    public User getUser() {
        return user;
    }
    
    public int getGrade() {
        return grade;
    }
    
    public void setDone() {
        this.done = true;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    @Override
    public String toString() {
        return this.name.get() + ", " + this.credits;
    }
    
}
