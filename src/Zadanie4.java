public class Zadanie4 {
    public static void main(String[] args) {
        LibraryAccount account = new LibraryAccount("Jan Nowak", 3, 5);
        LibraryAccount.FineCalculator calculator = account.new FineCalculator();
        double fine = calculator.calculate();

        MessagePrinter printer = message -> System.out.println("[BIBLIOTEKA] " + message);
        printer.print("Czytelnik: " + account.getReaderName() + ", opłata: " + fine + " zł");
    }
}
