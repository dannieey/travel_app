

import booking.BusinessClassDecorator;
import facade.TravelFacade;
import booking.PriceSubject;
import booking.UserObserver;

import java.util.Scanner;

public class Runner {

    public static void run() {
        Scanner sc = new Scanner(System.in);
        TravelFacade travel = new TravelFacade();
        PriceSubject priceSubject = travel.getPriceSubject();

        System.out.println("=== TravelApp Console Demo ===");

        // Имя пользователя для уведомлений
        System.out.print("Enter your name (for notifications): ");
        String userName = sc.nextLine();
        UserObserver user = new UserObserver(userName);
        priceSubject.registerObserver(user);

        // Город отправления
        System.out.print("Where are you departing from? ");
        String origin = sc.nextLine();

        // Назначение
        System.out.print("Enter your destination country/city: ");
        String destination = sc.nextLine();



        // Выбор транспорта
        System.out.print("Choose transport (plane/car/cruise): ");
        String transport = sc.nextLine();
        travel.selectTransport(transport);

        // Выбор класса билета только если plane
        // Выбор класса билета только если plane
        System.out.print("Select ticket class (economy/business): ");
        String seatClass = sc.nextLine();
        travel.applySeatClassUpgrade(seatClass);

        // Пересчёт стоимости сразу после выбора бизнес-класса
        double total = travel.calculateTotalCost();
        System.out.printf("Current total cost with seat class: %.2f\n", total);




        // Даты
        System.out.print("Departure date (dd-mm-yyyy): ");
        String dateTo = sc.nextLine();
        System.out.print("Return date (dd-mm-yyyy) or empty: ");
        String dateBack = sc.nextLine();

        // Бронирование
        travel.bookTransport(origin, destination, dateTo, dateBack);

        // Доп. опции
        System.out.print("Would you like to add meal option? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            travel.addMealOption();
        }

        System.out.print("Would you like to add insurance? (yes/no): ");
        if (sc.nextLine().equalsIgnoreCase("yes")) {
            travel.addInsuranceOption();
        }
        // Выбор маршрута / настроения
        System.out.print("Choose travel mood (relaxation/adrenaline/romance/custom): ");
        String mood = sc.nextLine();
        travel.selectRoute(mood, destination);





        // Отчёт
        System.out.println("\n=== TRIP SUMMARY ===");
        System.out.println("Traveler: " + userName);
        System.out.println("Origin: " + origin);
        System.out.println("Destination: " + destination);
        System.out.println("Transport: " + transport);
        if (transport.equalsIgnoreCase("plane")) {
            System.out.println("Seat class: " + (travel.getBooking() instanceof BusinessClassDecorator ? "Business" : "Economy"));
        }
        System.out.println("Departure date: " + dateTo);
        System.out.println("Return date: " + (dateBack.isEmpty() ? "One-way" : dateBack));
        System.out.println("Meal option: " + (travel.isMealAdded() ? "Included" : "Not included"));
        System.out.println("Insurance: " + (travel.isInsuranceAdded() ? "Included" : "Not included"));
        System.out.println("Mood / Route Type: " + mood);

        System.out.println("Planned Stops: " + String.join(", ", travel.getPlannedStops()));

        System.out.printf("FINAL TOTAL COST: %.2f\n", total);
        System.out.println("======================");

        //UPDATE
        System.out.println("\nPrice drop alert...");
        priceSubject.setPrice(priceSubject.getPrice() - 50.0);
        total = travel.calculateTotalCost();
        System.out.printf("Updated total cost after price drop: %.2f\n", total);


        System.out.println("Demo finished.");
        sc.close();
    }
}
