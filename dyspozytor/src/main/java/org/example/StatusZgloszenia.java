public enum StatusZgloszenia {
    AKTYWNE("Zgłoszenie jest w trakcie obsługi"),
    ZAKONCZONE("Zgłoszenie zostało zakończone");

    private final String opis;

    StatusZgloszenia(String opis) {
        this.opis = opis;
    }

    public String getOpis() {
        return opis;
    }

    @Override
    public String toString() {
        return name() + " (" + opis + ")";
    }
}