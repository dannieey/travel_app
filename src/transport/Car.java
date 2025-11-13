package transport;

public class Car implements Transport {
    private String model;
    private double distanceKm;

    public Car(String model, double distanceKm) {
        this.model = model;
        this.distanceKm = distanceKm;
    }
    @Override
    public double getCost() {
        double fuelPrice=1.2;
        return distanceKm*fuelPrice;
    }

    @Override
    public String getDescription() {
        return "Car"+model+" for " + distanceKm+"km";
    }
}
