// Zestaw laptopowy - konkretny typ sprzetu.
public class LaptopSet extends Equipment {
    private int ramGb;
    private boolean hasDockingStation;

    public LaptopSet(String id, String name, double baseDailyPrice, int ramGb, boolean hasDockingStation) {
        super(id, name, baseDailyPrice);
        this.ramGb = ramGb;
        this.hasDockingStation = hasDockingStation;
    }

    // Cena dzienna laptopa: cena bazowa, +5 PLN za stacje dokujaca,
    // +25 PLN jesli ma co najmniej 32 GB RAM.
    @Override
    public double calculateDailyPrice() {
        double price = getBaseDailyPrice();
        if (hasDockingStation) {
            price += 5;
        }
        if (ramGb >= 32) {
            price += 25;
        }
        return price;
    }

    @Override
    public String getDetails() {
        String stacja = hasDockingStation ? "ze stacja dokujaca" : "bez stacji dokujacej";
        return "Laptop, " + ramGb + "GB RAM, " + stacja;
    }
}
