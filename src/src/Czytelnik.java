public class Czytelnik {
  private String imie;
  private String nazwisko;
  private int numerKarty;
  private int liczbaWypozyczonych;
  public Czytelnik(String imie, String nazwisko, int numerKarty) {
    this.imie = imie;
    this.nazwisko = nazwisko;
    this.numerKarty = numerKarty;
    this.liczbaWypozyczonych = 0;
  }
  public void wypiszDane() {
    System.out.println("Czytelnik: " + imie + "" + nazwisko + " , nr karty: " + numerKarty + " , wypożyczone: " + liczbaWypozyczonych);
  }
  public void zwiekszLiczbeWypozyczonych() {
    this.liczbaWypozyczonych++;
  }
  public void zmniejszLiczbeWypozyczonych() {
    if (liczbaWypozyczonych > 0) this.liczbaWypozyczonych--;
  }
}
