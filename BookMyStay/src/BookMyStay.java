import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 11: Concurrent Booking Simulation (Thread Safety)
 *
 * @version 11.0
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

    // ================= INVENTORY (SHARED RESOURCE) =================
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 2);
        }

        // SYNCHRONIZED METHOD (critical section)
        public synchronized boolean bookRoom(String guestName, String roomType) {

            int available = inventory.getOrDefault(roomType, 0);

            if (available > 0) {
                System.out.println(guestName + " is booking...");

                // simulate delay (race condition scenario)
                try { Thread.sleep(100); } catch (Exception e) {}

                inventory.put(roomType, available - 1);

                System.out.println("Booking SUCCESS for " + guestName +
                        " | Remaining: " + (available - 1));
                return true;
            } else {
                System.out.println("Booking FAILED for " + guestName + " (No rooms)");
                return false;
            }
        }
    }

    // ================= BOOKING TASK (THREAD) =================
    static class BookingTask implements Runnable {

        private RoomInventory inventory;
        private Reservation reservation;

        public BookingTask(RoomInventory inventory, Reservation reservation) {
            this.inventory = inventory;
            this.reservation = reservation;
        }

        @Override
        public void run() {
            inventory.bookRoom(reservation.guestName, reservation.roomType);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Concurrent Booking System ");
        System.out.println("=======================================");

        RoomInventory inventory = new RoomInventory();

        // Multiple users (threads)
        Thread t1 = new Thread(new BookingTask(inventory,
                new Reservation("Akaash", "Single")));

        Thread t2 = new Thread(new BookingTask(inventory,
                new Reservation("Ravi", "Single")));

        Thread t3 = new Thread(new BookingTask(inventory,
                new Reservation("Meena", "Single"))); // should fail

        // Start threads simultaneously
        t1.start();
        t2.start();
        t3.start();
    }
}