package transport;

public class TransportFactory {
    public static Transport createTransport(String type, String name, String seatClass, double distanceOrDays){
        switch(type.toLowerCase()){
            case "plane":return new Plane(name,seatClass);
            case "car":return new Car(name, distanceOrDays);
            case "cruise":return new Cruise(name, (int)distanceOrDays);
            default:throw new IllegalArgumentException("Unknown transport type");
        }
    }
}
