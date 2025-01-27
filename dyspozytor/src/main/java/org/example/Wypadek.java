import java.time.LocalDateTime;
import java.util.UUID;

public class Wypadek {
    private final String id;
    private final LocalDateTime znacznikCzasu;
    private final Lokalizacja lokalizacja;
    private StatusWypadku status;
    private final TypWypadku typ;
    private final int liczbaOsobPoszkodowanych;

    public Wypadek(Lokalizacja lokalizacja, TypWypadku typ, int liczbaOsobPoszkodowanych) {
        this.id = UUID.randomUUID().toString();
        this.znacznikCzasu = LocalDateTime.now();
        this.lokalizacja = lokalizacja;
        this.status = StatusWypadku.AKTYWNY;
        this.typ = typ;
        this.liczbaOsobPoszkodowanych = liczbaOsobPoszkodowanych;
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

    public StatusWypadku getStatus() {
        return status;
    }

    public TypWypadku getTyp() {
        return typ;
    }

    public int getLiczbaOsobPoszkodowanych() {
        return liczbaOsobPoszkodowanych;
    }


    // Metody
    public void zmienStatus(StatusWypadku nowyStatus) {
        this.status = nowyStatus;
    }


    public Class<? extends JednostkaRatownicza>[] getWymaganeJednostki() {
        return typ.getWymaganeJednostki();
    }

    public boolean czyWymagaJednostki(Class<? extends JednostkaRatownicza> typJednostki) {
        return typ.wymagaJednostki(typJednostki);
    }

    @Override
    public String toString() {
        return String.format("Wypadek{id='%s', typ=%s, lokalizacja=%s, status=%s, " +
                        "poszkodowani=%d}",
                id, typ, lokalizacja, status,
                liczbaOsobPoszkodowanych);
    }
}
