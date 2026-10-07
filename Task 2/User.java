import java.util.HashMap;

public class User {

    private String name;
    private double balance;
    private HashMap<String, Integer> portfolio;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.portfolio = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public HashMap<String, Integer> getPortfolio() {
        return portfolio;
    }

    public boolean buyStock(String symbol, int quantity, double price) {

        double totalCost = quantity * price;

        if (totalCost > balance) {
            System.out.println("Insufficient balance.");
            return false;
        }

        balance -= totalCost;

        portfolio.put(
            symbol,
            portfolio.getOrDefault(symbol, 0) + quantity
        );

        System.out.println(
            "Bought " + quantity + " shares of " + symbol
        );

        return true;
    }

    public boolean sellStock(String symbol, int quantity, double price) {

        int ownedQuantity = portfolio.getOrDefault(symbol, 0);

        if (ownedQuantity < quantity) {
            System.out.println("Not enough shares to sell.");
            return false;
        }

        balance += quantity * price;

        int remaining = ownedQuantity - quantity;

        if (remaining == 0) {
            portfolio.remove(symbol);
        } else {
            portfolio.put(symbol, remaining);
        }

        System.out.println(
            "Sold " + quantity + " shares of " + symbol
        );

        return true;
    }

    public void displayPortfolio() {

        System.out.println("\n===== PORTFOLIO =====");
        System.out.println("User: " + name);
        System.out.printf("Balance: $%.2f%n", balance);

        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            System.out.println("Stocks:");

            for (String symbol : portfolio.keySet()) {
                System.out.println(
                    symbol + " : " + portfolio.get(symbol) + " shares"
                );
            }
        }
    }
}