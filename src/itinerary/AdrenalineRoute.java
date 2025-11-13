package itinerary;

public class AdrenalineRoute implements RouteStrategy{
    @Override
    public String createRoute() {
        return "Adrenaline Route: skydiving, mountain biking, surfing, night party";
    }
}