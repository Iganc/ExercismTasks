class ReverseString {

    String reverse(String inputString) {
        int lenght = inputString.length();
        StringBuilder outputString = new StringBuilder();
        char[] letters = new char[lenght];
        for (int i = 0; i < lenght; i++){
            letters[i]=inputString.charAt(i);
        }
        for (int i = lenght - 1; i >= 0; i--){
            outputString.append(inputString.charAt(i));
        }
        return outputString.toString();
    }
  
}
