package booking;

public class UserObserver {
    private String name;
    public UserObserver(String name) {
        this.name=name;
    }
    public void update(double price) {
        System.out.println("Notification for " + name+":+ Price changed to "+price+"$");
    }
}
