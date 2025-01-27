import java.time.LocalDateTime;
import java.util.*;

public class Zgloszenie {
    private final String id;
    private final LocalDateTime znacznikCzasu;
    private final Lokalizacja lokalizacja;
    private StatusZgloszenia status;
    private final List<JednostkaRatownicza> wymaganeJednostki;
    private final List<JednostkaRatownicza> przypisaneJednostki;
    private final int liczbaWymaganychTur;
    private int liczbaPrzepracowanychTur;
    private int liczbaOsobWymagajacychPomocy;
    private int liczbaOsobKtorymUdzielonoPomocy;
    private int liczbaOsobWymagajacychHospitalizacji;
    private List<Osoba> osobyDoHospitalizacji;
    private Map<Osoba, Szpital> przypisaniaDoSzpitali;
//Tworzenie nowego zgloszenia z okreslonymi parametrami
    public Zgloszenie(Lokalizacja lokalizacja, List<JednostkaRatownicza> wymaganeJednostki,
                      int liczbaWymaganychTur, int liczbaOsobWymagajacychPomocy,
                      int liczbaOsobWymagajacychHospitalizacji){
        this.id = UUID.randomUUID().toString();
        this.znacznikCzasu = LocalDateTime.now();
        this.lokalizacja = lokalizacja;
        this.status = StatusZgloszenia.AKTYWNE;
        this.wymaganeJednostki = new ArrayList<>(wymaganeJednostki);
        this.przypisaneJednostki = new ArrayList<>();
        this.liczbaWymaganychTur = liczbaWymaganychTur;
        this.liczbaPrzepracowanychTur = 0;
        this.liczbaOsobWymagajacychPomocy = liczbaOsobWymagajacychPomocy;
        this.liczbaOsobKtorymUdzielonoPomocy = 0;
        this.liczbaOsobWymagajacychHospitalizacji = liczbaOsobWymagajacychHospitalizacji;
        this.osobyDoHospitalizacji = new ArrayList<>();
        this.przypisaniaDoSzpitali = new HashMap<>();

    }
    public void dodajOsobeDoHospitalizacji(Osoba osoba) {
        if (osobyDoHospitalizacji.size() < liczbaOsobWymagajacychHospitalizacji) {
            osobyDoHospitalizacji.add(osoba);
        }
    }

    public void przypiszOsobeDoSzpitala(Osoba osoba, Szpital szpital) {
        if (osobyDoHospitalizacji.contains(osoba) && !przypisaniaDoSzpitali.containsKey(osoba)) {
            przypisaniaDoSzpitali.put(osoba, szpital);
        }
    }

    public int getLiczbaOsobWymagajacychHospitalizacji() {
        return liczbaOsobWymagajacychHospitalizacji;
    }

    public List<Osoba> getOsobyDoHospitalizacji() {
        return new ArrayList<>(osobyDoHospitalizacji);
    }

    public Map<Osoba, Szpital> getPrzypisaniaDoSzpitali() {
        return new HashMap<>(przypisaniaDoSzpitali);
    }

    // Gettery
    public String getId() {
        return id;
    }

    public LocalDateTime getZnacznikCzasu() {
        return znacznikCzasu;
    }

    public Lokalizacja getLokalizacja() {
        return lokalizacja;
    }

    public StatusZgloszenia getStatus() {
        return status;
    }

    public List<JednostkaRatownicza> getWymaganeJednostki() {
        return new ArrayList<>(wymaganeJednostki);
    }

    public List<JednostkaRatownicza> getPrzypisaneJednostki() {
        return new ArrayList<>(przypisaneJednostki);
    }

    public int getLiczbaWymaganychTur() {
        return liczbaWymaganychTur;
    }

    public int getLiczbaPrzepracowanychTur() {
        return liczbaPrzepracowanychTur;
    }

    public int getLiczbaOsobWymagajacychPomocy() {
        return liczbaOsobWymagajacychPomocy;
    }

    public int getLiczbaOsobKtorymUdzielonoPomocy() {
        return liczbaOsobKtorymUdzielonoPomocy;
    }


    public void przypiszJednostkeRatownicza(JednostkaRatownicza jednostka) {
        if (!przypisaneJednostki.contains(jednostka)) {
            przypisaneJednostki.add(jednostka);
        }
    }

    public void aktualizujDaneZgloszenia(int nowaPrzepracowanaTura, int nowaLiczbaOsobZPomoca) {
        this.liczbaPrzepracowanychTur = nowaPrzepracowanaTura;
        this.liczbaOsobKtorymUdzielonoPomocy = nowaLiczbaOsobZPomoca;

        // Automatycznie zakończ zgłoszenie jeśli spełnione są warunki
        if (czyMoznaZakonczyc()) {
            this.status = StatusZgloszenia.ZAKONCZONE;
        }
    }

    private boolean czyMoznaZakonczyc() {
        return liczbaPrzepracowanychTur >= liczbaWymaganychTur &&
                liczbaOsobKtorymUdzielonoPomocy >= liczbaOsobWymagajacychPomocy;
    }

    @Override
    public String toString() {
        return String.format(
                "Zgłoszenie{id='%s', status=%s, lokalizacja=%s, postęp=%d/%d tur, pomoc=%d/%d osób}",
                id, status, lokalizacja, liczbaPrzepracowanychTur, liczbaWymaganychTur,
                liczbaOsobKtorymUdzielonoPomocy, liczbaOsobWymagajacychPomocy
        );
    }
}