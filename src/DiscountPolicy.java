// Interfejs opisujacy sposob naliczania znizki na rezerwacje.
public interface DiscountPolicy {
    double applyDiscount(Student student, double price);
}
