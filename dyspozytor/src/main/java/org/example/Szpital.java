import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Szpital {
    private final String nazwa;
    private final int maksymalnaPojemnosc;
    private final Lokalizacja lokalizacja;
    private final Map<Osoba, Integer> pacjenci; // Osoba -> liczba pozostałych tur hospitalizacji
    private int dostepneMiejsca;
//Tworzy nowy szpital o określonej nazwie, pojemności i lokalizacji
    public Szpital(String nazwa, int maksymalnaPojemnosc, Lokalizacja lokalizacja) {
        this.nazwa = nazwa;
        this.maksymalnaPojemnosc = maksymalnaPojemnosc;
        this.lokalizacja = lokalizacja;
        this.pacjenci = new HashMap<>();
        this.dostepneMiejsca = maksymalnaPojemnosc;
    }
//Przyjmuje nowego pacjenta do szpitala, jeśli są wolne miejsca
    public boolean przyjmijPacjenta(Osoba osoba, int liczbaWymaganychTurHospitalizacji) {
        if (dostepneMiejsca > 0 && !pacjenci.containsKey(osoba)) {
            pacjenci.put(osoba, liczbaWymaganychTurHospitalizacji);
            dostepneMiejsca--;
            System.out.println("Przyjęto pacjenta do szpitala " + nazwa + ":");
            System.out.println("  - PESEL: " + osoba.getPesel());
            System.out.println("  - Płeć: " + osoba.getPlec());
            System.out.println("  - Wiek: " + osoba.getWiek());
            System.out.println("  - Przewidywany czas hospitalizacji: " + liczbaWymaganychTurHospitalizacji + " tur");
            return true;
        }
        return false;
    }
//Aktualizuje stan wszystkich pacjentów, zmniejszając pozostały czas hospitalizacji
    public void aktualizujStanPacjentow() {
        List<Osoba> pacjenciDoWypisu = new ArrayList<>();

        for (Map.Entry<Osoba, Integer> entry : pacjenci.entrySet()) {
            int pozostaleTury = entry.getValue() - 1;
            if (pozostaleTury <= 0) {
                pacjenciDoWypisu.add(entry.getKey());
            } else {
                entry.setValue(pozostaleTury);
            }
        }

        for (Osoba osoba : pacjenciDoWypisu) {
            wypiszPacjenta(osoba);
        }
    }

    public boolean wypiszPacjenta(Osoba osoba) {
        if (pacjenci.remove(osoba) != null) {
            dostepneMiejsca++;
            System.out.println("Wypisano pacjenta ze szpitala " + nazwa + ":");
            System.out.println("  - PESEL: " + osoba.getPesel());
            System.out.println("  - Płeć: " + osoba.getPlec());
            System.out.println("  - Wiek: " + osoba.getWiek());
            return true;
        }
        return false;
    }
    // Gettery
    public String getNazwa() {
        return nazwa;
    }

    public int getDostepneMiejsca() {
        return dostepneMiejsca;
    }

    public Lokalizacja getLokalizacja() {
        return lokalizacja;
    }

    public int getLiczbaHospitalizowanychPacjentow() {
        return pacjenci.size();
    }

    public boolean czyMaMiejsce() {
        return dostepneMiejsca > 0;
    }

    @Override
    public String toString() {
        return String.format("Szpital{nazwa='%s', zajęte=%d/%d, lokalizacja=%s}",
                nazwa, (maksymalnaPojemnosc - dostepneMiejsca), maksymalnaPojemnosc, lokalizacja);
    }
}
