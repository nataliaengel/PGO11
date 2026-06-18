import java.util.ArrayList;

import java.util.List;

import java.util.Scanner;

public class Main {

   public static void main(String[] args) {

       List<Student> students = new ArrayList<>();

       students.add(new Student("S001", "Anna Kowalska", "12c", 120));

       students.add(new Student("S002", "Marek Nowak", "12c", 40));

       students.add(new Student("S003", "Julia Zielinska", "13a", 0));

       List<Equipment> equipment = new ArrayList<>();

       equipment.add(new LaptopSet("E001", "Lenovo ThinkPad Lab", 80, 32, true));

       equipment.add(new LaptopSet("E002", "Dell XPS Demo", 100, 16, false));

       equipment.add(new CameraKit("E003", "Sony Content Kit", 90, 3, true));

       equipment.add(new CameraKit("E004", "Canon Interview Kit", 70, 1, true));

       ReservationService service = new ReservationService(equipment, students);

       Scanner scanner = new Scanner(System.in);

       int choice = -1;

       while (choice != 0) {

           System.out.println("\n1. Wyświetl sprzęt");

           System.out.println("2. Utwórz rezerwację");

           System.out.println("3. Zwróć sprzęt");

           System.out.println("4. Pokaż aktywne rezerwacje");

           System.out.println("5. Pokaż raport");

           System.out.println("0. Zakończ");

           System.out.print("Wybór: ");

           try {

               choice = Integer.parseInt(scanner.nextLine().trim());

           } catch (NumberFormatException e) {

               System.out.println("Nieprawidłowy wybór.");

               continue;

           }

           switch (choice) {

               case 1:

                   System.out.println("=== Lista sprzętu ===");

                   for (Equipment eq : service.getEquipmentList()) {

                       System.out.println(eq.getDisplayText());

                   }

                   break;

               case 2:

                   System.out.print("Podaj id studenta: ");

                   String studentId = scanner.nextLine().trim();

                   System.out.print("Podaj id sprzętu: ");

                   String equipmentId = scanner.nextLine().trim();

                   System.out.print("Podaj liczbę dni: ");

                   try {

                       int days = Integer.parseInt(scanner.nextLine().trim());

                       System.out.println(service.createReservation(studentId, equipmentId, days));

                   } catch (NumberFormatException e) {

                       System.out.println("Błąd: nieprawidłowa liczba dni.");

                   }

                   break;

               case 3:

                   System.out.print("Podaj id rezerwacji: ");

                   String reservationId = scanner.nextLine().trim();

                   System.out.println(service.returnEquipment(reservationId));

                   break;

               case 4:

                   service.printActiveReservations();

                   break;

               case 5:

                   service.printReport();

                   break;

               case 0:

                   System.out.println("Do widzenia!");

                   break;

               default:

                   System.out.println("Nieprawidłowy wybór.");

           }

       }

       scanner.close();

   }

}
 
