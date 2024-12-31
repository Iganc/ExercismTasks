class BirdWatcher {
    private final int[] birdsPerDay;

    public BirdWatcher(int[] birdsPerDay) {
        this.birdsPerDay = birdsPerDay.clone();
    }

    public int[] getLastWeek() {
        int[] lastWeekBirdCounts = new int[]{0, 2, 5, 3, 7, 8, 4};
        return lastWeekBirdCounts;
    }

    public int getToday() {
        return birdsPerDay[birdsPerDay.length - 1];
    }

    public void incrementTodaysCount() {
        int count = birdsPerDay[birdsPerDay.length - 1];
        birdsPerDay[birdsPerDay.length - 1] = count + 1;
    }

    public boolean hasDayWithoutBirds() {
        int length = birdsPerDay.length;
        for(int i = 0; i < length; i++){
            if (birdsPerDay[i] == 0){
                return true;
            }
        }
        return false;
    }

    public int getCountForFirstDays(int numberOfDays) {
        int sum = 0;
        if (numberOfDays > 7){
            numberOfDays = 7;
        }
        for (int i = 0; i < numberOfDays; i++) {
            sum += birdsPerDay[i];
        }
        return sum;
    }


    public int getBusyDays() {
        int length = birdsPerDay.length;
        int sum = 0;
        for(int i = 0; i < length; i++){
            if (birdsPerDay[i] >= 5){
                sum++;
            }
        }
        return sum;
    }
}
