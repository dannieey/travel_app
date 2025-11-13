package booking;

public class MealDecorator implements Booking{
    private Booking booking;
    private double mealCost=20;
    public MealDecorator(Booking booking) {
        this.booking = booking;
    }
    @Override
    public String getDescription() {
        return booking.getDescription()+" + Meal";
    }

    @Override
    public double getCost() {
        return booking.getCost()+mealCost;
    }
}
