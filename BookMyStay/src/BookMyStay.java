import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * @version 10.0
 */

public class BookMyStay {

    // ================= RESERVATION =================
    static class Reservation {
        String reservationId;
        String roomType;

        public Reservation(String reservationId, String roomType) {
            this.reservationId = reservationId;
            this.roomType = roomType;
        }
    }

    // ================= INVENTORY =================
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 1);
            inventory.put("Double", 1);
        }

        public void increase(String type) {
            inventory.put(type, inventory.get(type) + 1);
        }

        public void display() {
            System.out.println("\n=== Inventory ===");
            for (String key : inventory.keySet()) {
                System.out.println(key + " Rooms: " + inventory.get(key));
            }
        }
    }

    // ================= BOOKING HISTORY =================
    static class BookingHistory {
        private Map<String, Reservation> bookings = new HashMap<>();

        public void add(Reservation r) {
            bookings.put(r.reservationId, r);
        }

        public Reservation get(String id) {
            return bookings.get(id);
        }

        public void remove(String id) {
            bookings.remove(id);
        }
    }

    // ================= CANCELLATION SERVICE =================
    static class CancellationService {

        // Stack for rollback (LIFO)
        private Stack<String> rollbackStack = new Stack<>();

        public void cancel(String reservationId,
                           BookingHistory history,
                           RoomInventory inventory) {

            System.out.println("\nCancelling: " + reservationId);

            // Validate reservation
            Reservation r = history.get(reservationId);

            if (r == null) {
                System.out.println("Cancellation FAILED: Reservation not found");
                return;
            }

            // Push to rollback stack
            rollbackStack.push(reservationId);

            // Restore inventory
            inventory.increase(r.roomType);

            // Remove booking
            history.remove(reservationId);

            System.out.println("Cancellation SUCCESS for " + reservationId);
        }

        public void showRollbackStack() {
            System.out.println("\nRollback Stack (LIFO): " + rollbackStack);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Cancellation & Rollback System ");
        System.out.println("=======================================");

        RoomInventory inventory = new RoomInventory();
        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings
        history.add(new Reservation("S-101", "Single"));
        history.add(new Reservation("D-201", "Double"));

        CancellationService service = new CancellationService();

        // Cancel booking
        service.cancel("S-101", history, inventory);

        // Try invalid cancel
        service.cancel("S-999", history, inventory);

        // Show rollback stack
        service.showRollbackStack();

        // Show updated inventory
        inventory.display();
    }
}