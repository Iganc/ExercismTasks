class Acronym {
    StringBuilder strb = new StringBuilder();
    Acronym(String phrase) {
        String[] whole = phrase.split("[\\s-_]+");
        for(int i = 0; i < whole.length; i++){
            String letters = String.valueOf(whole[i].charAt(0));
            strb.append(letters);
        }

    }

    String get() {
        String strfinal = strb.toString().toUpperCase();
        return strfinal;
    }

}
