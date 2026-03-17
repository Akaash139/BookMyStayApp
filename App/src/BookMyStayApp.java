import java.util.HashMap;

/**
 * ============================================================
 * MAIN CLASS - UseCase3RoomInventory
 * ============================================================
 *
 * Use Case 3: Centralized Room Inventory Management
 *
 * @version 3.0
 */

public class BookMyStayApp{

    /**
     * Room Inventory Class (Centralized)
     */
    static class RoomInventory {

        private HashMap<String, Integer> inventory;

        // Constructor
        public RoomInventory() {
            inventory = new HashMap<>();

            // Initialize room availability
            inventory.put("Single", 5);
            inventory.put("Double", 3);
            inventory.put("Suite", 2);
        }

        // Get availability
        public int getAvailability(String roomType) {
            return inventory.getOrDefault(roomType, 0);
        }

        // Update availability
        public void updateAvailability(String roomType, int count) {
            inventory.put(roomType, count);
        }

        // Display all inventory
        public void displayInventory() {
            System.out.println("\n=== Current Room Inventory ===");

            for (String room : inventory.keySet()) {
                System.out.println(room + " Rooms Available: " + inventory.get(room));
            }
        }
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Hotel Room Inventory System ");
        System.out.println("=======================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Check availability
        System.out.println("\nChecking Single Room Availability...");
        System.out.println("Available: " + inventory.getAvailability("Single"));

        // Update inventory (simulate booking)
        System.out.println("\nBooking a Single Room...");
        int current = inventory.getAvailability("Single");
        inventory.updateAvailability("Single", current - 1);

        // Display updated inventory
        inventory.displayInventory();
    }
}