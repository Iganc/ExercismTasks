class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {
        String number = String.valueOf(numberToCheck);
        int sum =0;
        for(int i=0; i < number.length(); i++){
            int j = Character.digit(number.charAt(i), 10);
            sum += (int) Math.pow(j, number.length());
            System.out.println(sum);
        }
        return sum == numberToCheck;
    }
}
