class RotationalCipher {
int shift;
StringBuilder sb = new StringBuilder();

    String rotate(String data) {
        for(int i = 0; i < data.length(); i++){
            char ch = data.charAt(i);
            if(Character.isLetter(ch)){
                char base = Character.isUpperCase(ch) ? 'A' : 'a';
                ch = (char) ((ch-base+shift) % 26 + base);
            }
            sb.append(ch);
        }

        return sb.toString();
    }
    RotationalCipher(int shiftKey) {
        this.shift = shiftKey;
    }

}
