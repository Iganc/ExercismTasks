import java.util.HashMap;
import java.util.List;
import java.util.Map;

class KindergartenGarden {

    private final Map<String, List<Plant>> studentPlants = new HashMap<>();

    KindergartenGarden(String garden) {
        String[] students = {"Alice", "Bob", "Charlie", "David", "Eve", "Fred", "Ginny", "Harriet", "Ileana", "Joseph", "Kincaid", "Larry"};
        String[] rows = garden.split("\n");
        int numStudents = Math.min(students.length, rows[0].length() / 2);

        for (int i = 0; i < numStudents; i++) {
            int index = i * 2;
            List<Plant> plants = List.of(
                    Plant.getPlant(rows[0].charAt(index)),
                    Plant.getPlant(rows[0].charAt(index + 1)),
                    Plant.getPlant(rows[1].charAt(index)),
                    Plant.getPlant(rows[1].charAt(index + 1))
            );
            studentPlants.put(students[i], plants);
        }
    }

    List<Plant> getPlantsOfStudent(String student) {
        return studentPlants.get(student);
    }
}
