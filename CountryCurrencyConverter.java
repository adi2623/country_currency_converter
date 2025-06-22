import java.util.*;

public class CountryCurrencyConverter {

    private static final LinkedHashMap<String, String> COUNTRY_TO_CURRENCY = new LinkedHashMap<>();
    private static final Map<String, Double> CURRENCY_TO_USD = new HashMap<>();
    private static final Scanner SCANNER = new Scanner(System.in);

    static {
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

        CURRENCY_TO_USD.put("USD", 1.0);
        CURRENCY_TO_USD.put("INR", 83.2);
        CURRENCY_TO_USD.put("EUR", 0.92);
        CURRENCY_TO_USD.put("GBP", 0.78);
        CURRENCY_TO_USD.put("JPY", 157.3);
        CURRENCY_TO_USD.put("AUD", 1.49);
        CURRENCY_TO_USD.put("CAD", 1.37);
        CURRENCY_TO_USD.put("CHF", 0.89);
        CURRENCY_TO_USD.put("CNY", 7.26);
        CURRENCY_TO_USD.put("RUB", 89.5);
        CURRENCY_TO_USD.put("BRL", 5.42);
        CURRENCY_TO_USD.put("ZAR", 18.11);
        CURRENCY_TO_USD.put("MXN", 18.5);
        CURRENCY_TO_USD.put("KRW", 1380.0);
        CURRENCY_TO_USD.put("SGD", 1.35);
        CURRENCY_TO_USD.put("NZD", 1.61);
        CURRENCY_TO_USD.put("SEK", 10.8);
        CURRENCY_TO_USD.put("NOK", 10.5);
        CURRENCY_TO_USD.put("TRY", 32.5);
        CURRENCY_TO_USD.put("IDR", 16000.0);
        CURRENCY_TO_USD.put("THB", 36.7);
        CURRENCY_TO_USD.put("MYR", 4.7);
        CURRENCY_TO_USD.put("BDT", 117.0);
        CURRENCY_TO_USD.put("PKR", 278.0);
        CURRENCY_TO_USD.put("AED", 3.67);
    }

    public static void main(String[] args) {
        printWelcome();

        boolean continueConversion = true;
        while (continueConversion) {
            displayCountryList();

            String fromCountry = selectCountry("🌍 FROM Country Number");
            if (fromCountry == null) continue;

            String toCountry = selectCountry("✈️ TO Country Number");
            if (toCountry == null) continue;

            double amount = enterAmount();
            if (amount < 0) continue;

            String fromCurrency = COUNTRY_TO_CURRENCY.get(fromCountry);
            String toCurrency = COUNTRY_TO_CURRENCY.get(toCountry);

            double converted = convertCurrency(fromCurrency, toCurrency, amount);

            printResult(fromCountry, toCountry, fromCurrency, toCurrency, amount, converted);

            System.out.print("\n🔁 Would you like to convert again? (yes/no): ");
            continueConversion = SCANNER.nextLine().trim().equalsIgnoreCase("yes");
        }

        System.out.println("\n🌟 Thank you for using the Currency Converter! Goodbye.");
    }

    private static void printWelcome() {
        System.out.println("╔════════════════════════════════════════════╗");
        System.out.println("║          🌎 WELCOME TO CURRENCY HUB        ║");
        System.out.println("╚════════════════════════════════════════════╝");
    }

    private static void displayCountryList() {
        System.out.println("\n📋 Available Countries:");
        int i = 1;
        for (String country : COUNTRY_TO_CURRENCY.keySet()) {
            System.out.printf(" %2d. %-20s [%s]%n", i++, country, COUNTRY_TO_CURRENCY.get(country));
        }
    }

    private static String selectCountry(String promptLabel) {
        try {
            System.out.print("\n" + promptLabel + ": ");
            int selection = Integer.parseInt(SCANNER.nextLine().trim());
            List<String> countryList = new ArrayList<>(COUNTRY_TO_CURRENCY.keySet());
            if (selection < 1 || selection > countryList.size()) {
                System.out.println("⚠️ Invalid selection. Please enter a valid number.");
                return null;
            }
            return countryList.get(selection - 1);
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Please enter a valid number.");
            return null;
        }
    }

    private static double enterAmount() {
        try {
            System.out.print("\n💰 Enter amount to convert: ");
            double amount = Double.parseDouble(SCANNER.nextLine().trim());
            if (amount < 0) {
                System.out.println("⚠️ Amount must be positive.");
                return -1;
            }
            return amount;
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid amount entered.");
            return -1;
        }
    }

    private static double convertCurrency(String from, String to, double amount) {
        double usdValue = amount / CURRENCY_TO_USD.get(from);
        return usdValue * CURRENCY_TO_USD.get(to);
    }

    private static void printResult(String fromCountry, String toCountry, String fromCurrency, String toCurrency, double amount, double result) {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("🔄 Converting %.2f %s (%s)%n", amount, fromCurrency, fromCountry);
        System.out.printf("✅ Result: %.2f %s (%s)%n", result, toCurrency, toCountry);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }
}
