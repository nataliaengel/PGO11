import java.util.ArrayList;
import java.util.List;


public class ReservationService {
    private List<Student> students;
    private List<Equipment> equipmentList;
    private List<Reservation> reservations;
    private DiscountPolicy discountPolicy;
    private int nextReservationNumber;

    public ReservationService(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.students = new ArrayList<>();
        this.equipmentList = new ArrayList<>();
        this.reservations = new ArrayList<>();
        this.nextReservationNumber = 1;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Reservation createReservation(String studentId, String equipmentId, int days) {
        Student student = findStudentById(studentId);
        if (student == null) {
            throw new IllegalArgumentException("nie znaleziono studenta o id " + studentId + ".");
        }

        Equipment equipment = findEquipmentById(equipmentId);
        if (equipment == null) {
            throw new IllegalArgumentException("nie znaleziono sprzetu o id " + equipmentId + ".");
        }

        if (!equipment.isAvailable()) {
            throw new IllegalArgumentException("sprzet " + equipmentId + " nie jest dostepny.");
        }

        if (days < 1 || days > 14) {
            throw new IllegalArgumentException("liczba dni musi byc od 1 do 14.");
        }

        String reservationId = "R" + String.format("%03d", nextReservationNumber);
        nextReservationNumber++;

        Reservation reservation = new Reservation(reservationId, student, equipment, days);
        equipment.setAvailable(false); 
        reservations.add(reservation);
        return reservation;
    }

  
    public int returnEquipment(String reservationId) {
        Reservation reservation = findReservationById(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("nie znaleziono rezerwacji o id " + reservationId + ".");
        }

        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new IllegalArgumentException("rezerwacja " + reservationId + " nie jest aktywna.");
        }

        reservation.setStatus(ReservationStatus.RETURNED);
        reservation.getEquipment().setAvailable(true); 

        double cost = reservation.calculateTotalCost(discountPolicy);
        int earnedPoints = (int) (cost / 10); 
        reservation.getStudent().addLoyaltyPoints(earnedPoints);
        return earnedPoints;
    }

    public double calculateCost(Reservation reservation) {
        return reservation.calculateTotalCost(discountPolicy);
    }

   
    public void printReport() {
        System.out.println("--- Aktywne rezerwacje ---");
        boolean anyActive = false;
        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.ACTIVE) {
                System.out.println(r.getDisplayText());
                anyActive = true;
            }
        }
        if (!anyActive) {
            System.out.println("(brak)");
        }

        System.out.println("--- Zakonczone rezerwacje ---");
        double totalRevenue = 0;
        boolean anyReturned = false;
        for (Reservation r : reservations) {
            if (r.getStatus() == ReservationStatus.RETURNED) {
                double cost = calculateCost(r);
                System.out.println(r.getDisplayText() + " | koszt: " + String.format("%.2f", cost) + " PLN");
                totalRevenue += cost;
                anyReturned = true;
            }
        }
        if (!anyReturned) {
            System.out.println("(brak)");
        }

        System.out.println("Laczny przychod z zakonczonych rezerwacji: "
                + String.format("%.2f", totalRevenue) + " PLN");

        Student top = findStudentWithMostPoints();
        if (top != null) {
            System.out.println("Student z najwieksza liczba punktow: "
                    + top.getFullName() + " (" + top.getLoyaltyPoints() + " pkt)");
        }
    }



    private Student findStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    private Equipment findEquipmentById(String id) {
        for (Equipment e : equipmentList) {
            if (e.getId().equals(id)) {
                return e;
            }
        }
        return null;
    }

    private Reservation findReservationById(String id) {
        for (Reservation r : reservations) {
            if (r.getId().equals(id)) {
                return r;
            }
        }
        return null;
    }

    private Student findStudentWithMostPoints() {
        Student top = null;
        for (Student s : students) {
            if (top == null || s.getLoyaltyPoints() > top.getLoyaltyPoints()) {
                top = s;
            }
        }
        return top;
    }
}
