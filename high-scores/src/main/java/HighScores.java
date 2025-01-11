import java.text.CollationElementIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

class HighScores {
    private List<Integer> highScores;
    public HighScores(List<Integer> highScores) {
        this.highScores = highScores;

    }

    List<Integer> scores() {
        return highScores;
    }

    Integer latest() {
        return highScores.getLast();
    }

    Integer personalBest() {
        int biggest = 0;
        for (int i = 0; i < highScores.size(); i++) {
            if (biggest < highScores.get(i)) {
                biggest = highScores.get(i);
            }
        }
        return biggest;
    }

    List<Integer> personalTopThree() {
        List<Integer> Sorted = new ArrayList<>(highScores);
        Collections.sort(Sorted, Collections.reverseOrder());
        return Sorted.subList(0, Math.min(3, Sorted.size()));
    }

}
