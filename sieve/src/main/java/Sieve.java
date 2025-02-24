import java.util.ArrayList;
import java.util.List;

class Sieve {
    List<NumberInfo> numberInfoList = new ArrayList<>();
    Sieve(int maxPrime) {
        for(int i = 2; i <= maxPrime; i++){
            numberInfoList.add(new NumberInfo(i, true));
        }
    }

    List<Integer> getPrimes() {
        if(numberInfoList.size() < 1){
            return new ArrayList<>();
        }

        int max = numberInfoList.get(numberInfoList.size() - 1).getNumber();
        for(int i = 0; i < numberInfoList.size(); i++){
            NumberInfo current = numberInfoList.get(i);

            if(current.getInfo()){
                int value = current.getNumber();
                for (int multiple = value * 2; multiple <= max; multiple += value) {
                    int index = multiple - 2;
                    numberInfoList.get(index).setInfo(false);
                }
            }
        }

        List<Integer> primes = new ArrayList<>();
        for (NumberInfo ni : numberInfoList) {
            if (ni.getInfo()) {
                primes.add(ni.getNumber());
            }
        }
        return primes;
    }
}

class NumberInfo {
    private int number;
    private boolean info;

    public NumberInfo(int number, boolean info) {
        this.number = number;
        this.info = info;
    }

    public int getNumber() {
        return number;
    }

    public boolean getInfo() {
        return info;
    }
    public void setInfo(boolean info){
        this.info = info;
    }
}
