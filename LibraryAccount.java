public class LibraryAccount {
    private final String readerName;
    private final int borrowedBooks;
    private final int lateDays;

    public LibraryAccount(String readerName, int borrowedBooks, int lateDays) {
        this.readerName = readerName;
        this.borrowedBooks = borrowedBooks;
        this.lateDays = lateDays;
    }

    public String getReaderName() { return readerName; }

    public class FineCalculator {
        public double calculate() {
            return borrowedBooks * lateDays * 1.50;
        }
    }
}
