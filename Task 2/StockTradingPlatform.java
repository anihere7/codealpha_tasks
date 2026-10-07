import java.util.ArrayList;
import java.util.Scanner;

public class StockTradingPlatform {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        ArrayList<Stock> stocks = new ArrayList<>();
        ArrayList<Transaction> transactions = new ArrayList<>();

        // Market data
        stocks.add(new Stock("AAPL", "Apple Inc.", 180.00));
        stocks.add(new Stock("GOOGL", "Alphabet Inc.", 140.00));
        stocks.add(new Stock("TSLA", "Tesla Inc.", 250.00));

        // User
        User user = new User("Investor", 10000.00);

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("      STOCK TRADING PLATFORM");
            System.out.println("=================================");
            System.out.println("1. Display Market Data");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. View Transactions");
            System.out.println("6. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n===== MARKET DATA =====");

                    for (Stock stock : stocks) {
                        stock.displayStock();
                    }
                    break;

                case 2:
                    System.out.print("Enter stock symbol: ");
                    String buySymbol = scanner.next().toUpperCase();

                    System.out.print("Enter quantity: ");
                    int buyQuantity = scanner.nextInt();

                    Stock buyStock = findStock(stocks, buySymbol);

                    if (buyStock != null) {

                        boolean bought = user.buyStock(
                                buyStock.getSymbol(),
                                buyQuantity,
                                buyStock.getPrice()
                        );

                        if (bought) {
                            transactions.add(
                                    new Transaction(
                                            "BUY",
                                            buyStock.getSymbol(),
                                            buyQuantity,
                                            buyStock.getPrice()
                                    )
                            );
                        }

                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock symbol: ");
                    String sellSymbol = scanner.next().toUpperCase();

                    System.out.print("Enter quantity: ");
                    int sellQuantity = scanner.nextInt();

                    Stock sellStock = findStock(stocks, sellSymbol);

                    if (sellStock != null) {

                        boolean sold = user.sellStock(
                                sellStock.getSymbol(),
                                sellQuantity,
                                sellStock.getPrice()
                        );

                        if (sold) {
                            transactions.add(
                                    new Transaction(
                                            "SELL",
                                            sellStock.getSymbol(),
                                            sellQuantity,
                                            sellStock.getPrice()
                                    )
                            );
                        }

                    } else {
                        System.out.println("Stock not found.");
                    }
                    break;

                case 4:
                    user.displayPortfolio();
                    break;

                case 5:
                    System.out.println("\n===== TRANSACTIONS =====");

                    if (transactions.isEmpty()) {
                        System.out.println("No transactions yet.");
                    } else {
                        for (Transaction transaction : transactions) {
                            transaction.displayTransaction();
                        }
                    }
                    break;

                case 6:
                    System.out.println(
                            "Thank you for using Stock Trading Platform!"
                    );
                    break;

                default:
                    System.out.println(
                            "Invalid choice. Please try again."
                    );
            }

        } while (choice != 6);

        scanner.close();
    }

    public static Stock findStock(
            ArrayList<Stock> stocks,
            String symbol) {

        for (Stock stock : stocks) {

            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }

        return null;
    }
}