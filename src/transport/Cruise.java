package transport;

public class Cruise implements Transport{
    private String shipName;
    private int durationDays;
    public Cruise(String shipName, int durationDays) {
        this.shipName = shipName;
        this.durationDays = durationDays;
    }
    @Override
    public double getCost() {
        return durationDays*100;
    }

    @Override
    public String getDescription() {
        return "Cruise "+shipName+"for " +durationDays+" days";
    }
}
