
package opintoapp.domain;

public class Course {
    
    private String name;
    private int credits;
    private boolean done;
    private String grade;
    private User user;

    public Course(String name, int credit, boolean done, String grade, User user) {
        this.name = name;
        this.credits = credit;
        this.done = done;
        this.user = user;
    }
    
    public Course(String name, int credit, User user) {
        this.name = name;
        this.credits = credit;
        this.user = user;
        this.done = false;
    }

    public String getName() {
        return name;
    }

    public int getCredit() {
        return credits;
    }

    public boolean isDone() {
        return done;
    }

    public User getUser() {
        return user;
    }
    
    
    
    
}
