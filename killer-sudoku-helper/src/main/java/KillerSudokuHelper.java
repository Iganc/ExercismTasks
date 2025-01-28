import java.util.ArrayList;
import java.util.List;

public class KillerSudokuHelper {

    public List<List<Integer>> combinationsInCage(int cageSum, int cageSize) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), cageSum, cageSize, 1);
        return result;
    }

    public List<List<Integer>> combinationsInCage(int cageSum, int cageSize, List<Integer> exclude) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), cageSum, cageSize, 1, exclude);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int remainingSum, int remainingSize, int start) {
        if (remainingSum < 0 || remainingSize < 0) return;
        if (remainingSum == 0 && remainingSize == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i <= 9; i++) {
            tempList.add(i);
            backtrack(result, tempList, remainingSum - i, remainingSize - 1, i + 1);
            tempList.remove(tempList.size() - 1);
        }
    }

    private void backtrack(List<List<Integer>> result, List<Integer> tempList, int remainingSum, int remainingSize, int start, List<Integer> exclude) {
        if (remainingSum < 0 || remainingSize < 0) return;
        if (remainingSum == 0 && remainingSize == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i = start; i <= 9; i++) {
            if (exclude.contains(i)) continue;
            tempList.add(i);
            backtrack(result, tempList, remainingSum - i, remainingSize - 1, i + 1, exclude);
            tempList.remove(tempList.size() - 1);
        }
    }
}