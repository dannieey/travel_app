package booking;

public class BaseBooking implements Booking {

    @Override
    public String getDescription() {
        return "Base Booking";
    }

    @Override
    public double getCost() {
        return 0;
    }
}
