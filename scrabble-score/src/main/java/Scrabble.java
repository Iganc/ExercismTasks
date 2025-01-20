import java.util.Dictionary;
import java.util.Hashtable;

class Scrabble {
    public int sum;
    Scrabble(String word) {
        Dictionary<String, Integer> dict = new Hashtable<>();
        dict.put("A", 1);
        dict.put("E", 1);
        dict.put("I", 1);
        dict.put("O", 1);
        dict.put("U", 1);
        dict.put("L", 1);
        dict.put("N", 1);
        dict.put("R", 1);
        dict.put("S", 1);
        dict.put("T", 1);
        dict.put("D", 2);
        dict.put("G", 2);
        dict.put("B", 3);
        dict.put("C", 3);
        dict.put("M", 3);
        dict.put("P", 3);
        dict.put("F", 4);
        dict.put("H", 4);
        dict.put("V", 4);
        dict.put("W", 4);
        dict.put("Y", 4);
        dict.put("K", 5);
        dict.put("J", 8);
        dict.put("X", 8);
        dict.put("Q", 10);
        dict.put("Z", 10);
        word = word.toUpperCase();
        for(int i = 0; i < word.length(); i++){
            String letters = String.valueOf(word.charAt(i));
            int points = dict.get(letters);
            sum += points;
        }
    }

    int getScore() {
        return sum;
    }

}
