public class Hamming {
    int distance = 0;
    public Hamming(String leftStrand, String rightStrand) {
        int leftLenght = leftStrand.length();
        int rightLenght = rightStrand.length();
        if(leftLenght != rightLenght){
            throw new IllegalArgumentException("strands must be of equal length");
        }
        char[] leftstr = leftStrand.toCharArray();
        char[] rightstr = rightStrand.toCharArray();
        for(int i=0; i<leftLenght; i++){
            if(leftstr[i] != rightstr[i]){
                distance++;
            }
        }
    }

    public int getHammingDistance() {
        return distance;
    }
}
