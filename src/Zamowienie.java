import java.util.ArrayList;
public class Zamowienie {
  private int numerZamowienia;
  private KlientKawiarni klient;
  private ArrayList<ProduktMenu>produkty;
  private boolean oplacone;

private static int kolejnyNumer = 1;

public Zamowienie(KlientKawiarni klient) {
  this.numerZamowienia = kolejnyNumer++;
  this.klient = klient;
  this.produkty = new ArrayList<>();
  this.oplacone = false;
}

public void dodajProdukt(ProduktMenu produkt) {
  produkty.add(produkt);
}

public double policzWartosc() {
  double suma = 0;
  for (ProduktMenu p : produkty) {
   suma += p.getCena();
    }
  return suma;
}

public int policzLiczbeProduktow()
 {
    return produkty.size();
  }

public void oznaczJakoOplacone() {
  this.oplacone = true;
 }

public static int pobierzKolejnyNumer() {
  return kolejnyNumer;
 }

public String toString() {
  return "Zamowienie{nr=" + numerZamowienia + ", klient=" + klient.getImie() + " " + klient.getNazwisko() + " , produkty=" + produkty.size() + " , wartosc=" + policzWartosc() + " , oplacone=" + oplacone + "}";
 }
}
