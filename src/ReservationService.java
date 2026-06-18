import java.util.ArrayList;
import java.util.List;

// Glowna logika programu: tworzenie i zwracanie rezerwacji oraz raporty.
// Tu trzymamy wszystkie kolekcje (studenci, sprzet, rezerwacje).
public class ReservationService {
    private List<Student> students;
    private List<Equipment> equipmentList;
    private List<Reservation> reservations;
    private DiscountPolicy discountPolicy;
    private int nextReservationNumber; // do generowania id R001, R002, ...

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

    // Tworzy rezerwacje, ale tylko gdy wszystkie dane sa poprawne.
    // W razie bledu rzuca wyjatek z opisem, ktory wypisuje Main.
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
        equipment.setAvailable(false); // sprzet jest teraz zajety
        reservations.add(reservation);
        return reservation;
    }

    // Zwraca sprzet po id rezerwacji i zwraca liczbe przyznanych punktow.
    public int returnEquipment(String reservationId) {
        Reservation reservation = findReservationById(reservationId);
        if (reservation == null) {
            throw new IllegalArgumentException("nie znaleziono rezerwacji o id " + reservationId + ".");
        }

        if (reservation.getStatus() != ReservationStatus.ACTIVE) {
            throw new IllegalArgumentException("rezerwacja " + reservationId + " nie jest aktywna.");
        }

        reservation.setStatus(ReservationStatus.RETURNED);
        reservation.getEquipment().setAvailable(true); // sprzet znowu dostepny

        double cost = reservation.calculateTotalCost(discountPolicy);
        int earnedPoints = (int) (cost / 10); // 1 punkt za kazde pelne 10 PLN
        reservation.getStudent().addLoyaltyPoints(earnedPoints);
        return earnedPoints;
    }

    // Liczy koszt rezerwacji z aktualna polityka znizek (uzywane przez Main i raport).
    public double calculateCost(Reservation reservation) {
        return reservation.calculateTotalCost(discountPolicy);
    }

    // Raport: aktywne i zakonczone rezerwacje, laczny przychod, najlepszy student.
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

    // --- metody pomocnicze (prywatne) ---

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
