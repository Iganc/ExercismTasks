import java.util.List;

class PrimeCalculator {

    int nth(int nth) {
        if(nth < 1){
            throw new IllegalArgumentException();
        }
        int num = 2;
        List<Integer> prime = new java.util.ArrayList<>(List.of());
        while(prime.size() < nth) {
            if(isPrime(num)){
                prime.add(num);
            }
            num++;
        }
        return prime.get(nth-1);
    }
    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

}
