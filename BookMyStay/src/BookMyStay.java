import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * @version 7.0
 */

public class BookMyStay {

    // ================= SERVICE CLASS =================
    static class AddOnService {
        String name;
        double price;

        public AddOnService(String name, double price) {
            this.name = name;
            this.price = price;
        }
    }

    // ================= SERVICE MANAGER =================
    static class AddOnServiceManager {

        // Map: ReservationID → List of Services
        private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

        // Add service to reservation
        public void addService(String reservationId, AddOnService service) {

            serviceMap.putIfAbsent(reservationId, new ArrayList<>());
            serviceMap.get(reservationId).add(service);

            System.out.println("Added " + service.name + " to " + reservationId);
        }

        // Display services
        public void displayServices(String reservationId) {

            System.out.println("\nServices for Reservation: " + reservationId);

            List<AddOnService> services = serviceMap.get(reservationId);

            if (services == null) {
                System.out.println("No services selected.");
                return;
            }

            double total = 0;

            for (AddOnService s : services) {
                System.out.println("- " + s.name + " : ₹" + s.price);
                total += s.price;
            }

            System.out.println("Total Add-On Cost: ₹" + total);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Add-On Service System ");
        System.out.println("=======================================");

        // Assume reservation already exists (from UC6)
        String reservationId = "Single-12345";

        AddOnServiceManager manager = new AddOnServiceManager();

        // Guest selects services
        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("WiFi", 200));
        manager.addService(reservationId, new AddOnService("Airport Pickup", 1000));

        // Display services + total cost
        manager.displayServices(reservationId);

        System.out.println("\nNOTE: Booking & Inventory unchanged.");
    }
}