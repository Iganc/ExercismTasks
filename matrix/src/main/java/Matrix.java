import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Matrix {
    String[] rows;
    Matrix(String matrixAsString) {
        rows = matrixAsString.split("\n");
    }

    int[] getRow(int rowNumber) {
        String[] row = rows[rowNumber - 1].split(" ");
        int[] result = new int[row.length];
        for(int i = 0; i < row.length; i++){
            result[i] = Integer.parseInt(row[i]);
        }
        return result;
    }

    int[] getColumn(int columnNumber) {
        String[] column = new String[rows.length];
        for(int i = 0; i < rows.length; i++){
            column[i] = rows[i].split(" ")[columnNumber - 1];
        }
        int[] result = new int[column.length];
        for(int i = 0; i < column.length; i++){
            result[i] = Integer.parseInt(column[i]);
        }
        return result;
    }
}
