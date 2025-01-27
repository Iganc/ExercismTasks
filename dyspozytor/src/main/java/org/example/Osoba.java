import java.util.Random;

public class Osoba {
    private final String pesel;

    public Osoba(String pesel) {
        if (!walidujPESEL(pesel)) {
            throw new IllegalArgumentException("Nieprawidłowy numer PESEL");
        }
        this.pesel = pesel;
    }

    public String getPesel() {
        return pesel;
    }

    public static String generujPESEL() {
        StringBuilder pesel = new StringBuilder();
        Random random = new Random();

        // Generuj rok (00-99)
        int rok = random.nextInt(100);
        pesel.append(String.format("%02d", rok));

        // Generuj miesiąc (01-12)
        int miesiac = random.nextInt(12) + 1;
        pesel.append(String.format("%02d", miesiac));

        // Generuj dzień (01-31, z uwzględnieniem długości miesiąca)
        int maxDni;
        switch(miesiac) {
            case 2: // luty
                maxDni = 28;
                break;
            case 4: case 6: case 9: case 11: // miesiące 30-dniowe
                maxDni = 30;
                break;
            default: // miesiące 31-dniowe
                maxDni = 31;
        }
        int dzien = random.nextInt(maxDni) + 1;
        pesel.append(String.format("%02d", dzien));

        // Wagi używane do walidacji PESEL
        int[] wagi = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};
        int suma = 0;

        // Obliczamy sumę dla pierwszych 6 cyfr
        for (int i = 0; i < 6; i++) {
            int cyfra = Character.getNumericValue(pesel.charAt(i));
            suma += cyfra * wagi[i];
        }

        // Dodajemy 4 losowe cyfry (w tym cyfrę płci)
        for (int i = 0; i < 4; i++) {
            int cyfra = random.nextInt(10);
            pesel.append(cyfra);
            suma += cyfra * wagi[i + 6];
        }

        // Obliczamy cyfrę kontrolną
        int cyfraKontrolna = (10 - (suma % 10)) % 10;
        pesel.append(cyfraKontrolna);

        return pesel.toString();
    }

    private boolean walidujPESEL(String pesel) {
        // Sprawdzenie czy PESEL ma dokładnie 11 cyfr
        if (pesel == null || !pesel.matches("\\d{11}")) {
            return false;
        }

        // Konwersja stringa na tablicę cyfr
        int[] cyfry = new int[11];
        for (int i = 0; i < 11; i++) {
            cyfry[i] = Character.getNumericValue(pesel.charAt(i));
        }

        // Wagi używane do walidacji PESEL
        int[] wagi = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3, 1};

        // Obliczenie sumy kontrolnej
        int suma = 0;
        for (int i = 0; i < 11; i++) {
            suma += cyfry[i] * wagi[i];
        }

        // Sprawdzenie czy suma kontrolna jest poprawna
        return suma % 10 == 0;
    }

    public int getWiek() {
        int rok = Integer.parseInt(pesel.substring(0, 2));
        int miesiac = Integer.parseInt(pesel.substring(2, 4));

        // Określenie wieku na podstawie miesiąca urodzenia
        int pelnyRok;
        if (miesiac > 80) {
            pelnyRok = 1800 + rok;
        } else if (miesiac > 60) {
            pelnyRok = 2200 + rok;
        } else if (miesiac > 40) {
            pelnyRok = 2100 + rok;
        } else if (miesiac > 20) {
            pelnyRok = 2000 + rok;
        } else {
            pelnyRok = 1900 + rok;
        }

        // Aktualny rok
        int aktualnyRok = java.time.LocalDate.now().getYear();
        return aktualnyRok - pelnyRok;
    }

    public boolean isPelnoletni() {
        return getWiek() >= 18;
    }

    public String getPlec() {
        // Płeć określana jest na podstawie przedostatniej cyfry PESEL
        int cyfraPlci = Character.getNumericValue(pesel.charAt(9));
        return cyfraPlci % 2 == 0 ? "K" : "M";
    }

    @Override
    public String toString() {
        return "Osoba{" +
                "PESEL='" + pesel + '\'' +
                ", płeć='" + getPlec() + '\'' +
                ", wiek=" + getWiek() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Osoba osoba = (Osoba) o;
        return pesel.equals(osoba.pesel);
    }

    @Override
    public int hashCode() {
        return pesel.hashCode();
    }
}