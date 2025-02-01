import java.util.ArrayList;
import java.util.List;

class LargestSeriesProductCalculator {
    int length;
    List<Integer> allDigits =  new ArrayList<>();
    long sum = 0;
    LargestSeriesProductCalculator(String inputNumber) {
        if (!inputNumber.matches("\\d+")) {
            throw new IllegalArgumentException("String to search may only contain digits.");
        }
        length = inputNumber.length();
        for(int i = 0; i < length; i++){
            allDigits.add(Character.getNumericValue(inputNumber.charAt(i)));
        }
    }

    long calculateLargestProductForSeriesLength(int numberOfDigits) {
        if(numberOfDigits < 0){
            throw new IllegalArgumentException("Series length must be non-negative.");
        }
        if (numberOfDigits > length) {
            throw new IllegalArgumentException("Series length must be less than or equal to the length of the string to search.");
        }

        for (int i = 0; i <= length - numberOfDigits; i++) {
            long product = 1;
            for(int j = 0; j<numberOfDigits; j++){
                product *= allDigits.get(i+j);
            }
            if(product > sum){
                sum = product;
            }
        }
        return sum;
    }
}
