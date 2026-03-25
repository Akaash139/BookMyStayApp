import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 9: Error Handling & Validation
 *
 * @version 9.0
 */

public class BookMyStay {

    // ================= CUSTOM EXCEPTION =================
    static class InvalidBookingException extends Exception {
        public InvalidBookingException(String message) {
            super(message);
        }
    }

    // ================= INVENTORY =================
    static class RoomInventory {
        private Map<String, Integer> inventory = new HashMap<>();

        public RoomInventory() {
            inventory.put("Single", 2);
            inventory.put("Double", 1);
            inventory.put("Suite", 0);
        }

        public int getAvailability(String type) {
            return inventory.getOrDefault(type, -1);
        }

        public void decrease(String type) {
            inventory.put(type, inventory.get(type) - 1);
        }
    }

    // ================= VALIDATOR =================
    static class BookingValidator {

        public void validate(String roomType, RoomInventory inventory)
                throws InvalidBookingException {

            // Check valid room type
            if (inventory.getAvailability(roomType) == -1) {
                throw new InvalidBookingException("Invalid room type: " + roomType);
            }

            // Check availability
            if (inventory.getAvailability(roomType) <= 0) {
                throw new InvalidBookingException("No rooms available for: " + roomType);
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Booking Validation System ");
        System.out.println("=======================================");

        RoomInventory inventory = new RoomInventory();
        BookingValidator validator = new BookingValidator();

        // Test inputs
        String[] requests = {"Single", "Suite", "Luxury"}; // valid, no stock, invalid

        for (String roomType : requests) {

            System.out.println("\nRequesting: " + roomType);

            try {
                // Validate before booking
                validator.validate(roomType, inventory);

                // If valid → process booking
                inventory.decrease(roomType);

                System.out.println("Booking successful for " + roomType);

            } catch (InvalidBookingException e) {
                // Handle error (gracefully)
                System.out.println("Booking FAILED: " + e.getMessage());
            }
        }

        System.out.println("\nSystem running safely after errors.");
    }
}