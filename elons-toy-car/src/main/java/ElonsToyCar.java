public class ElonsToyCar {
    private int METERS = 0;
    private int PERCENTAGE = 100;


    public static ElonsToyCar buy() {
        ElonsToyCar car = new ElonsToyCar();
        return car;
    }

    public String distanceDisplay() {
            return String.format("Driven %d meters", METERS);
    }
    public String batteryDisplay(){
        char perc = '%';
        if(PERCENTAGE > 0){
        return String.format("Battery at %d%c", PERCENTAGE, perc);
        }
        else{
            return String.format("Battery empty");
        }
    }

    public void drive() {
        if(PERCENTAGE > 0 ){
            METERS = METERS + 20;
            PERCENTAGE = PERCENTAGE - 1;
        }
    }
}
