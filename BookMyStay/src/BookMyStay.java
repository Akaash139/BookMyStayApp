import java.io.*;
import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * @version 12.0
 */

public class BookMyStay {

    // ================= RESERVATION =================
    static class Reservation implements Serializable {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public void display() {
            System.out.println(guestName + " | " + roomType);
        }
    }

    // ================= INVENTORY =================
    static class RoomInventory implements Serializable {
        Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 2);
            inventory.put("Double", 1);
        }

        public void display() {
            System.out.println("\nInventory:");
            for (String key : inventory.keySet()) {
                System.out.println(key + ": " + inventory.get(key));
            }
        }
    }

    // ================= PERSISTENCE SERVICE =================
    static class PersistenceService {

        private static final String FILE_NAME = "hotel_data.ser";

        // SAVE (Serialization)
        public void save(RoomInventory inventory, List<Reservation> bookings) {
            try (ObjectOutputStream oos =
                         new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {

                oos.writeObject(inventory);
                oos.writeObject(bookings);

                System.out.println("\nData saved successfully.");

            } catch (IOException e) {
                System.out.println("Error saving data: " + e.getMessage());
            }
        }

        // LOAD (Deserialization)
        public Object[] load() {
            try (ObjectInputStream ois =
                         new ObjectInputStream(new FileInputStream(FILE_NAME))) {

                RoomInventory inventory = (RoomInventory) ois.readObject();
                List<Reservation> bookings = (List<Reservation>) ois.readObject();

                System.out.println("\nData loaded successfully.");
                return new Object[]{inventory, bookings};

            } catch (Exception e) {
                System.out.println("\nNo previous data found. Starting fresh.");
                return null;
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Persistence & Recovery System ");
        System.out.println("=======================================");

        PersistenceService service = new PersistenceService();

        // Try loading previous data
        Object[] data = service.load();

        RoomInventory inventory;
        List<Reservation> bookings;

        if (data != null) {
            inventory = (RoomInventory) data[0];
            bookings = (List<Reservation>) data[1];
        } else {
            // fresh start
            inventory = new RoomInventory();
            bookings = new ArrayList<>();
        }

        // Simulate new booking
        bookings.add(new Reservation("Akaash", "Single"));

        // Display current state
        inventory.display();

        System.out.println("\nBookings:");
        for (Reservation r : bookings) {
            r.display();
        }

        // Save before exit
        service.save(inventory, bookings);
    }
}}