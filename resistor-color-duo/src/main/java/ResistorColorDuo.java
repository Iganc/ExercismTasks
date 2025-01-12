class ResistorColorDuo {
    int value(String[] colors) {
        int firstColor = 0, secondColor = 0;
        switch (colors[0]) {
            case "black":
                firstColor = 0;
                break;
            case "brown":
                firstColor = 1;
                break;
            case "red":
                firstColor = 2;
                break;
            case "orange":
                firstColor = 3;
                break;
            case "yellow":
                firstColor = 4;
                break;
            case "green":
                firstColor = 5;
                break;
            case "blue":
                firstColor = 6;
                break;
            case "violet":
                firstColor = 7;
                break;
            case "grey":
                firstColor = 8;
                break;
            case "white":
                firstColor = 9;
                break;
        }
        switch (colors[1]) {
            case "black":
                secondColor = 0;
                break;
            case "brown":
                secondColor = 1;
                break;
            case "red":
                secondColor = 2;
                break;
            case "orange":
                secondColor = 3;
                break;
            case "yellow":
                secondColor = 4;
                break;
            case "green":
                secondColor = 5;
                break;
            case "blue":
                secondColor = 6;
                break;
            case "violet":
                secondColor = 7;
                break;
            case "grey":
                secondColor = 8;
                break;
            case "white":
                secondColor = 9;
                break;
        }
        String value = String.valueOf(firstColor) + String.valueOf(secondColor);
        return Integer.parseInt(value);
    }
}
