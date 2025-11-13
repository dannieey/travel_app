package itinerary;

public class RelaxationRoute implements RouteStrategy {
    @Override
    public String createRoute() {
        return "Relaxation Route: spa, beach, fine dining, sunset cruise";
    }
}
