# MediaLab – system rezerwacji sprzętu

Konsolowy program w Javie do obsługi wypożyczeń sprzętu (laptopy i kamery) przez studentów.
Po uruchomieniu tworzą się przykładowe dane, a użytkownik wybiera opcje z menu.

## Jak uruchomić

W folderze z plikami:

```
javac *.java
java Main
```

## Opis klas

- **Main** – uruchamia program, przygotowuje przykładowe dane, wyświetla menu i czyta wybór
  użytkownika z konsoli. Sama logika rezerwacji jest w `ReservationService`, tutaj jest tylko
  obsługa wejścia/wyjścia.
- **Student** – reprezentuje studenta: id, imię i nazwisko, grupa, punkty lojalnościowe.
  Ma metodę `addLoyaltyPoints`, która dolicza punkty po zwrocie sprzętu.
- **Equipment** – klasa abstrakcyjna, baza dla całego sprzętu. Trzyma id, nazwę, cenę bazową
  i informację, czy sprzęt jest dostępny. Wymusza na klasach potomnych metody
  `calculateDailyPrice()` i `getDetails()`.
- **LaptopSet** – konkretny typ sprzętu (laptop). Liczy cenę dzienną: cena bazowa, +5 PLN za
  stację dokującą, +25 PLN przy co najmniej 32 GB RAM.
- **CameraKit** – konkretny typ sprzętu (kamera). Cena dzienna: cena bazowa + 10 PLN za każdy
  obiektyw, +15 PLN za statyw.
- **Reservation** – pojedyncza rezerwacja. Przechowuje obiekty `Student` i `Equipment`
  (a nie tylko ich id), liczbę dni i status. Liczy całkowity koszt z uwzględnieniem zniżki.
- **ReservationService** – główna logika: tworzenie rezerwacji, zwrot sprzętu i raporty.
  Tu są wszystkie kolekcje (`ArrayList`) ze studentami, sprzętem i rezerwacjami oraz
  sprawdzanie poprawności danych.
- **LoyaltyDiscountPolicy** – osobna klasa licząca zniżkę: student z co najmniej 100 punktami
  dostaje 10% taniej.

## Interfejsy

- **Displayable** – metoda `getDisplayText()`. Implementują go klasy, które trzeba ładnie
  wypisać w konsoli (`Student`, `Equipment`, `Reservation`). Dzięki temu w pętli można
  po prostu wywołać `getDisplayText()` niezależnie od tego, jaki to obiekt.
- **DiscountPolicy** – metoda `applyDiscount(student, price)`. Oddziela sposób naliczania
  zniżki od reszty programu. Gdyby kiedyś trzeba było zmienić zasady zniżek, wystarczy
  napisać nową klasę implementującą ten interfejs.

## Gdzie działa polimorfizm

Najważniejsze miejsce to liczenie ceny. Lista sprzętu to `List<Equipment>`, ale trzyma
zarówno obiekty `LaptopSet`, jak i `CameraKit`. Kiedy wywołuję `equipment.calculateDailyPrice()`,
Java sama wybiera właściwą wersję metody zależnie od typu obiektu – laptop liczy cenę inaczej
niż kamera, a kod w `Reservation` i przy wyświetlaniu listy jest taki sam dla obu.

To samo dotyczy `getDisplayText()` z interfejsu `Displayable` – w pętli wypisuję studentów,
sprzęt i rezerwacje tym samym wywołaniem, choć każda klasa zwraca inny tekst.

## Przykładowy zapis konsoli

```
=== MediaLab - system rezerwacji sprzetu ===
1. Wyswietl liste studentow
2. Wyswietl liste sprzetu
3. Utworz rezerwacje
4. Zwroc sprzet
5. Pokaz aktywne rezerwacje
6. Pokaz raport
0. Zakoncz
Wybor: 2
--- Lista sprzetu ---
E001 | Lenovo ThinkPad Lab | Laptop, 32GB RAM, ze stacja dokujaca | 120.00 PLN/dzien | dostepny
E002 | Dell XPS Demo | Laptop, 16GB RAM, bez stacji dokujacej | 100.00 PLN/dzien | dostepny
E003 | Sony Content Kit | Kamera, 3 obiektyw(y), ze statywem | 115.00 PLN/dzien | dostepny
E004 | Canon Interview Kit | Kamera, 1 obiektyw(y), bez statywu | 80.00 PLN/dzien | dostepny

Wybor: 3
Podaj id studenta: S001
Podaj id sprzetu: E003
Podaj liczbe dni: 3
Utworzono rezerwacje R001.
Sprzet: Sony Content Kit
Koszt: 310.50 PLN
Status: ACTIVE

Wybor: 3
Podaj id studenta: S002
Podaj id sprzetu: E003
Podaj liczbe dni: 2
Blad: sprzet E003 nie jest dostepny.

Wybor: 4
Podaj id rezerwacji: R001
Zwrocono sprzet. Student otrzymal 31 punktow lojalnosciowych.

Wybor: 6
--- Aktywne rezerwacje ---
(brak)
--- Zakonczone rezerwacje ---
R001 | student: Anna Kowalska | sprzet: Sony Content Kit | dni: 3 | status: RETURNED | koszt: 310.50 PLN
Laczny przychod z zakonczonych rezerwacji: 310.50 PLN
Student z najwieksza liczba punktow: Anna Kowalska (151 pkt)

Wybor: 0
Do widzenia!
```

Anna Kowalska ma 120 punktów, więc dostała 10% zniżki: 115 PLN/dzień × 3 dni = 345 PLN,
po zniżce 310.50 PLN. Za to dostała 31 punktów (1 punkt za każde pełne 10 PLN), czyli ma teraz 151.
```
