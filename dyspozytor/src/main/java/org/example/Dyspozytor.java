import java.util.*;
import java.util.stream.Collectors;

public class Dyspozytor {
    private final List<Wypadek> aktywneWypadki;
    private final List<Wypadek> historyczneWypadki;
    private final List<Zgloszenie> aktywneZgloszenia;
    private final List<Zgloszenie> zakonczoneZgloszenia;
    private final List<JednostkaRatownicza> wszystkieJednostki;
    private final List<Szpital> dostepneSzpitale;

    public Dyspozytor() {
        this.aktywneWypadki = new ArrayList<>();
        this.historyczneWypadki = new ArrayList<>();
        this.aktywneZgloszenia = new ArrayList<>();
        this.zakonczoneZgloszenia = new ArrayList<>();
        this.wszystkieJednostki = new ArrayList<>();
        this.dostepneSzpitale = new ArrayList<>();
    }
//Dodaj nowy szpital do listy dostępnych szpitali
    public void dodajSzpital(Szpital szpital) {
        if (szpital != null && !dostepneSzpitale.contains(szpital)) {
            dostepneSzpitale.add(szpital);
            System.out.println("Dodano nowy szpital: " + szpital.getNazwa());
        }
    }
//Generuje osoby wymagajace hospitalizacji dla danego zgloszenia
    private void generujOsobyDoHospitalizacji(Zgloszenie zgloszenie) {
        int liczbaWymagajacychHospitalizacji = zgloszenie.getLiczbaOsobWymagajacychHospitalizacji();

        for (int i = 0; i < liczbaWymagajacychHospitalizacji; i++) {
            String pesel = Osoba.generujPESEL();
            Osoba osoba = new Osoba(pesel);
            zgloszenie.dodajOsobeDoHospitalizacji(osoba);
        }

        przydzielPacjentowDoSzpitali(zgloszenie);
    }

//Przydziela pacjentów do najbliższych szpitali z wolnymi miejscami
    public void przydzielPacjentowDoSzpitali(Zgloszenie zgloszenie) {
        for (Osoba osoba : zgloszenie.getOsobyDoHospitalizacji()) {
            if (!zgloszenie.getPrzypisaniaDoSzpitali().containsKey(osoba)) {
                Szpital najlepszySzpital = znajdzNajlepszySzpital(zgloszenie.getLokalizacja());
                if (najlepszySzpital != null && najlepszySzpital.czyMaMiejsce()) {
                    najlepszySzpital.przyjmijPacjenta(osoba, 3);
                    zgloszenie.przypiszOsobeDoSzpitala(osoba, najlepszySzpital);
                }
            }
        }
    }
//Znajduje najbliższy szpital z wolnymi miejscami
    private Szpital znajdzNajlepszySzpital(Lokalizacja lokalizacja) {
        return dostepneSzpitale.stream()
                .filter(Szpital::czyMaMiejsce)
                .min((s1, s2) -> Double.compare(
                        s1.getLokalizacja().obliczOdleglosc(lokalizacja),
                        s2.getLokalizacja().obliczOdleglosc(lokalizacja)))
                .orElse(null);
    }
//Tworzy nowe zgłoszenie na podstawie wypadku
    private Zgloszenie generujZgloszenie(Wypadek wypadek) {
        List<JednostkaRatownicza> wymaganeJednostki = znajdzWymaganeJednostki(wypadek);
        int liczbaWymagajacychHospitalizacji = wypadek.getLiczbaOsobPoszkodowanych() / 2;

        Zgloszenie zgloszenie = new Zgloszenie(
                wypadek.getLokalizacja(),
                wymaganeJednostki,
                wypadek.getTyp().getWymaganeLiczbyTur(),
                wypadek.getLiczbaOsobPoszkodowanych(),
                liczbaWymagajacychHospitalizacji
        );

        generujOsobyDoHospitalizacji(zgloszenie);
        return zgloszenie;
    }
//Dodaje nową jednostkę ratowniczą do systemu
    public void dodajJednostke(JednostkaRatownicza jednostka) {
        wszystkieJednostki.add(jednostka);
    }
    //Obsługuje nowy wypadek - tworzy zgłoszenie i przypisuje jednostki
    public void dodajWypadek(Wypadek wypadek) {
        aktywneWypadki.add(wypadek);
        Zgloszenie noweZgloszenie = generujZgloszenie(wypadek);
        aktywneZgloszenia.add(noweZgloszenie);
        przypiszJednostkiRatownicze(noweZgloszenie);
    }
//Znajduje wymagane jednostki dla danego wypadku
    private List<JednostkaRatownicza> znajdzWymaganeJednostki(Wypadek wypadek) {
        List<JednostkaRatownicza> wymagane = new ArrayList<>();
        Class<? extends JednostkaRatownicza>[] wymaganeTypy = wypadek.getWymaganeJednostki();

        for (Class<? extends JednostkaRatownicza> typ : wymaganeTypy) {
            if (typ.equals(Pogotowie.class)) {
                int potrzebneJednostki = wypadek.getLiczbaOsobPoszkodowanych();
                for (int i = 0; i < potrzebneJednostki; i++) {
                    Optional<JednostkaRatownicza> jednostka = znajdzNajblizszaWolnaJednostke(typ, wypadek.getLokalizacja());
                    jednostka.ifPresent(wymagane::add);
                }
            } else {
                Optional<JednostkaRatownicza> jednostka = znajdzNajblizszaWolnaJednostke(typ, wypadek.getLokalizacja());
                jednostka.ifPresent(wymagane::add);
            }
        }
        return wymagane;
    }
//Znajduje najbliższą wolną jednostkę danego typu
    private Optional<JednostkaRatownicza> znajdzNajblizszaWolnaJednostke(
            Class<? extends JednostkaRatownicza> typ,
            Lokalizacja lokalizacja) {
        return wszystkieJednostki.stream()
                .filter(j -> j.getClass().equals(typ))
                .filter(j -> j.getStatus() == StatusJednostki.DOSTEPNY)
                .min((j1, j2) -> {
                    double odl1 = j1.getAktualnaLokalizacja().obliczOdleglosc(lokalizacja);
                    double odl2 = j2.getAktualnaLokalizacja().obliczOdleglosc(lokalizacja);
                    return Double.compare(odl1, odl2);
                });
    }
//Przypisuje jednostki ratownicze do zgłoszeń według priorytetów
    public void przypiszJednostkiRatownicze(Zgloszenie zgloszenie) {
        List<Zgloszenie> posortowaneZgloszenia = aktywneZgloszenia.stream()
                .filter(z -> !maWystarczajaceJednostki(z))
                .sorted((z1, z2) -> Integer.compare(
                        obliczPriorytetZgloszenia(z2),
                        obliczPriorytetZgloszenia(z1)))
                .collect(Collectors.toList());

        for (Zgloszenie z : posortowaneZgloszenia) {
            przydzielDostepneJednostki(z);
        }
    }
//Sprawdza czy zgłoszenie ma wystarczającą liczbę jednostek
    private boolean maWystarczajaceJednostki(Zgloszenie zgloszenie) {
        return zgloszenie.getPrzypisaneJednostki().size() >= zgloszenie.getWymaganeJednostki().size();
    }
//Przydziela dostępne jednostki do zgłoszenia
    private void przydzielDostepneJednostki(Zgloszenie zgloszenie) {
        List<JednostkaRatownicza> wymagane = zgloszenie.getWymaganeJednostki();
        for (JednostkaRatownicza jednostka : wymagane) {
            if (!zgloszenie.getPrzypisaneJednostki().contains(jednostka)) {
                Optional<JednostkaRatownicza> dostepnaJednostka =
                        znajdzNajblizszaWolnaJednostke(jednostka.getClass(), zgloszenie.getLokalizacja());

                dostepnaJednostka.ifPresent(j -> {
                    j.przypiszDoZdarzenia(zgloszenie);
                    zgloszenie.przypiszJednostkeRatownicza(j);
                });
            }
        }
    }
//Oblicza priorytet zgłoszenia na podstawie liczby osób i czasu oczekiwania
    private int obliczPriorytetZgloszenia(Zgloszenie zgloszenie) {
        int priorytet = zgloszenie.getLiczbaOsobWymagajacychPomocy() * 2;

        long czasOczekiwania = System.currentTimeMillis() -
                zgloszenie.getZnacznikCzasu().toInstant(java.time.ZoneOffset.UTC).toEpochMilli();
        priorytet += Math.min(5, czasOczekiwania / (1000 * 60 * 10));

        return Math.min(10, priorytet);
    }
// Aktualizuje statusy wszystkich elementów systemu (zgłoszenia, szpitale, wypadki)
    public void aktualizujStatusy() {
        Iterator<Zgloszenie> iteratorZgloszen = aktywneZgloszenia.iterator();
        while (iteratorZgloszen.hasNext()) {
            Zgloszenie zgloszenie = iteratorZgloszen.next();
            if (zgloszenie.getStatus() == StatusZgloszenia.ZAKONCZONE) {
                iteratorZgloszen.remove();
                zakonczoneZgloszenia.add(zgloszenie);

                for (JednostkaRatownicza jednostka : zgloszenie.getPrzypisaneJednostki()) {
                    jednostka.powrotDoJednostki();
                }
            }
        }

        for (Szpital szpital : dostepneSzpitale) {
            szpital.aktualizujStanPacjentow();
        }

        Iterator<Wypadek> iteratorWypadkow = aktywneWypadki.iterator();
        while (iteratorWypadkow.hasNext()) {
            Wypadek wypadek = iteratorWypadkow.next();
            if (wypadek.getStatus() == StatusWypadku.HISTORYCZNY) {
                iteratorWypadkow.remove();
                historyczneWypadki.add(wypadek);
            }
        }
    }
//Zwracanie list
    public List<JednostkaRatownicza> getWszystkieJednostki() {
        return new ArrayList<>(wszystkieJednostki);
    }

    public List<Zgloszenie> getAktywneZgloszenia() {
        return new ArrayList<>(aktywneZgloszenia);
    }

    public List<Zgloszenie> getZakonczoneZgloszenia() {
        return new ArrayList<>(zakonczoneZgloszenia);
    }

    public String generujRaportStatusu() {
        StringBuilder raport = new StringBuilder("=== Raport Dyspozytora ===\n");

        raport.append("-- Aktywne wypadki --\n");
        for (Wypadek w : aktywneWypadki) {
            raport.append(String.format("   Wypadek{id=%s, typ=%s, lokalizacja=%s, status=%s, poszkodowani=%d}\n",
                    w.getId(), w.getTyp(), w.getLokalizacja(), w.getStatus(), w.getLiczbaOsobPoszkodowanych()));
        }

        raport.append("\n-- Aktywne zgłoszenia --\n");
        for (Zgloszenie z : aktywneZgloszenia) {
            raport.append(String.format("   Zgloszenie{id=%s, lok=%s, status=%s, turPotrzebne=%d, turWykonane=%d, pomocy=%d/%d}\n",
                    z.getId(), z.getLokalizacja(), z.getStatus(),
                    z.getLiczbaWymaganychTur(), z.getLiczbaPrzepracowanychTur(),
                    z.getLiczbaOsobKtorymUdzielonoPomocy(), z.getLiczbaOsobWymagajacychPomocy()));

            // Dodanie informacji o hospitalizacji
            raport.append(String.format("      Hospitalizacja: %d osób przypisanych do szpitali\n",
                    z.getPrzypisaniaDoSzpitali().size()));
        }

        raport.append("\n-- Zakończone zgłoszenia --\n");
        for (Zgloszenie z : zakonczoneZgloszenia) {
            raport.append(String.format("   Zgloszenie{id=%s, lok=%s, status=%s, turPotrzebne=%d, turWykonane=%d, pomocy=%d/%d}\n",
                    z.getId(), z.getLokalizacja(), z.getStatus(),
                    z.getLiczbaWymaganychTur(), z.getLiczbaPrzepracowanychTur(),
                    z.getLiczbaOsobKtorymUdzielonoPomocy(), z.getLiczbaOsobWymagajacychPomocy()));
        }

        raport.append("\n-- Jednostki --\n");
        for (JednostkaRatownicza j : wszystkieJednostki) {
            double dystans = 0.0;
            String celPodrozy = "null";
            if (j.getPrzypisaneZgloszenie() != null) {
                celPodrozy = j.getPrzypisaneZgloszenie().getLokalizacja().toString();
                dystans = j.getAktualnaLokalizacja().obliczOdleglosc(
                        j.getPrzypisaneZgloszenie().getLokalizacja());
            }

            raport.append(String.format("   %s{id=%s, status=%s, lok=%s, celPodrozy=%s, dist=%.2f}\n",
                    j.getTypJednostki(), j.getId(), j.getStatus(),
                    j.getAktualnaLokalizacja(), celPodrozy, dystans));
        }

        raport.append("\n-- Szpitale --\n");
        for (Szpital szpital : dostepneSzpitale) {
            raport.append(String.format("   %s\n", szpital));
        }

        return raport.toString();
    }
}