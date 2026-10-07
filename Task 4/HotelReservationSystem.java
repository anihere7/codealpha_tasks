import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Reservation> reservations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    static int nextReservationId = 1;

    public static void main(String[] args) {

        loadRooms();
        loadReservations();

        int choice;

        do {
            System.out.println("\n=================================");
            System.out.println("      HOTEL RESERVATION SYSTEM");
            System.out.println("=================================");
            System.out.println("1. Search Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. View Booking Details");
            System.out.println("4. Cancel Reservation");
            System.out.println("5. Display All Rooms");
            System.out.println("6. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    searchRooms();
                    break;

                case 2:
                    bookRoom();
                    break;

                case 3:
                    viewBooking();
                    break;

                case 4:
                    cancelReservation();
                    break;

                case 5:
                    displayAllRooms();
                    break;

                case 6:
                    saveRooms();
                    saveReservations();
                    System.out.println("Thank you for using Hotel Reservation System!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 6);

        scanner.close();
    }

    // Load rooms
    public static void loadRooms() {

        File file = new File("rooms.txt");

        if (!file.exists()) {

            rooms.add(new Room(101, "Standard", 100));
            rooms.add(new Room(102, "Standard", 100));
            rooms.add(new Room(201, "Deluxe", 180));
            rooms.add(new Room(202, "Deluxe", 180));
            rooms.add(new Room(301, "Suite", 300));
            rooms.add(new Room(302, "Suite", 300));

            return;
        }

        try {
            BufferedReader reader =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int roomNumber = Integer.parseInt(data[0]);
                String category = data[1];
                double price = Double.parseDouble(data[2]);
                boolean available = Boolean.parseBoolean(data[3]);

                Room room = new Room(roomNumber, category, price);
                room.setAvailable(available);

                rooms.add(room);
            }

            reader.close();

        } catch (Exception e) {
            System.out.println("Error loading rooms.");
        }
    }

    // Search rooms
    public static void searchRooms() {

        scanner.nextLine();

        System.out.print(
                "Enter room category (Standard/Deluxe/Suite): "
        );

        String category = scanner.nextLine();

        boolean found = false;

        System.out.println("\n===== AVAILABLE ROOMS =====");

        for (Room room : rooms) {

            if (room.getCategory().equalsIgnoreCase(category)
                    && room.isAvailable()) {

                room.displayRoom();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available rooms found.");
        }
    }

    // Book room
    public static void bookRoom() {

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        Room selectedRoom = findRoom(roomNumber);

        if (selectedRoom == null) {
            System.out.println("Room not found.");
            return;
        }

        if (!selectedRoom.isAvailable()) {
            System.out.println("Room is already booked.");
            return;
        }

        scanner.nextLine();

        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();

        double amount = selectedRoom.getPrice();

        System.out.println("\n===== PAYMENT =====");
        System.out.printf("Amount: $%.2f%n", amount);
        System.out.println("Payment successful!");

        Reservation reservation = new Reservation(
                nextReservationId,
                customerName,
                selectedRoom.getRoomNumber(),
                selectedRoom.getCategory(),
                amount
        );

        reservations.add(reservation);

        selectedRoom.setAvailable(false);

        nextReservationId++;

        saveRooms();
        saveReservations();

        System.out.println("\nRoom booked successfully!");

        reservation.displayReservation();
    }

    // View booking
    public static void viewBooking() {

        System.out.print("Enter reservation ID: ");
        int id = scanner.nextInt();

        for (Reservation reservation : reservations) {

            if (reservation.getReservationId() == id) {

                reservation.displayReservation();
                return;
            }
        }

        System.out.println("Reservation not found.");
    }

    // Cancel reservation
    public static void cancelReservation() {

        System.out.print("Enter reservation ID: ");
        int id = scanner.nextInt();

        Reservation foundReservation = null;

        for (Reservation reservation : reservations) {

            if (reservation.getReservationId() == id) {
                foundReservation = reservation;
                break;
            }
        }

        if (foundReservation == null) {
            System.out.println("Reservation not found.");
            return;
        }

        Room room = findRoom(foundReservation.getRoomNumber());

        if (room != null) {
            room.setAvailable(true);
        }

        reservations.remove(foundReservation);

        saveRooms();
        saveReservations();

        System.out.println(
                "Reservation cancelled successfully."
        );
    }

    // Display all rooms
    public static void displayAllRooms() {

        System.out.println("\n===== ALL ROOMS =====");

        for (Room room : rooms) {
            room.displayRoom();
        }
    }

    // Find room
    public static Room findRoom(int roomNumber) {

        for (Room room : rooms) {

            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }

        return null;
    }

    // Save rooms
    public static void saveRooms() {

        try {

            PrintWriter writer =
                    new PrintWriter("rooms.txt");

            for (Room room : rooms) {

                writer.println(
                        room.getRoomNumber() + "," +
                        room.getCategory() + "," +
                        room.getPrice() + "," +
                        room.isAvailable()
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving rooms.");
        }
    }

    // Save reservations
    public static void saveReservations() {

        try {

            PrintWriter writer =
                    new PrintWriter("reservations.txt");

            for (Reservation reservation : reservations) {

                writer.println(
                        reservation.getReservationId() + "," +
                        reservation.getRoomNumber()
                );
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving reservations."
            );
        }
    }

    // Load reservations
    public static void loadReservations() {

        File file = new File("reservations.txt");

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file)
                    );

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                int roomNumber = Integer.parseInt(data[1]);

                Room room = findRoom(roomNumber);

                if (room != null) {

                    Reservation reservation =
                            new Reservation(
                                    id,
                                    "Existing Customer",
                                    roomNumber,
                                    room.getCategory(),
                                    room.getPrice()
                            );

                    reservations.add(reservation);

                    if (id >= nextReservationId) {
                        nextReservationId = id + 1;
                    }
                }
            }

            reader.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading reservations."
            );
        }
    }
}