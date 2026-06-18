import java.util.ArrayList;
import java.util.List;

public class Order {
    private final String orderNumber;
    private final String customerName;
    private final List<OrderItem> items = new ArrayList<>();

    public Order(String orderNumber, String customerName) {
        this.orderNumber = orderNumber;
        this.customerName = customerName;
    }

    public void addItem(OrderItem item) { items.add(item); }

    public double total() {
        return items.stream().mapToDouble(OrderItem::total).sum();
    }

    public String getOrderNumber() { return orderNumber; }
    public String getCustomerName() { return customerName; }

    public static class OrderItem {
        private final String productName;
        private final double unitPrice;
        private final int quantity;

        public OrderItem(String productName, double unitPrice, int quantity) {
            this.productName = productName;
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public double total() { return unitPrice * quantity; }
    }
}
