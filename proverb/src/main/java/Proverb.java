import java.util.ArrayList;
import java.util.List;

class Proverb {
    String first_half = "For want of a ";
    String the = " the ";
    String second_half = " was lost.";
    String last_sentence = "And all for the want of a ";
    List<String> tempList = new ArrayList<>();
    Proverb(String[] words) {
        if(words.length == 0){
            tempList.add("");
            return;
        }
        if(words.length == 1) {
            tempList.add(last_sentence + words[0]+ ".");
            return;
        }
        for(int i = 0; i < words.length - 1; i++){
            tempList.add(first_half + words[i] + the + words[i + 1] + second_half);
        }
        tempList.add(last_sentence + words[0] + ".");
    }

    String recite() {
        if(tempList == null || tempList.isEmpty()){
            return "";
        }
        StringBuilder result = new StringBuilder();
        for (String sentence : tempList) {
            result.append(sentence).append("\n");
        }
        return result.toString().trim();
    }
}
