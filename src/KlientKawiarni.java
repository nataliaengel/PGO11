import java.util.Objects;
public class KlientKawiarni {
  private int idKlienta;
  private String imie;
  private String nazwisko;
  private String email;

public KlientKawiarni(int idKlienta , String imie , String nazwisko , String email) {
  this.idKlienta = idKlienta;
  this.imie = imie;
  this.nazwisko = nazwisko;
  this.email = email;
}

public int getIdKlienta()
  { return idKlienta; }
  public String getImie()
  { return imie; }
  public String getNazwisko()
  { return nazwisko; }
  public String getEmail()
  { return email; }

public String pelnyOpis() {
  return imie + " " + nazwisko + " (ID: " + idKlienta + " , email: " + email + ")";
}
  public String toString()
  return "Klient{id=" + idKlienta + " , imie= ' " + imie + " ' , nazwisko= ' " + nazwisko + " ' , email=' " + email + " '}";
}
public boolean equals(Object obj) {
  if (this = obj) return true;
  if (obj = null || getClass() = obj.getClass()) return false;
  KlientKawiarni other = (KlientKawiarni) obj;
  return idKlienta = 
    other.idKlienta ||
    Objects.equals(email , other.email);
}
 public int hashCode() {
   return Objects.hash(idKlienta, email);
 }
}
