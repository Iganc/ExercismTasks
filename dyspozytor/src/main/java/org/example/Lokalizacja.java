import java.util.Objects;

public class Lokalizacja {
    private final double x;
    private final double y;

    public Lokalizacja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Gettery
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    /**
     * Oblicza odległość euklidesową między dwoma lokalizacjami
     * @param other druga lokalizacja
     * @return odległość między punktami
     */
    public double obliczOdleglosc(Lokalizacja other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Oblicza kierunek do innej lokalizacji w radianach
     * @param other druga lokalizacja
     * @return kąt w radianach
     */
    public double obliczKierunek(Lokalizacja other) {
        return Math.atan2(other.y - this.y, other.x - this.x);
    }

    /**
     * Tworzy nową lokalizację przesuniętą o zadaną odległość w danym kierunku
     * @param odleglosc odległość do przesunięcia
     * @param kierunek kierunek w radianach
     * @return nowa lokalizacja
     */
    public Lokalizacja przesun(double odleglosc, double kierunek) {
        double noweX = x + odleglosc * Math.cos(kierunek);
        double noweY = y + odleglosc * Math.sin(kierunek);
        return new Lokalizacja(noweX, noweY);
    }

    /**
     * Sprawdza czy punkt znajduje się w zadanym promieniu od bieżącej lokalizacji
     * @param other sprawdzana lokalizacja
     * @param promien maksymalna odległość
     * @return true jeśli punkt jest w zasięgu
     */
    public boolean czyWZasiegu(Lokalizacja other, double promien) {
        return obliczOdleglosc(other) <= promien;
    }

    @Override
    public String toString() {
        return String.format("(%.2f, %.2f)", x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lokalizacja that = (Lokalizacja) o;

        // Używamy małej delty dla porównywania liczb zmiennoprzecinkowych
        double EPSILON = 0.0001;
        return Math.abs(that.x - x) < EPSILON &&
                Math.abs(that.y - y) < EPSILON;
    }

    @Override
    public int hashCode() {
        // Zaokrąglamy współrzędne do 4 miejsc po przecinku dla stabilnego hashCode
        long tempX = Math.round(x * 10000);
        long tempY = Math.round(y * 10000);
        return Objects.hash(tempX, tempY);
    }
}