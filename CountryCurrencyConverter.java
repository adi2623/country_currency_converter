import java.util.*;

public class CountryCurrencyConverter {

    private static final LinkedHashMap<String, String> COUNTRY_TO_CURRENCY = new LinkedHashMap<>();
    private static final Map<String, Double> CURRENCY_TO_USD_RATE = new HashMap<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    static {
        // Country to currency code
        COUNTRY_TO_CURRENCY.put("India", "INR");
        COUNTRY_TO_CURRENCY.put("United States", "USD");
        COUNTRY_TO_CURRENCY.put("Eurozone", "EUR");
        COUNTRY_TO_CURRENCY.put("United Kingdom", "GBP");
        COUNTRY_TO_CURRENCY.put("Japan", "JPY");
        COUNTRY_TO_CURRENCY.put("Australia", "AUD");
        COUNTRY_TO_CURRENCY.put("Canada", "CAD");
        COUNTRY_TO_CURRENCY.put("Switzerland", "CHF");
        COUNTRY_TO_CURRENCY.put("China", "CNY");
        COUNTRY_TO_CURRENCY.put("Russia", "RUB");
        COUNTRY_TO_CURRENCY.put("Brazil", "BRL");
        COUNTRY_TO_CURRENCY.put("South Africa", "ZAR");
        COUNTRY_TO_CURRENCY.put("Mexico", "MXN");
        COUNTRY_TO_CURRENCY.put("South Korea", "KRW");
        COUNTRY_TO_CURRENCY.put("Singapore", "SGD");
        COUNTRY_TO_CURRENCY.put("New Zealand", "NZD");
        COUNTRY_TO_CURRENCY.put("Sweden", "SEK");
        COUNTRY_TO_CURRENCY.put("Norway", "NOK");
        COUNTRY_TO_CURRENCY.put("Turkey", "TRY");
        COUNTRY_TO_CURRENCY.put("Indonesia", "IDR");
        COUNTRY_TO_CURRENCY.put("Thailand", "THB");
        COUNTRY_TO_CURRENCY.put("Malaysia", "MYR");
        COUNTRY_TO_CURRENCY.put("Bangladesh", "BDT");
        COUNTRY_TO_CURRENCY.put("Pakistan", "PKR");
        COUNTRY_TO_CURRENCY.put("UAE", "AED");

        // Currency to USD conversion rates
        CURRENCY_TO_USD_RATE.put("USD", 1.0);
        CURRENCY_TO_USD_RATE.put("INR", 83.2);
        CURRENCY_TO_USD_RATE.put("EUR", 0.92);
        CURRENCY_TO_USD_RATE.put("GBP", 0.78);
        CURRENCY_TO_USD_RATE.put("JPY", 157.3);
        CURRENCY_TO_USD_RATE.put("AUD", 1.49);
        CURRENCY_TO_USD_RATE.put("CAD", 1.37);
        CURRENCY_TO_USD_RATE.put("CHF", 0.89);
        CURRENCY_TO_USD_RATE.put("CNY", 7.26);
        CURRENCY_TO_USD_RATE.put("RUB", 89.5);
        CURRENCY_TO_USD_RATE.put("BRL", 5.42);
        CURRENCY_TO_USD_RATE.put("ZAR", 18.11);
        CURRENCY_TO_USD_RATE.put("MXN", 18.5);
        CURRENCY_TO_USD_RATE.put("KRW", 1380.0);
        CURRENCY_TO_USD_RATE.put("SGD", 1.35);
        CURRENCY_TO_USD_RATE.put("NZD", 1.61);
        CURRENCY_TO_USD_RATE.put("SEK", 10.8);
        CURRENCY_TO_USD_RATE.put("NOK", 10.5);
        CURRENCY_TO_USD_RATE.put("TRY", 32.5);
        CURRENCY_TO_USD_RATE.put("IDR", 16000.0);
        CURRENCY_TO_USD_RATE.put("THB", 36.7);
        CURRENCY_TO_USD_RATE.put("MYR", 4.7);
        CURRENCY_TO_USD_RATE.put("BDT", 117.0);
        CURRENCY_TO_USD_RATE.put("PKR", 278.0);
        CURRENCY_TO_USD_RATE.put("AED", 3.67);
    }

    public static void main(String[] args) {
        System.out.println("=== Currency Converter ===");
        displayCountries();

        String fromCountry = selectCountry("FROM");
        if (fromCountry == null) return;

        String toCountry = selectCountry("TO");
        if (toCountry == null) return;

        double amount = inputAmount();
        if (amount < 0) return;

        String fromCurrency = COUNTRY_TO_CURRENCY.get(fromCountry);
        String toCurrency = COUNTRY_TO_CURRENCY.get(toCountry);

        double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
        System.out.printf("%n%.2f %s (%s) = %.2f %s (%s)%n",
                amount, fromCurrency, fromCountry,
                convertedAmount, toCurrency, toCountry);
    }

    private static void displayCountries() {
        int index = 1;
        for (String country : COUNTRY_TO_CURRENCY.keySet()) {
            System.out.printf("%2d. %s%n", index++, country);
        }
    }

    private static String selectCountry(String label) {
        System.out.printf("%nEnter number for %s country: ", label);
        try {
            int choice = Integer.parseInt(SCANNER.nextLine().trim());
            if (choice < 1 || choice > COUNTRY_TO_CURRENCY.size()) {
                System.out.println("Invalid selection. Please try again.");
                return null;
            }
            return new ArrayList<>(COUNTRY_TO_CURRENCY.keySet()).get(choice - 1);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return null;
        }
    }

    private static double inputAmount() {
        System.out.print("Enter amount to convert: ");
        try {
            double amount = Double.parseDouble(SCANNER.nextLine().trim());
            if (amount < 0) {
                System.out.println("Amount must be positive.");
                return -1;
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount entered.");
            return -1;
        }
    }

    private static double convertCurrency(String fromCode, String toCode, double amount) {
        double inUSD = amount / CURRENCY_TO_USD_RATE.get(fromCode);
        return inUSD * CURRENCY_TO_USD_RATE.get(toCode);
    }
}
