import java.util.HashSet;
import java.util.Set;

public class PangramChecker {

    public boolean isPangram(String input) {
        input = input.replace(" ", "").toLowerCase();

        int alphabet = 26;
        Set<Character> s = new HashSet<>();
        for(int i = 0; i < input.length(); i++){
            char aaa = input.charAt(i);
            if (Character.isLetter(aaa)) {
                s.add(aaa);
            }
        }
        return s.size() == alphabet;
    }

}
