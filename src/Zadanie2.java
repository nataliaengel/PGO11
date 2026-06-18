public class Zadanie2 {
    public static void main(String[] args) {
        ServiceOrder order = new ServiceOrder("Firma Alfa", 10, 120.0);
        PriceCalculator calculator = new PriceCalculator();

        PriceStrategy standard = o -> o.hours() * o.hourRate();
        PriceStrategy discount = o -> o.hours() * o.hourRate() * 0.90;
        PriceStrategy weekend  = o -> o.hours() * o.hourRate() * 1.25;

        System.out.println("standard: " + calculator.calculate(order, standard));
        System.out.println("discount: " + calculator.calculate(order, discount));
        System.out.println("weekend:  " + calculator.calculate(order, weekend));
    }
}
