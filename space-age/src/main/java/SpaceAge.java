class SpaceAge {
    public double sec;
    public double multiplier;
    SpaceAge(double seconds) {
        sec = seconds;
    }

    double onEarth() {
        multiplier = 1;
        return sec / (31557600 * multiplier);
    }

    double onMercury() {
        multiplier = 0.2408467;
        return sec / (31557600 * multiplier);
    }

    double onVenus() {
        multiplier = 0.61519726;
        return sec / (31557600 * multiplier);
    }

    double onMars() {
        multiplier = 1.8808158;
        return sec / (31557600 * multiplier);  
    }

    double onJupiter() {
        multiplier = 11.862615;
        return sec / (31557600 * multiplier);
    }

    double onSaturn() {
        multiplier = 29.447498;
        return sec / (31557600 * multiplier);
    }

    double onUranus() {
        multiplier = 84.016846;
        return sec / (31557600 * multiplier);
    }

    double onNeptune() {
        multiplier = 164.79132;
        return sec / (31557600 * multiplier);
    }

}
