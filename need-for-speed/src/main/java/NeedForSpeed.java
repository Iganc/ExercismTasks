class NeedForSpeed {
    private int speed;
    private int batteryDrain;
    private int distanceDriven;
    private int percentage;
    NeedForSpeed(int speed, int batteryDrain) {
        this.speed = speed;
        this.batteryDrain = batteryDrain;
        this.distanceDriven = 0;
        this.percentage = 100;
    }

    public boolean batteryDrained() {
       return percentage < batteryDrain;
    }

    public int distanceDriven() {
        return distanceDriven;
    }

    public void drive() {
        if(!batteryDrained()) {
            distanceDriven += speed;
            percentage -= batteryDrain;
        }
    }

    public static NeedForSpeed nitro() {
        return new NeedForSpeed(50, 4);
    }

    public int getBatteryDrain() {
        return batteryDrain;
    }
    public int getSpeed() {
        return speed;
    }
}

class RaceTrack {
    private int distance;
    RaceTrack(int distance) {
        this.distance = distance;
    }

    public boolean canFinishRace(NeedForSpeed car) {
        int max = car.getSpeed() * (100 / car.getBatteryDrain());
        return max >= distance;
    }
}
