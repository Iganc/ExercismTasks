public class CarsAssemble {

    public double total;

    public double productionRatePerHour(int speed) {
        double produced = speed * 221;
        double faulty = 1.0;


        if(speed >= 1 && speed <= 4){
            faulty = 1.0;
        }
        else if (speed >= 5 && speed <= 8) {
            faulty = 0.9;
        }
        else if (speed == 9) {
            faulty = 0.8;
        }
        else if (speed == 10) {
            faulty = 0.77;
        }
        total = produced * faulty;
        return total;
    }

    public int workingItemsPerMinute(int speed) {
         double Hour = productionRatePerHour(speed);
         Hour /= 60;
         int cars = (int) Hour;
         return cars;

    }
}
