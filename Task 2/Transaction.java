import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private String stockSymbol;
    private int quantity;
    private double price;
    private LocalDateTime dateTime;

    public Transaction(String type, String stockSymbol,
                       int quantity, double price) {

        this.type = type;
        this.stockSymbol = stockSymbol;
        this.quantity = quantity;
        this.price = price;
        this.dateTime = LocalDateTime.now();
    }

    public void displayTransaction() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println(
                dateTime.format(formatter)
                + " | " + type
                + " | " + stockSymbol
                + " | Quantity: " + quantity
                + " | Price: $"
                + String.format("%.2f", price)
                + " | Total: $"
                + String.format("%.2f", quantity * price)
        );
    }
}