import java.util.List;

class IsogramChecker {

    boolean isIsogram(String phrase) {
        List<Character> repeated = new java.util.ArrayList<>(List.of());
        phrase = phrase.toLowerCase().replace("-", "").replace(" ", "");
        for(int i = 0 ; i < phrase.length(); i++){
            char c = phrase.charAt(i);
            if (Character.isLetter(c)) {
                if(repeated.contains(c)){
                    return false;
                }
            }
            repeated.add(c);
        }
        return true;
    }

}
