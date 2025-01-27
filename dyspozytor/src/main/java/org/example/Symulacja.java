import java.util.*;
import java.time.LocalDateTime;

public class Symulacja {
    private static final double PREDKOSC_JEDNOSTKI = 10.0;
    private final Dyspozytor dyspozytor;
    private final Random random;
    private int aktualnaTura;
    private final double maksymalnyObszarX;
    private final double maksymalnyObszarY;
    private final Map<Integer, List<Wypadek>> zaplanowaneWypadki;

    public Symulacja(double maksymalnyObszarX, double maksymalnyObszarY) {
        this.maksymalnyObszarX = maksymalnyObszarX;
        this.maksymalnyObszarY = maksymalnyObszarY;
        this.random = new Random();
        this.aktualnaTura = 0;
        this.zaplanowaneWypadki = new HashMap<>();
        this.dyspozytor = new Dyspozytor();
        inicjalizujJednostki();
        inicjalizujSzpitale();
    }
//Dodaje szpitale do symulacji
    private void inicjalizujSzpitale() {
        Szpital szpital1 = new Szpital("Szpital Miejski", 20, new Lokalizacja(50.0, 50.0));
        Szpital szpital2 = new Szpital("Szpital Wojewódzki", 30, new Lokalizacja(30.0, 70.0));
        dyspozytor.dodajSzpital(szpital1);
        dyspozytor.dodajSzpital(szpital2);
    }
//Dodaje wszystkie jednostki do symulacji
    private void inicjalizujJednostki() {
        // Inicjalizacja jednostek Policji
        dyspozytor.dodajJednostke(new Policja("POL-1", new Lokalizacja(0, 0)));
        dyspozytor.dodajJednostke(new Policja("POL-2", new Lokalizacja(3, 3)));

        // Inicjalizacja jednostek Pogotowia
        dyspozytor.dodajJednostke(new Pogotowie("AMB-1", new Lokalizacja(0, 5)));
        dyspozytor.dodajJednostke(new Pogotowie("AMB-2", new Lokalizacja(3, 3)));
        dyspozytor.dodajJednostke(new Pogotowie("AMB-3", new Lokalizacja(3, 7)));
        dyspozytor.dodajJednostke(new Pogotowie("AMB-4", new Lokalizacja(2, 10)));

        // Inicjalizacja jednostek Straży
        dyspozytor.dodajJednostke(new Straz("STR-1", new Lokalizacja(10, 10)));
        dyspozytor.dodajJednostke(new Straz("STR-2", new Lokalizacja(12, 10)));
        dyspozytor.dodajJednostke(new Straz("STR-3", new Lokalizacja(11, 12)));
    }

    private Lokalizacja generujLosowaLokalizacje() {
        return new Lokalizacja(
                random.nextDouble() * maksymalnyObszarX,
                random.nextDouble() * maksymalnyObszarY
        );
    }

    private void generujLosowyWypadek() {
        if (random.nextDouble() < 0.5) {
            Lokalizacja lokalizacja = generujLosowaLokalizacje();
            TypWypadku[] typy = TypWypadku.values();
            TypWypadku typ = typy[random.nextInt(typy.length)];
            int liczbaOsob = 1 + random.nextInt(5);

            Wypadek wypadek = new Wypadek(lokalizacja, typ, liczbaOsob);
            dyspozytor.dodajWypadek(wypadek);
            System.out.println("Wygenerowano nowy wypadek: " + wypadek);
        }
    }

    private void przesunJednostkeWKierunkuCelu(JednostkaRatownicza jednostka, Lokalizacja cel) {
        Lokalizacja aktualna = jednostka.getAktualnaLokalizacja();
        double odleglosc = aktualna.obliczOdleglosc(cel);

        if (odleglosc <= PREDKOSC_JEDNOSTKI) {
            jednostka.zmienLokalizacje(cel);
            if (jednostka.getStatus() == StatusJednostki.ZADYSPONOWANY) {
                jednostka.zmienStatus(StatusJednostki.ZAJETY);
            }
        } else {
            double kierunek = aktualna.obliczKierunek(cel);
            Lokalizacja nowa = aktualna.przesun(PREDKOSC_JEDNOSTKI, kierunek);
            jednostka.zmienLokalizacje(nowa);
        }
    }

    private void aktualizujPozycjeJednostek() {
        for (JednostkaRatownicza jednostka : dyspozytor.getWszystkieJednostki()) {
            if (jednostka instanceof Pogotowie) {
                Pogotowie pogotowie = (Pogotowie) jednostka;

                switch (pogotowie.getStatus()) {
                    case ZADYSPONOWANY:
                        przesunJednostkeWKierunkuCelu(pogotowie,
                                pogotowie.getPrzypisaneZgloszenie().getLokalizacja());
                        break;

                    case TRANSPORT:
                        przesunJednostkeWKierunkuCelu(pogotowie,
                                pogotowie.getCelTransportu().getLokalizacja());
                        break;

                    case POWROT:
                        przesunJednostkeWKierunkuCelu(pogotowie,
                                pogotowie.getLokalizacjaStacjonowania());
                        break;
                }
            } else if (jednostka.getStatus() == StatusJednostki.ZADYSPONOWANY) {
                przesunJednostkeWKierunkuCelu(jednostka,
                        jednostka.getPrzypisaneZgloszenie().getLokalizacja());
            }
        }
    }

    private Optional<Osoba> znajdzPacjentaDoTransportu(Zgloszenie zgloszenie) {
        return zgloszenie.getOsobyDoHospitalizacji().stream()
                .filter(osoba -> zgloszenie.getPrzypisaniaDoSzpitali().containsKey(osoba))
                .filter(osoba -> !czyJestTransportowany(osoba, zgloszenie))
                .findFirst();
    }

    private boolean czyJestTransportowany(Osoba osoba, Zgloszenie zgloszenie) {
        return zgloszenie.getPrzypisaneJednostki().stream()
                .filter(j -> j instanceof Pogotowie)
                .map(j -> (Pogotowie)j)
                .anyMatch(p -> osoba.equals(p.getTransportowanyPacjent()));
    }

    private void aktualizujStatusyZgloszen() {
        for (Zgloszenie zgloszenie : dyspozytor.getAktywneZgloszenia()) {
            List<JednostkaRatownicza> zakonczoneJednostki = new ArrayList<>();

            for (JednostkaRatownicza jednostka : zgloszenie.getPrzypisaneJednostki()) {
                if (jednostka instanceof Pogotowie) {
                    Pogotowie pogotowie = (Pogotowie) jednostka;

                    switch (pogotowie.getStatus()) {
                        case ZAJETY:
                            Optional<Osoba> pacjentDoTransportu = znajdzPacjentaDoTransportu(zgloszenie);
                            if (pacjentDoTransportu.isPresent()) {
                                Osoba pacjent = pacjentDoTransportu.get();
                                Szpital szpital = zgloszenie.getPrzypisaniaDoSzpitali().get(pacjent);
                                if (szpital != null) {
                                    pogotowie.rozpocznijTransport(pacjent, szpital);
                                }
                            } else {
                                pogotowie.zarejestrujPomocPoszkodowanemu();
                                zakonczoneJednostki.add(pogotowie);
                            }
                            break;

                        case TRANSPORT:
                            if (pogotowie.getAktualnaLokalizacja().equals(
                                    pogotowie.getCelTransportu().getLokalizacja())) {
                                pogotowie.zakonczTransport();
                            }
                            break;

                        case POWROT:
                            if (pogotowie.getAktualnaLokalizacja().equals(
                                    pogotowie.getLokalizacjaStacjonowania())) {
                                pogotowie.powrotDoJednostki();
                            }
                            break;
                    }
                } else if (zgloszenie.getLiczbaPrzepracowanychTur() >=
                        zgloszenie.getLiczbaWymaganychTur()) {
                    zakonczoneJednostki.add(jednostka);
                }
            }

            for (JednostkaRatownicza jednostka : zakonczoneJednostki) {
                if (!(jednostka instanceof Pogotowie &&
                        ((Pogotowie)jednostka).jestWTrakceTransportu())) {
                    jednostka.powrotDoJednostki();
                }
            }

            zgloszenie.aktualizujDaneZgloszenia(
                    zgloszenie.getLiczbaPrzepracowanychTur() + 1,
                    zgloszenie.getLiczbaOsobKtorymUdzielonoPomocy() + zakonczoneJednostki.size()
            );
        }
    }
//Dodaje wypadek do listy zaplanowanych
    public void dodajZaplanowanyWypadek(int tura, Wypadek wypadek) {
        zaplanowaneWypadki.computeIfAbsent(tura, k -> new ArrayList<>()).add(wypadek);
    }
//Dodaj liste zaplanowanych wypadków
    public void dodajListeWypadkow(List<PlanowanyWypadek> listaWypadkow) {
        for (PlanowanyWypadek planowany : listaWypadkow) {
            Wypadek wypadek = new Wypadek(
                    planowany.getLokalizacja(),
                    planowany.getTypWypadku(),
                    planowany.getLiczbaOsob()
            );
            dodajZaplanowanyWypadek(planowany.getTura(), wypadek);
        }
    }
//Przetwarza wypadki zaplanowane na aktualna ture
    private void przetworzZaplanowaneWypadki() {
        List<Wypadek> wypadkiNaTure = zaplanowaneWypadki.get(aktualnaTura);
        if (wypadkiNaTure != null) {
            for (Wypadek wypadek : wypadkiNaTure) {
                dyspozytor.dodajWypadek(wypadek);
                System.out.println("Dodano zaplanowany wypadek: " + wypadek);
            }
        }
    }
//Wykonanie pojedynczej tury symulacji
    public void wykonajTure() {
        System.out.println("\n=== Tura " + aktualnaTura + " ===");

        generujLosowyWypadek();
        przetworzZaplanowaneWypadki();
        aktualizujPozycjeJednostek();
        aktualizujStatusyZgloszen();
        dyspozytor.aktualizujStatusy();

        System.out.println(dyspozytor.generujRaportStatusu());
        aktualnaTura++;
    }

    public void uruchomSymulacje(int liczba_tur) {
        System.out.println("Rozpoczynam symulację na " + liczba_tur + " tur");
        System.out.println("Obszar symulacji: " + maksymalnyObszarX + " x " + maksymalnyObszarY);
        System.out.println("Liczba jednostek: " + dyspozytor.getWszystkieJednostki().size());

        for (int i = 0; i < liczba_tur; i++) {
            wykonajTure();
        }
    }

    public static class PlanowanyWypadek {
        private final int tura;
        private final Lokalizacja lokalizacja;
        private final TypWypadku typWypadku;
        private final int liczbaOsob;

        public PlanowanyWypadek(int tura, Lokalizacja lokalizacja, TypWypadku typWypadku, int liczbaOsob) {
            this.tura = tura;
            this.lokalizacja = lokalizacja;
            this.typWypadku = typWypadku;
            this.liczbaOsob = liczbaOsob;
        }

        public int getTura() { return tura; }
        public Lokalizacja getLokalizacja() { return lokalizacja; }
        public TypWypadku getTypWypadku() { return typWypadku; }
        public int getLiczbaOsob() { return liczbaOsob; }
    }

    public static void main(String[] args) {
        Symulacja symulacja = new Symulacja(100.0, 100.0);
        List<PlanowanyWypadek> zaplanowaneWypadki = new ArrayList<>();

        zaplanowaneWypadki.add(new PlanowanyWypadek(
                2,
                new Lokalizacja(20.0, 30.0),
                TypWypadku.KATASTROFA_BUDOWLANA,
                4
        ));

        zaplanowaneWypadki.add(new PlanowanyWypadek(
                5,
                new Lokalizacja(60.0, 70.0),
                TypWypadku.POZAR_Z_POSZKODOWANYMI,
                3
        ));

        symulacja.dodajListeWypadkow(zaplanowaneWypadki);
        symulacja.uruchomSymulacje(20);
    }
}