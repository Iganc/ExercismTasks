class CollatzCalculator {
    int computeStepCount(int start) {
        if(start <= 0){
            throw new IllegalArgumentException("Only positive integers are allowed");
        }
        int counter = 0;
        for(; start != 1; counter++){
            start = (start % 2 == 0) ? start / 2 : start * 3 + 1;
        }
        return counter;
    }
}
