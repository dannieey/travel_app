package transport;

public class TransportFactory {
    public Transport createTransport(String type) {
        if (type == null) return null;
        switch (type.toLowerCase()) {
            case "plane":
                return new Plane(300.0);
            case "car":
                return new Car(120.0); // бензин/аренда пример
            case "cruise":
                return new Cruise(600.0);
            default:
                return null;
        }
    }
}
