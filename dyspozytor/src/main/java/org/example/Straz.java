public class Straz extends JednostkaRatownicza {
    public Straz(String id, Lokalizacja lokalizacjaStacjonowania) {
        super(id, lokalizacjaStacjonowania);
    }

    @Override
    public String getTypJednostki() {
        return "Straż Pożarna";
    }
}