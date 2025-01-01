
public class ExperimentalRemoteControlCar implements RemoteControlCar{
    public int distance;
    public void drive() {
        distance += 20;
    }

    public int getDistanceTravelled() {
        return distance;
    }
}
