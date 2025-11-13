package transport;

public class Plane implements Transport {
    private String airline;
    private String seatClass;

    public Plane(String airLine, String seatClass) {
        this.airline = airline;
        this.seatClass = seatClass;
    }

    @Override
    public double getCost() {
        return seatClass.equals("Econimy")?200:500;
    }

    @Override
    public String getDescription() {
        return airline +"Plane (" + seatClass + ")";
    }
}
