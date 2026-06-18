import java.util.ArrayList;

import java.util.List;

public class ReservationService {

   private List<Equipment> equipmentList;

   private List<Student> students;

   private List<Reservation> reservations;

   private DiscountPolicy discountPolicy;

   private int reservationCounter = 1;

   public ReservationService(List<Equipment> equipmentList, List<Student> students) {

       this.equipmentList = equipmentList;

       this.students = students;

       this.reservations = new ArrayList<>();

       this.discountPolicy = new LoyaltyDiscountPolicy();

   }

   public Student findStudent(String id) {

       for (Student s : students) {

           if (s.getId().equals(id)) return s;

       }

       return null;

   }

   public Equipment findEquipment(String id) {

       for (Equipment e : equipmentList) {

           if (e.getId().equals(id)) return e;

       }

       return null;

   }

   public Reservation findReservation(String id) {

       for (Reservation r : reservations) {

           if (r.getId().equals(id)) return r;

       }

       return null;

   }

   public String createReservation(String studentId, String equipmentId, int days) {

       Student student = findStudent(studentId);

       if (student == null) return "Błąd: student " + studentId + " nie istnieje.";

       Equipment equipment = findEquipment(equipmentId);

       if (equipment == null) return "Błąd: sprzęt " + equipmentId + " nie istnieje.";

       if (!equipment.isAvailable()) return "Błąd: sprzęt " + equipmentId + " nie jest dostępny.";

       if (days < 1 || days > 14) return "Błąd: liczba dni musi być od 1 do 14.";

       String reservationId = String.format("R%03d", reservationCounter++);

       Reservation reservation = new Reservation(reservationId, student, equipment, days);

       equipment.setAvailable(false);

       reservations.add(reservation);

       double cost = reservation.calculateTotalCost(discountPolicy);

       return "Utworzono rezerwację " + reservationId + ".\n"

           + "Sprzęt: " + equipment.getName() + "\n"

           + "Koszt: " + String.format("%.2f", cost) + " PLN\n"

           + "Status: " + reservation.getStatus();

   }

   public String returnEquipment(String reservationId) {

       Reservation reservation = findReservation(reservationId);

       if (reservation == null) return "Błąd: rezerwacja " + reservationId + " nie istnieje.";

       if (reservation.getStatus() != ReservationStatus.ACTIVE)

           return "Błąd: rezerwacja " + reservationId + " nie jest aktywna.";

       reservation.setStatus(ReservationStatus.RETURNED);

       reservation.getEquipment().setAvailable(true);

       double cost = reservation.calculateTotalCost(discountPolicy);

       int points = (int)(cost / 10);

       reservation.getStudent().addLoyaltyPoints(points);

       return "Zwrócono sprzęt. Student otrzymał " + points + " punkty lojalnościowe.";

   }

   public void printActiveReservations() {

       System.out.println("=== Aktywne rezerwacje ===");

       boolean found = false;

       for (Reservation r : reservations) {

           if (r.getStatus() == ReservationStatus.ACTIVE) {

               System.out.println(r.getDisplayText());

               found = true;

           }

       }

       if (!found) System.out.println("Brak aktywnych rezerwacji.");

   }

   public void printReport() {

       System.out.println("=== Raport ===");

       System.out.println("-- Zakończone rezerwacje --");

       double totalRevenue = 0;

       for (Reservation r : reservations) {

           if (r.getStatus() == ReservationStatus.RETURNED) {

               double cost = r.calculateTotalCost(discountPolicy);

               System.out.println(r.getDisplayText() + " | Koszt: " + String.format("%.2f", cost) + " PLN");

               totalRevenue += cost;

           }

       }

       System.out.println("Łączny przychód: " + String.format("%.2f", totalRevenue) + " PLN");

       Student top = null;

       for (Student s : students) {

           if (top == null || s.getLoyaltyPoints() > top.getLoyaltyPoints()) top = s;

       }

       if (top != null) {

           System.out.println("Student z największą liczbą punktów: "

               + top.getFullName() + " (" + top.getLoyaltyPoints() + " pkt)");

       }

   }

   public List<Equipment> getEquipmentList() { return equipmentList; }

   public List<Student> getStudents() { return students; }

}
 
