// Klasa abstrakcyjna bazowa dla calego sprzetu.
// Kazdy typ sprzetu sam liczy swoja cene dzienna (polimorfizm).
public abstract class Equipment implements Displayable {
    private String id;
    private String name;
    private double baseDailyPrice;
    private boolean available;

    public Equipment(String id, String name, double baseDailyPrice) {
        this.id = id;
        this.name = name;
        this.baseDailyPrice = baseDailyPrice;
        this.available = true; // nowy sprzet jest od razu dostepny
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBaseDailyPrice() {
        return baseDailyPrice;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    // Te dwie metody musza nadpisac klasy potomne.
    public abstract double calculateDailyPrice();

    public abstract String getDetails();

    @Override
    public String getDisplayText() {
        String dostepnosc = available ? "dostepny" : "niedostepny";
        return id + " | " + name + " | " + getDetails()
                + " | " + String.format("%.2f", calculateDailyPrice()) + " PLN/dzien"
                + " | " + dostepnosc;
    }
}
