package booking;

public class MealDecorator extends BookingDecorator {
    private double mealCost = 20.0;

    public MealDecorator(Booking wrapped) {
        super(wrapped);
    }

    @Override
    public String getDescription() {
        return wrapped.getDescription() + " + Meal";
    }

    @Override
    public double getPrice() {
        return wrapped.getPrice() + mealCost;
    }
}
