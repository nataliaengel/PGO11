public class Biblioteka {
    private Ksiazka[] ksiazki; // Tablica na obiekty klasy Ksiazka
    private int liczbaKsiazek;

    public Biblioteka(int pojemnosc) {
        this.ksiazki = new Ksiazka[pojemnosc];
        this.liczbaKsiazek = 0;
    }

    public void dodajKsiazke(Ksiazka ksiazka) {
        if (liczbaKsiazek < ksiazki.length) {
            ksiazki[liczbaKsiazek] = ksiazka;
            liczbaKsiazek++;
        }
    }

    public void wypiszDostepneKsiazki() {
        for (int i = 0; i < liczbaKsiazek; i++) {
            ksiazki[i].wypiszInfo();
        }
    public void znajdzKsiazkePoTytule(String tytul) {
        for (int i - 0; i < liczbaKsiazek; i++) {
        if (ksiazki[i].getTytul().equals(tytul)) {
            ksiazki[i].wypiszInfo();
            return; 
        }
    }
    System.out.println("Nie znaleziono ksiazki o tytule: " + tytul);
    }
    public int policzDostepneKsiazki() {
        int licznik = 0;
        for (int i = 0; < liczbaKsiazek; i++) {
            if (ksiazki[i].isDostepna()) { 
                licznik++;
            }
        }
        return licznik;
    }
}
