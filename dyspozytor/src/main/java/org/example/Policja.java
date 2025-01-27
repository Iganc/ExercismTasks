public class Policja extends JednostkaRatownicza {
    public Policja(String id, Lokalizacja lokalizacjaStacjonowania) {
        super(id, lokalizacjaStacjonowania);
    }

    @Override
    public String getTypJednostki() {
        return "Policja";
    }
}