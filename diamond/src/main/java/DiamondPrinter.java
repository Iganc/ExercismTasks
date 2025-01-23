import java.util.List;

class DiamondPrinter {

    List<String> printToList(char a) {
        List<String> list = new java.util.ArrayList<>();
        int n = a - 'A'; 
        for (char x = 'A'; x <= a; ++x) {
            StringBuilder sb = new StringBuilder();
            int distance = a - x;
            sb.append(" ".repeat(distance));
            sb.append(x);
            if (x > 'A') {
                int middleSpaces = 2 * (x - 'A') - 1;
                sb.append(" ".repeat(middleSpaces));
                sb.append(x);
            }
            sb.append(" ".repeat(distance));
            list.add(sb.toString());
        }
        for (int i = list.size() - 2; i >= 0; --i) {
            list.add(list.get(i));
        }
        return list;
    }
}
