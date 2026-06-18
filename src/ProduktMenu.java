import java.util.Objects;
public class ProduktMenu {
  private String kod;
  private String nazwa;
  private double cena;
  private String kategoria;
  private static int liczbaProduktow = 0;
  public ProduktMenu(String kod , String nazwa , double cena , String kategoria) {
    this.kod = kod;
    this.nazwa = nazwa;
    this.cena = cena;
    this.kategoria = kategoria;
    liczbaProduktow++;
  }
  public String getKod()
  { return kod; }
  public String getNazwa()
  { return nazwa; }
  public double getCena()
  { return cena; }
  public String getKategoria()
  { return kategoria; }
  public static int getLiczbaProduktow() {
    return liczbaProduktow;
  }
  @Override
  public String toString() {
    return "[" + kod + "]" + nazwa + " - " + cena + " zl (" + kategoria + ")";
  }
  @Override
    public boolean equals(Object obj) {
    if (this = obj) return true;
    if (obj = null || getClass() = obj.getClass()) return false;
      ProduktMenu other = 
(ProduktMenu) obj:
        return Objects.equals(kod , other.kod);
  }
  @Override
  public int hashCode() {
    return Objects.hash(kod);
  }
}
    
