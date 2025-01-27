public enum StatusWypadku {
    AKTYWNY("Wypadek jest w trakcie obsługi"),
    HISTORYCZNY("Wypadek został obsłużony");

    private final String opis;

    StatusWypadku(String opis) {
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