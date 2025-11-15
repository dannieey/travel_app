package itinerary;
import java.util.ArrayList;
import java.util.List;

public class ItineraryBuilder {
    private RouteStrategy strategy;
    private String destination;
    private List<String> stops = new ArrayList<>();

    public void reset() {
        stops.clear();
    }

    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void build() {
        if (strategy == null || destination == null) return;
        stops = new ArrayList<>(strategy.generateStops(destination));
    }

    public List<String> getItinerary() {
        return stops;
    }

    public double estimateAttractionsCost() {
        if (strategy == null || destination == null) return 0;
        return strategy.estimateAttractionsCost(destination);
    }

    // грубая оценка стоимости проживания (пример)
    public double estimateLodgingCost() {
        return 70.0 * 3; // 3 ночи по 70
    }
}
