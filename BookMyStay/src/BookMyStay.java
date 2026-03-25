import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 5: Booking Request (First-Come-First-Served)
 *
 * @version 5.0
 */

public class BookMyStay {

    /**
     * Reservation Class (represents a booking request)
     */
    static class Reservation {
        String guestName;
        String roomType;

        public Reservation(String guestName, String roomType) {
            this.guestName = guestName;
            this.roomType = roomType;
        }

        public void display() {
            System.out.println("Guest: " + guestName + " | Room: " + roomType);
        }
    }

    /**
     * Booking Queue (FIFO)
     */
    static class BookingQueue {

        private Queue<Reservation> queue = new LinkedList<>();

        // Add request
        public void addRequest(Reservation r) {
            queue.add(r);
            System.out.println("Request added for " + r.guestName);
        }

        // Display queue
        public void displayQueue() {
            System.out.println("\n=== Booking Queue (FIFO) ===");

            for (Reservation r : queue) {
                r.display();
            }
        }
    }

    /**
     * MAIN METHOD
     */
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Booking Request Queue System ");
        System.out.println("=======================================");

        BookingQueue bookingQueue = new BookingQueue();

        // Add booking requests (arrival order)
        bookingQueue.addRequest(new Reservation("Akaash", "Single"));
        bookingQueue.addRequest(new Reservation("Ravi", "Double"));
        bookingQueue.addRequest(new Reservation("Meena", "Suite"));

        // Display queue
        bookingQueue.displayQueue();

        System.out.println("\nNOTE: No booking processed yet (only queue).");
    }
}