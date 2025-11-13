package booking;

import java.util.ArrayList;
import java.util.List;

public class PriceSubject {
    private List<UserObserver> observers=new ArrayList<>();
    private double price;
    public void addObserver(UserObserver observer) {
        observers.add(observer);

    }
    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    private void notifyObservers() {
        for (UserObserver observer : observers) {
            observer.update(price);
        }
    }
}
