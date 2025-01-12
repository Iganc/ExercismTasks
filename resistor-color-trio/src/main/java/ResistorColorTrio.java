class ResistorColorTrio {
    String label(String[] colors) {
        int firstColor = 0, secondColor = 0, thirdColor = 0;
        firstColor = switch (colors[0]) {
            case "black" -> 0;
            case "brown" -> 1;
            case "red" -> 2;
            case "orange" -> 3;
            case "yellow" -> 4;
            case "green" -> 5;
            case "blue" -> 6;
            case "violet" -> 7;
            case "grey" -> 8;
            case "white" -> 9;
            default -> firstColor;
        };
        secondColor = switch (colors[1]) {
            case "black" -> 0;
            case "brown" -> 1;
            case "red" -> 2;
            case "orange" -> 3;
            case "yellow" -> 4;
            case "green" -> 5;
            case "blue" -> 6;
            case "violet" -> 7;
            case "grey" -> 8;
            case "white" -> 9;
            default -> secondColor;
        };
        thirdColor = switch (colors[2]) {
            case "black" -> 0;
            case "brown" -> 1;
            case "red" -> 2;
            case "orange" -> 3;
            case "yellow" -> 4;
            case "green" -> 5;
            case "blue" -> 6;
            case "violet" -> 7;
            case "grey" -> 8;
            case "white" -> 9;
            default -> thirdColor;
        };

        String strvalues = String.valueOf(firstColor) + String.valueOf(secondColor) + "0".repeat(thirdColor);
        long value = Long.parseLong(strvalues);

        if (value == 0) {
            return "0 ohms";
        } else if (value < 1000) {
            return value + " ohms";
        } else if (value < 1000000) {
            return (value / 1000) + " kiloohms";
        } else if (value < 1000000000) {
            return (value / 1000000) + " megaohms";
        } else {
            return (value / 1000000000) + " gigaohms";
        }
    }
}