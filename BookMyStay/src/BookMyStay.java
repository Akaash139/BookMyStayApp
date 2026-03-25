import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * @version 6.0
 */

public class BookMyStay {

    // ================= RESERVATION =================
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }
    }

    // ================= INVENTORY =================
    static class RoomInventory {
        private HashMap<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 2);
            inventory.put("Double", 1);
            inventory.put("Suite", 1);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, 0);
        }

        public void decrease(String type) {
            inventory.put(type, inventory.get(type) - 1);
        }

        public void display() {
            System.out.println("\n=== Updated Inventory ===");
            for (String key : inventory.keySet()) {
                System.out.println(key + " Rooms: " + inventory.get(key));
            }
        }
    }

    // ================= BOOKING QUEUE =================
    static class BookingQueue {
        Queue<Reservation> queue = new LinkedList<>();

        public void add(Reservation r) {
            queue.add(r);
        }

        public Reservation getNext() {
            return queue.poll(); // FIFO
        }

        public boolean isEmpty() {
            return queue.isEmpty();
        }
    }

    // ================= BOOKING SERVICE =================
    static class BookingService {

        // Store allocated room IDs (prevent duplicates)
        private Set<String> allocatedRooms = new HashSet<>();

        // Map room type → allocated IDs
        private HashMap<String, Set<String>> roomMap = new HashMap<>();

        public void processBookings(BookingQueue queue, RoomInventory inventory) {

            while (!queue.isEmpty()) {

                Reservation r = queue.getNext();

                System.out.println("\nProcessing: " + r.guestName);

                // Check availability
                if (inventory.getAvailability(r.roomType) > 0) {

                    // Generate unique room ID
                    String roomId = r.roomType + "-" + UUID.randomUUID().toString().substring(0, 5);

                    // Ensure uniqueness
                    if (!allocatedRooms.contains(roomId)) {

                        allocatedRooms.add(roomId);

                        // Map room type → room IDs
                        roomMap.putIfAbsent(r.roomType, new HashSet<>());
                        roomMap.get(r.roomType).add(roomId);

                        // Decrease inventory
                        inventory.decrease(r.roomType);

                        // Confirm booking
                        System.out.println("Booking CONFIRMED");
                        System.out.println("Guest: " + r.guestName);
                        System.out.println("Room Type: " + r.roomType);
                        System.out.println("Room ID: " + roomId);
                    }

                } else {
                    System.out.println("Booking FAILED (No rooms available)");
                }
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Booking Allocation System ");
        System.out.println("=======================================");

        // Create inventory
        RoomInventory inventory = new RoomInventory();

        // Create queue
        BookingQueue queue = new BookingQueue();

        // Add requests (FIFO)
        queue.add(new Reservation("Akaash", "Single"));
        queue.add(new Reservation("Ravi", "Single"));
        queue.add(new Reservation("Meena", "Single")); // should fail
        queue.add(new Reservation("John", "Suite"));

        // Process bookings
        BookingService service = new BookingService();
        service.processBookings(queue, inventory);

        // Show final inventory
        inventory.display();
    }
}}