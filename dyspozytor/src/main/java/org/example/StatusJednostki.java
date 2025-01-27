public enum StatusJednostki {
    DOSTEPNY("Jednostka jest dostępna do zadysponowania"),
    ZADYSPONOWANY("Jednostka jest w drodze do zdarzenia"),
    ZAJETY("Jednostka prowadzi działania na miejscu zdarzenia"),
    TRANSPORT("Jednostka transportuje pacjenta do szpitala"), // Nowy status
    POWROT("Jednostka wraca do bazy"); // Nowy status

    private final String opis;

    StatusJednostki(String opis) {
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