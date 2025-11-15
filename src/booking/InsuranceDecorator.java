package booking;

public class InsuranceDecorator extends BookingDecorator {
    private double insuranceCost = 15.0;

    public InsuranceDecorator(Booking wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + Insurance";
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + insuranceCost;
    }
}
