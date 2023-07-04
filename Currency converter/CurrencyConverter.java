import java.util.Scanner;

public class CurrencyConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to Currency Converter!");
        System.out.println("Available currencies: USD, EUR, GBP");
        System.out.print("Enter the currency you want to convert from: ");
        String fromCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter the currency you want to convert to: ");
        String toCurrency = scanner.nextLine().toUpperCase();
        System.out.print("Enter the amount: ");
        double amount = scanner.nextDouble();
        
        double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
        System.out.println(amount + " " + fromCurrency + " = " + convertedAmount + " " + toCurrency);
    }
    
    public static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        // Exchange rates as of September 2021
        double usdToEurRate = 0.85;
        double usdToGbpRate = 0.73;
        double eurToUsdRate = 1.18;
        double eurToGbpRate = 0.86;
        double gbpToUsdRate = 1.37;
        double gbpToEurRate = 1.16;
        
        double convertedAmount;
        
        if (fromCurrency.equals("USD") && toCurrency.equals("EUR")) {
            convertedAmount = amount * usdToEurRate;
        } else if (fromCurrency.equals("USD") && toCurrency.equals("GBP")) {
            convertedAmount = amount * usdToGbpRate;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("USD")) {
            convertedAmount = amount * eurToUsdRate;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("GBP")) {
            convertedAmount = amount * eurToGbpRate;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("USD")) {
            convertedAmount = amount * gbpToUsdRate;
        } else if (fromCurrency.equals("GBP") && toCurrency.equals("EUR")) {
            convertedAmount = amount * gbpToEurRate;
        } else {
            // Unsupported currency pair
            throw new IllegalArgumentException("Unsupported currency pair: " + fromCurrency + " to " + toCurrency);
        }
        
        return convertedAmount;
    }
}
