import java.util.HashMap;

/**
 * ============================================================
 * MAIN CLASS - UseCase4RoomSearch
 * ============================================================
 *
 * Use Case 4: Room Search & Availability Check
 *
 * @version 4.0
 */

public class BookMyStay{

    /**
     * ABSTRACT ROOM CLASS
     */
    static abstract class Room {
        protected int beds;
        protected int size;
        protected double price;

        public Room(int beds, int size, double price) {
            this.beds = beds;
            this.size = size;
            this.price = price;
        }

        public void displayDetails() {
            System.out.println("Beds: " + beds);
            System.out.println("Size: " + size + " sq.ft");
            System.out.println("Price: ₹" + price);
        }
    }

    // Room Types
    static class SingleRoom extends Room {
        public SingleRoom() { super(1, 250, 1500); }
    }

    static class DoubleRoom extends Room {
        public DoubleRoom() { super(2, 400, 2500); }
    }

    static class SuiteRoom extends Room {
        public SuiteRoom() { super(3, 750, 5000); }
    }

    /**
     * INVENTORY (Same as UC3)
     */
    static class RoomInventory {

        private HashMap<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 5);
            inventory.put("Double", 0); // unavailable
            inventory.put("Suite", 2);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }
    }

    /**
     * SEARCH SERVICE (READ-ONLY)
     */
    static class SearchService {

        public void searchRooms(RoomInventory inventory) {

            System.out.println("\n=== Available Rooms ===");

            // Create room objects
            Room single = new SingleRoom();
            Room doubleRoom = new DoubleRoom();
            Room suite = new SuiteRoom();

            // Check availability (READ ONLY)
            if (inventory.getAvailability("Single") > 0) {
                System.out.println("\nSingle Room:");
                single.displayDetails();
                System.out.println("Available: " + inventory.getAvailability("Single"));
            }

            if (inventory.getAvailability("Double") > 0) {
                System.out.println("\nDouble Room:");
                doubleRoom.displayDetails();
                System.out.println("Available: " + inventory.getAvailability("Double"));
            }

            if (inventory.getAvailability("Suite") > 0) {
                System.out.println("\nSuite Room:");
                suite.displayDetails();
                System.out.println("Available: " + inventory.getAvailability("Suite"));
            }
        }
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Hotel Room Search System ");
        System.out.println("=======================================");

        // Initialize inventory
        RoomInventory inventory = new RoomInventory();

        // Search (READ ONLY)
        SearchService search = new SearchService();
        search.searchRooms(inventory);
    }
}