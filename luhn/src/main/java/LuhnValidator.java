class LuhnValidator {

    boolean isValid(String candidate) {
        candidate = candidate.replace(" ", "");
        int length = candidate.length();
        int sum = 0;
        boolean isSecond = false;
        if(length <= 1){
            return false;
        }
        for(int i = length -1 ; i >= 0; i--){
            char c = candidate.charAt(i);
            if(Character.isDigit(c)) {

                int num = Character.getNumericValue(candidate.charAt(i));

                if (isSecond) {
                    num *= 2;
                    if (num > 9) {
                        num -= 9;
                    }
                }
                sum += num;
                isSecond = !isSecond;
            } else if (!Character.isWhitespace(c)) {
                return false;
            }
        }
        return (sum%10==0);
    }

}
