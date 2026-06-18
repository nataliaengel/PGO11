@FunctionalInterface
public interface PriceStrategy {
    double calculate(ServiceOrder order);
}
