
package opintoapp.domain;

public class Course {
    
    private int id;
    private String name;
    private int credits;
    private boolean done;
    private int grade;
    private User user;

    public Course(int id, String name, int credits, boolean done, int grade, User user) {
        this.id = id;
        this.name = name;
        this.credits = credits;
        this.done = done;
        this.grade = grade;
        this.user = user;
    }
    
    public Course(User user, String name, int credits) {
//        this.id = id;
        this.user = user;
        this.name = name;
        this.credits = credits;
        this.done = false;
    }

    public String getName() {
        return name;
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
    
    public void setDone() {
        this.done = true;
    }
    
    public void setGrade(int grade) {
        this.grade = grade;
    }
    
    @Override
    public String toString() {
        return this.name;
    }
    
}
