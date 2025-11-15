package booking;

public class BusinessClassDecorator extends BookingDecorator {

    public BusinessClassDecorator(Booking wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + Business Class";
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + 110.0;
    }


}
