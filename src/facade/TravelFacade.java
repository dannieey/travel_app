package facade;

import itinerary.*;
import transport.*;
import booking.*;

import java.util.Locale;

public class TravelFacade {
    private ItineraryBuilder itineraryBuilder = new ItineraryBuilder();
    private Transport selectedTransport;
    private Booking booking = new Booking();

    public void selectRoute(String type){
        RouteStrategy strategy;
        switch (type.toLowerCase()){
            case "relaxation":
                strategy = new RelaxationRoute();
                break;
            case "adrenaline":
                strategy = new AdrenalineRoute();
                break;
            case "romance":
                strategy = new RomanceRoute();
                break;
            default:
                System.out.println("Unknown route type. Using default relaxation.");
                strategy = new RelaxationRoute();
        }

        String route = strategy.createRoute();
        itineraryBuilder.clear();
        itineraryBuilder.addStep(route);
        System.out.println("Route selected: " + route);
    }

    public void selectTransport(String type){
        selectedTransport = TransportFactory.createTransport(type);
        if (selectedTransport != null){
            System.out.println("Selected transport: " + selectedTransport.getDescription());
            itineraryBuilder.addStep("Transport: "+ selectedTransport.getDescription());
        }else {
            System.out.println("Unknown transport type!");
        }
    }

    public void addBookingOption(String option){
        switch (option.toLowerCase()){
            case "meal":
                booking = new MealDecorator(booking);
                break;
            case "insurance":
                booking = new InsuranceDecorator(booking);
                break;
            default:
                System.out.println("Unknown booking option!" + option);
        }
        System.out.println("Booking option added: " + option);
    }

    public String getItinerary(){
        return itineraryBuilder.build();
    }

    public void showFullPlan(){
        System.out.println("Full Travel Plan:");
        System.out.println("Itinerary: " + getItinerary());
        if (selectedTransport != null){
            System.out.println("Transport: "+ selectedTransport.getDescription());
        }
        System.out.println("Booking details: " + booking.getDescription());
    }
}