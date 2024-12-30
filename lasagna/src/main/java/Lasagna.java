public class Lasagna {
    public int expectedMinutesInOven(){
        int oven_time = 40;
        return oven_time;
    }
    public int remainingMinutesInOven(int time_cooked){
        int remaining = expectedMinutesInOven() - time_cooked;
        return remaining;
    }
    public int preparationTimeInMinutes(int layers){
        int time = 2 * layers;
        return time;
    }
    public int totalTimeInMinutes(int layers, int time_cooked){
        int total = preparationTimeInMinutes(layers) + time_cooked;
        return total;
    }
}