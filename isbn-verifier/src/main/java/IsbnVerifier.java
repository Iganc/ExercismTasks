class IsbnVerifier {

    boolean isValid(String stringToVerify) {
        stringToVerify = stringToVerify.replace("-", "");
        if (stringToVerify.length() != 10) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            char currentChar = stringToVerify.charAt(i);
            int value;
            if (i == 9 && currentChar == 'X') {
                value = 10;
            } else if (Character.isDigit(currentChar)) {
                value = Character.getNumericValue(currentChar);
            } else {
                return false;
            }
            sum += value * (10 - i);
        }
        return sum % 11 == 0;
    }
}
