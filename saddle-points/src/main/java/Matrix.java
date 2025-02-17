
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Matrix {
    List<List<Integer>> values;
    List<MatrixCoordinate> saddlePointsList = new ArrayList<>();

    Matrix(List<List<Integer>> values) {
        this.values = values;
        for (int i = 0; i < values.size(); i++) {
            for (int j = 0; j < values.get(i).size(); j++) {
                int current = values.get(i).get(j);
                boolean isMaxInRow = true;
                for (int k = 0; k < values.get(i).size(); k++) {
                    if (values.get(i).get(k) > current) {
                        isMaxInRow = false;
                        break;
                    }
                }

                boolean isMinInColumn = true;
                for (int k = 0; k < values.size(); k++) {
                    if (values.get(k).get(j) < current) {
                        isMinInColumn = false;
                        break;
                    }
                }

                if (isMaxInRow && isMinInColumn) {
                    saddlePointsList.add(new MatrixCoordinate(i + 1, j + 1)); // 1-based indexing
                }
            }
        }
    }

    Set<MatrixCoordinate> getSaddlePoints() {
        System.out.println(saddlePointsList);
        return new HashSet<>(saddlePointsList);
    }
}