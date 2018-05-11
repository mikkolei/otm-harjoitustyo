
package opintoapp.domain;

import javafx.beans.property.SimpleStringProperty;

/**
 * Class to represent a single course
 */
public class Course {
    
    private int id;
    private SimpleStringProperty name = new SimpleStringProperty("");
    private int credits;
    private boolean done;
    private int grade;
    private User user;

    public Course(int id, User user, String name, int credits, boolean done, int grade) {
        this.id = id;
        this.user = user;
        this.name.set(name);
        this.credits = credits;
        this.done = done;
        this.grade = grade;
    }
    
    public Course(User user, String name, int credits) {
        this.user = user;
        this.name.set(name);
        this.credits = credits;
        this.done = false;
    }
    
    public int getId() {
        return id;
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
        return this.user;
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
    
    public void setId(int id) { 
        this.id = id;
    }
    
    @Override
    public String toString() {
        return this.name.get() + ", " + this.credits;
    }
    
}
