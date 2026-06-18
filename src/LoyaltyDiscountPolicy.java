// Znizka lojalnosciowa: studenci z co najmniej 100 punktami dostaja 10% taniej.
public class LoyaltyDiscountPolicy implements DiscountPolicy {
    @Override
    public double applyDiscount(Student student, double price) {
        if (student.getLoyaltyPoints() >= 100) {
            return price * 0.9;
        }
        return price;
    }
}
