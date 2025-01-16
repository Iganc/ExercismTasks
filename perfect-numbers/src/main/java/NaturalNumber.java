class NaturalNumber {
    public int sum = 0;
    int identification = 0;
    NaturalNumber(int number) {
       identification = number;
       if(identification <= 0 ){
           throw new IllegalArgumentException("You must supply a natural number (positive integer)");
       }
       for(int i = 1; i < number; i++){
           if(number % i == 0){
               sum += i;
           }
       }
    }

    Classification getClassification() {
        if (sum == identification) {
            return Classification.PERFECT;
        } else if (sum > identification) {
            return Classification.ABUNDANT;
        } else {
            return Classification.DEFICIENT;
        }
    }
}
