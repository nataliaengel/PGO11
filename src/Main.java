import java.util.Scanner;

// Punkt startowy programu: przygotowuje dane, pokazuje menu i czyta wybor uzytkownika.
// Cala logika biznesowa jest w ReservationService - tu tylko obsluga konsoli.
public class Main {
    public static void main(String[] args) {
        ReservationService service = new ReservationService(new LoyaltyDiscountPolicy());
        seedData(service);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    showStudents(service);
                    break;
                case "2":
                    showEquipment(service);
                    break;
                case "3":
                    createReservation(service, scanner);
                    break;
                case "4":
                    returnEquipment(service, scanner);
                    break;
                case "5":
                    showActiveReservations(service);
                    break;
                case "6":
                    service.printReport();
                    break;
                case "0":
                    running = false;
                    System.out.println("Do widzenia!");
                    break;
                default:
                    System.out.println("Nieznana opcja, sprobuj ponownie.");
            }
        }
    }

    // Przykladowe dane startowe (bez bazy danych i plikow).
    private static void seedData(ReservationService service) {
        service.addStudent(new Student("S001", "Anna Kowalska", "12c", 120));
        service.addStudent(new Student("S002", "Marek Nowak", "12c", 40));
        service.addStudent(new Student("S003", "Julia Zielinska", "13a", 0));

        service.addEquipment(new LaptopSet("E001", "Lenovo ThinkPad Lab", 90, 32, true));
        service.addEquipment(new LaptopSet("E002", "Dell XPS Demo", 100, 16, false));
        service.addEquipment(new CameraKit("E003", "Sony Content Kit", 70, 3, true));
        service.addEquipment(new CameraKit("E004", "Canon Interview Kit", 70, 1, false));
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("=== MediaLab - system rezerwacji sprzetu ===");
        System.out.println("1. Wyswietl liste studentow");
        System.out.println("2. Wyswietl liste sprzetu");
        System.out.println("3. Utworz rezerwacje");
        System.out.println("4. Zwroc sprzet");
        System.out.println("5. Pokaz aktywne rezerwacje");
        System.out.println("6. Pokaz raport");
        System.out.println("0. Zakoncz");
        System.out.print("Wybor: ");
    }

    private static void showStudents(ReservationService service) {
        System.out.println("--- Lista studentow ---");
        for (Student s : service.getStudents()) {
            System.out.println(s.getDisplayText());
        }
    }

    private static void showEquipment(ReservationService service) {
        System.out.println("--- Lista sprzetu ---");
        for (Equipment e : service.getEquipmentList()) {
            System.out.println(e.getDisplayText());
        }
    }

    private static void createReservation(ReservationService service, Scanner scanner) {
        System.out.print("Podaj id studenta: ");
        String studentId = scanner.nextLine().trim();
        System.out.print("Podaj id sprzetu: ");
        String equipmentId = scanner.nextLine().trim();
        System.out.print("Podaj liczbe dni: ");
        String daysText = scanner.nextLine().trim();

        int days;
        try {
            days = Integer.parseInt(daysText);
        } catch (NumberFormatException e) {
            System.out.println("Blad: liczba dni musi byc liczba calkowita.");
            return;
        }

        try {
            Reservation reservation = service.createReservation(studentId, equipmentId, days);
            double cost = service.calculateCost(reservation);
            System.out.println("Utworzono rezerwacje " + reservation.getId() + ".");
            System.out.println("Sprzet: " + reservation.getEquipment().getName());
            System.out.println("Koszt: " + String.format("%.2f", cost) + " PLN");
            System.out.println("Status: " + reservation.getStatus());
        } catch (IllegalArgumentException e) {
            System.out.println("Blad: " + e.getMessage());
        }
    }

    private static void returnEquipment(ReservationService service, Scanner scanner) {
        System.out.print("Podaj id rezerwacji: ");
        String reservationId = scanner.nextLine().trim();
        try {
            int points = service.returnEquipment(reservationId);
            System.out.println("Zwrocono sprzet. Student otrzymal " + points + " punktow lojalnosciowych.");
        } catch (IllegalArgumentException e) {
            System.out.println("Blad: " + e.getMessage());
        }
    }

    private static void showActiveReservations(ReservationService service) {
        System.out.println("--- Aktywne rezerwacje ---");
        boolean any = false;
        for (Reservation r : service.getReservations()) {
            if (r.getStatus() == ReservationStatus.ACTIVE) {
                System.out.println(r.getDisplayText());
                any = true;
            }
        }
        if (!any) {
            System.out.println("(brak aktywnych rezerwacji)");
        }
    }
}
