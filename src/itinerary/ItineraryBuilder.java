package itinerary;

import java.util.ArrayList;
import java.util.List;

public class ItineraryBuilder {
    private List<String> itinerarySteps = new ArrayList<>();

    public void addStep(String step) {
        itinerarySteps.add(step);
    }
    public void clear(){
        itinerarySteps.clear();
    }
    public String build(){
        return String.join("\n", itinerarySteps);
    }
    public List<String> getSteps() {
        return itinerarySteps;
    }
}