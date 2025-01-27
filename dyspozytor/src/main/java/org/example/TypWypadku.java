public enum TypWypadku {
    // Zmienione deklaracje - usunięty parametr priorytetu
    WYPADEK_DROGOWY(new Class[]{Pogotowie.class, Policja.class}, 1),
    POZAR(new Class[]{Straz.class}, 2),
    POZAR_Z_POSZKODOWANYMI(new Class[]{Straz.class, Pogotowie.class}, 2),
    KATASTROFA_BUDOWLANA(new Class[]{Straz.class, Pogotowie.class, Policja.class}, 3),
    ZASLABNIECIE(new Class[]{Pogotowie.class}, 1);

    private final Class<? extends JednostkaRatownicza>[] wymaganeJednostki;
    private final int wymaganeLiczbyTur; // liczba tur potrzebna do obsługi zdarzenia

    // Zmieniony konstruktor - usunięty parametr priorytetu
    TypWypadku(Class<? extends JednostkaRatownicza>[] wymaganeJednostki, int wymaganeLiczbyTur) {
        this.wymaganeJednostki = wymaganeJednostki;
        this.wymaganeLiczbyTur = wymaganeLiczbyTur;
    }

    public Class<? extends JednostkaRatownicza>[] getWymaganeJednostki() {
        return wymaganeJednostki.clone();
    }

    public int getWymaganeLiczbyTur() {
        return wymaganeLiczbyTur;
    }

    public boolean wymagaJednostki(Class<? extends JednostkaRatownicza> typJednostki) {
        for (Class<? extends JednostkaRatownicza> wymaganaJednostka : wymaganeJednostki) {
            if (wymaganaJednostka.equals(typJednostki)) {
                return true;
            }
        }
        return false;
    }
}