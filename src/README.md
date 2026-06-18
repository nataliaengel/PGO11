# MediaLab – system rezerwacji sprzętu

Konsolowy program w Javie do obsługi wypożyczeń sprzętu (laptopy i kamery) przez studentów.
Projekt zaliczeniowy z programowania obiektowego.

## Uruchomienie

```
javac *.java
java Main
```

Po starcie tworzą się przykładowe dane, a użytkownik wybiera opcje z menu (lista studentów,
lista sprzętu, tworzenie rezerwacji, zwrot, aktywne rezerwacje, raport).

## Co pokazuje projekt

- **Dziedziczenie** – `LaptopSet` i `CameraKit` dziedziczą po abstrakcyjnej klasie `Equipment`.
- **Polimorfizm** – cena dzienna liczona przez `calculateDailyPrice()` zależnie od typu sprzętu.
- **Enkapsulacja** – wszystkie pola prywatne, dostęp przez metody.
- **Interfejsy** – `Displayable` (wyświetlanie) i `DiscountPolicy` (zniżki).
- **Kompozycja i kolekcje** – `Reservation` trzyma obiekty `Student` i `Equipment`,
  a `ReservationService` zarządza listami (`ArrayList`).

Szczegółowy opis klas, interfejsów i przykładowy zapis konsoli znajduje się w pliku
[`OPIS.md`](OPIS.md).
