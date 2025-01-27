import java.util.UUID;

public abstract class JednostkaRatownicza {
    private final String id;
    private final Lokalizacja lokalizacjaStacjonowania;
    private Lokalizacja aktualnaLokalizacja;
    private StatusJednostki status;
    private Zgloszenie przypisaneZgloszenie;

//Tworzy nową jednostkę ratowniczą z określonym ID i lokalizacją bazy
    public JednostkaRatownicza(String id, Lokalizacja lokalizacjaStacjonowania) {
        this.id = id;
        this.lokalizacjaStacjonowania = lokalizacjaStacjonowania;
        this.aktualnaLokalizacja = lokalizacjaStacjonowania;
        this.status = StatusJednostki.DOSTEPNY;
    }

    // Gettery
    public String getId() {
        return id;
    }

    public Lokalizacja getLokalizacjaStacjonowania() {
        return lokalizacjaStacjonowania;
    }

    public Lokalizacja getAktualnaLokalizacja() {
        return aktualnaLokalizacja;
    }

    public StatusJednostki getStatus() {
        return status;
    }

    public Zgloszenie getPrzypisaneZgloszenie() {
        return przypisaneZgloszenie;
    }

    // Metody
    public void zmienStatus(StatusJednostki nowyStatus) {
        this.status = nowyStatus;
    }
//Przypisuje jednostkę do zdarzenia, jeśli jest dostępna
    public void przypiszDoZdarzenia(Zgloszenie zgloszenie) {
        if (status == StatusJednostki.DOSTEPNY) {
            this.przypisaneZgloszenie = zgloszenie;
            this.status = StatusJednostki.ZADYSPONOWANY;
        } else {
            throw new IllegalStateException("Jednostka nie jest dostępna do przypisania");
        }
    }

    public void zmienLokalizacje(Lokalizacja nowaLokalizacja) {
        this.aktualnaLokalizacja = nowaLokalizacja;
    }
//Przywraca jednostkę do bazy i resetuje jej stan
    public void powrotDoJednostki() {
        this.aktualnaLokalizacja = this.lokalizacjaStacjonowania;
        this.status = StatusJednostki.DOSTEPNY;
        this.przypisaneZgloszenie = null;
    }

    // Metoda abstrakcyjna do implementacji w klasach pochodnych
    public abstract String getTypJednostki();

    @Override
    public String toString() {
        return String.format("%s{id='%s', status=%s, lokalizacja=%s}",
                getTypJednostki(), id, status, aktualnaLokalizacja);
    }
}