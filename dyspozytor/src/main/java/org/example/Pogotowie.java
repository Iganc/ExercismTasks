public class Pogotowie extends JednostkaRatownicza {
    private int liczbaObsluzonychOsob;
    private Osoba transportowanyPacjent;
    private Szpital celTransportu;

    public Pogotowie(String id, Lokalizacja lokalizacjaStacjonowania) {
        super(id, lokalizacjaStacjonowania);
        this.liczbaObsluzonychOsob = 0;
        this.transportowanyPacjent = null;
        this.celTransportu = null;
    }
    public void rozpocznijTransport(Osoba pacjent, Szpital szpital) {
        if (this.getStatus() == StatusJednostki.ZAJETY) {
            this.transportowanyPacjent = pacjent;
            this.celTransportu = szpital;
            this.zmienStatus(StatusJednostki.TRANSPORT);
        }
    }

    public boolean jestWTrakceTransportu() {
        return this.getStatus() == StatusJednostki.TRANSPORT;
    }

    public Szpital getCelTransportu() {
        return celTransportu;
    }

    public Osoba getTransportowanyPacjent() {
        return transportowanyPacjent;
    }

    public void zakonczTransport() {
        if (this.getStatus() == StatusJednostki.TRANSPORT) {
            this.zmienStatus(StatusJednostki.POWROT);
            this.transportowanyPacjent = null;
            this.celTransportu = null;
        }
    }

    @Override
    public String getTypJednostki() {
        return "Pogotowie";
    }

    public void zarejestrujPomocPoszkodowanemu() {
        this.liczbaObsluzonychOsob++;
    }

    public int getLiczbaObsluzonychOsob() {
        return liczbaObsluzonychOsob;
    }
}