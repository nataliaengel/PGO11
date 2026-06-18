// Zestaw kamerowy - konkretny typ sprzetu.
public class CameraKit extends Equipment {
    private int lensCount;
    private boolean hasTripod;

    public CameraKit(String id, String name, double baseDailyPrice, int lensCount, boolean hasTripod) {
        super(id, name, baseDailyPrice);
        this.lensCount = lensCount;
        this.hasTripod = hasTripod;
    }

    // Cena dzienna kamery: cena bazowa + 10 PLN za kazdy obiektyw,
    // +15 PLN jesli zestaw ma statyw.
    @Override
    public double calculateDailyPrice() {
        double price = getBaseDailyPrice();
        price += lensCount * 10;
        if (hasTripod) {
            price += 15;
        }
        return price;
    }

    @Override
    public String getDetails() {
        String statyw = hasTripod ? "ze statywem" : "bez statywu";
        return "Kamera, " + lensCount + " obiektyw(y), " + statyw;
    }
}
