Opis projektu - MediaLab System Rezerwacji

Klasy

Main - uruchamia program, tworzy dane startowe, obsługuje menu konsolowe

Student - reprezentuje studenta: id, imie, grupa, punkty lojalnosciowe

Equipment - abstrakcyjna klasa bazowa dla sprzetu, implementuje Displayable

LaptopSet - zestaw laptopowy, dziedziczy po Equipment, liczy cene wg RAM i stacji dokujacej

CameraKit - zestaw kamerowy, dziedziczy po Equipment, liczy cene wg liczby obiektywow i statywu

Reservation - laczy studenta ze sprzetem, implementuje Displayable

ReservationService - glowna logika biznesowa: tworzenie i zwrot rezerwacji, raporty

Interfejsy

Displayable - implementowany przez Equipment i Reservation, metoda getDisplayText()

DiscountPolicy - implementowany przez LoyaltyDiscountPolicy, metoda applyDiscount()

Polimorfizm

Przy tworzeniu rezerwacji wywolywana jest metoda calculateDailyPrice().
 
