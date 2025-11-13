package booking;

public class InsuranceDecorator implements Booking{
    private Booking booking;
    private double insuranceCoat=50;
    public InsuranceDecorator(Booking booking) {
        this.booking = booking;
    }
    @Override
    public String getDescription() {
        return booking.getDescription()+" +Insurance";
    }

    @Override
    public double getCost() {
        return booking.getCost()+insuranceCoat;
    }
}
