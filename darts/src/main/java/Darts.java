class Darts {
    int score(double xOfDart, double yOfDart) {
        double distance = Math.sqrt(Math.pow(xOfDart, 2) + Math.pow(yOfDart, 2));
        int finalScore = 0;
        System.out.println(distance);
        if(distance < 1.1){
            finalScore = 10;
        } else if (distance >= 1.1 && distance <= 5) {
            finalScore = 5;
        } else if (distance > 5 && distance <= 10) {
            finalScore = 1;
        } else {
            finalScore = 0;
        }

        return finalScore;
    }
}
