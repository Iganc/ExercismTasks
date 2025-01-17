public class EliudsEggs {
    public int eggCount(int number) {
        int ones = 0;
        while (number > 0) {
            if(number%2==1){
                ones++;
            }
            number /= 2 ;
        }
        return ones;
    }
}
