import java.time.LocalDate;

public class Reservation {

    private int reservationId;
    private String customerName;
    private int roomNumber;
    private String roomCategory;
    private double amount;
    private LocalDate date;

    public Reservation(int reservationId, String customerName,
                       int roomNumber, String roomCategory,
                       double amount) {

        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomNumber = roomNumber;
        this.roomCategory = roomCategory;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    public int getReservationId() {
        return reservationId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void displayReservation() {
        System.out.println("\n===== BOOKING DETAILS =====");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Room Number: " + roomNumber);
        System.out.println("Room Category: " + roomCategory);
        System.out.printf("Amount Paid: $%.2f%n", amount);
        System.out.println("Booking Date: " + date);
    }
}