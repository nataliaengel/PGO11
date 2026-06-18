public class PriceCalculator {
    public double calculate(ServiceOrder order, PriceStrategy strategy) {
        return strategy.calculate(order);
    }
}
