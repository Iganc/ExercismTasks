import java.util.*;

class ProteinTranslator {

    List<String> translate(String rnaSequence) {
        Dictionary<Object, Object> dict = new Hashtable<>();
        dict.put("AUG", "Methionine");
        dict.put("UUU", "Phenylalanine");
        dict.put("UUC", "Phenylalanine");
        dict.put("UUA", "Leucine");
        dict.put("UUG", "Leucine");
        dict.put("UCU", "Serine");
        dict.put("UCC", "Serine");
        dict.put("UCA", "Serine");
        dict.put("UCG", "Serine");
        dict.put("UAU", "Tyrosine");
        dict.put("UAC", "Tyrosine");
        dict.put("UGU", "Cysteine");
        dict.put("UGC", "Cysteine");
        dict.put("UGG", "Tryptophan");
        dict.put("UAA", "STOP");
        dict.put("UAG", "STOP");
        dict.put("UGA", "STOP");

        if (rnaSequence.isEmpty()) {
            return Collections.emptyList();
        }

        String[] results = rnaSequence.split("(?<=\\G.{" + 3 + "})");
        List<String> proteins = new ArrayList<>();
        for(String codon : results){
            String protein = (String) dict.get(codon);
            if ("STOP".equals(protein)) {
                break;
            }
            if (protein == null) {
                throw new IllegalArgumentException("Invalid codon");
            }
            proteins.add(protein);
        }
        return proteins;
    }
}
