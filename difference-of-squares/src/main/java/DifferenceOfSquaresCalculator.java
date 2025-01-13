class DifferenceOfSquaresCalculator {
    int squareOfSum, sumOfSquare;
    int computeSquareOfSumTo(int input) {
        for(int i =1; i<=input; i++){
            squareOfSum += i;
        }
        squareOfSum = (int) Math.pow(squareOfSum, 2);
        return squareOfSum;
    }

    int computeSumOfSquaresTo(int input) {
        for(int i =1; i<=input; i++){
            sumOfSquare += i*i;
        }
        return sumOfSquare;
    }

    int computeDifferenceOfSquares(int input) {
        return computeSquareOfSumTo(input) - computeSumOfSquaresTo(input);
    }

}
