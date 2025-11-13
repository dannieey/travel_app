package utils;

public class Helpers {
    public static double calculateBudget(double base, int days, double extras) {
        return base * days + extras;
    }
    public static String formatDays(int days) {
        return days + (days == 1 ? " day" : " days");
    }
}