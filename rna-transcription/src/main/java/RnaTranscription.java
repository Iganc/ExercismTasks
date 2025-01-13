class RnaTranscription {

    String transcribe(String dnaStrand) {
       char[] dnaArray = dnaStrand.toCharArray();
       char[] finalArray = new char[dnaArray.length];

        for (int i = 0; i < dnaArray.length; i++){
            switch (dnaArray[i]){
                case 'G':
                    finalArray[i] = 'C';
                    break;
                case 'C':
                    finalArray[i] = 'G';
                    break;
                case 'T':
                    finalArray[i] = 'A';
                    break;
                case 'A':
                    finalArray[i] = 'U';
                    break;
            }
        }
        return new String(finalArray);
    }

}
