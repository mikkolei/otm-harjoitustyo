
package opintoapp.domain;

public class Course {
    
    private String name;
    private int credit;
    private boolean done;
    private int grade;
    private User user;

    public Course(String name, int credit, boolean done, User user) {
        this.name = name;
        this.credit = credit;
        this.done = done;
        this.user = user;
    }

    public String getName() {
        return name;
    }

//    public int getCredit() {
//        return credit;
//    }

    public boolean isDone() {
        return done;
    }

    public User getUser() {
        return user;
    }
    
    
    
    
}
