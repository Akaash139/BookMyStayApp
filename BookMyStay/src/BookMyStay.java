import java.util.*;

/**
 * ============================================================
 * MAIN CLASS - BookMyStay
 * ============================================================
 *
 * Use Case 8: Booking History & Reporting
 *
 * @version 8.0
 */

public class BookMyStay {

    // ================= RESERVATION =================
    static class Reservation {
        String guestName;
        String roomType;
        String roomId;

        public Reservation(String guestName, String roomType, String roomId) {
            this.guestName = guestName;
            this.roomType = roomType;
            this.roomId = roomId;
        }

        public void display() {
            System.out.println("Guest: " + guestName +
                    " | Room: " + roomType +
                    " | Room ID: " + roomId);
        }
    }

    // ================= BOOKING HISTORY =================
    static class BookingHistory {

        // List → maintains order
        private List<Reservation> history = new ArrayList<>();

        // Add confirmed booking
        public void addReservation(Reservation r) {
            history.add(r);
        }

        // Get all bookings
        public List<Reservation> getAll() {
            return history;
        }
    }

    // ================= REPORT SERVICE =================
    static class BookingReportService {

        public void generateReport(List<Reservation> history) {

            System.out.println("\n=== Booking Report ===");

            if (history.isEmpty()) {
                System.out.println("No bookings found.");
                return;
            }

            for (Reservation r : history) {
                r.display();
            }

            System.out.println("\nTotal Bookings: " + history.size());
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        System.out.println("=======================================");
        System.out.println(" Booking History & Reporting System ");
        System.out.println("=======================================");

        BookingHistory history = new BookingHistory();

        // Simulate confirmed bookings (from UC6)
        history.addReservation(new Reservation("Akaash", "Single", "S-101"));
        history.addReservation(new Reservation("Ravi", "Double", "D-201"));
        history.addReservation(new Reservation("Meena", "Suite", "SU-301"));

        // Generate report
        BookingReportService report = new BookingReportService();
        report.generateReport(history.getAll());

        System.out.println("\nNOTE: Report is read-only (no data modified).");
    }
}}