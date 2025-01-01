// import java.util.Dictionary;
// import java.util.Hashtable;

// class SqueakyClean {
//     static String clean(String identifier) {
//         identifier = identifier.replace(' ', '_');

//         String[] parts = identifier.split("-");
//         for (int i = 1; i < parts.length; i++) {
//             if (!parts[i].isEmpty()) {
//                 parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1);
//             }
//         }

//         Dictionary<String, String> dict= new Hashtable<>();
//         dict.put("4", "a");
//         dict.put("3", "e");
//         dict.put("0", "o");
//         dict.put("1", "l");
//         dict.put("7", "t");
        
//         StringBuilder result = new StringBuilder();
//         for (char c : String.join("", parts).toCharArray()) {
//             String replacement = dict.get(String.valueOf(c));
//             if (replacement != null) {
//                 result.append(replacement);
//             } else {
//                 result.append(c);
//             }
//         }
        
//         return String.join("", parts);

//     }
// }

import java.util.Dictionary;
import java.util.Hashtable;

class SqueakyClean {
    static String clean(String identifier) {
        identifier = identifier.replace(' ', '_');

        String[] parts = identifier.split("-");
        for (int i = 1; i < parts.length; i++) {
            if (!parts[i].isEmpty()) {
                parts[i] = Character.toUpperCase(parts[i].charAt(0)) + parts[i].substring(1);
            }
        }

        Dictionary<String, String> dict = new Hashtable<>();
        dict.put("4", "a");
        dict.put("3", "e");
        dict.put("0", "o");
        dict.put("1", "l");
        dict.put("7", "t");
        dict.put("¡", "");
        dict.put("!", "");
        dict.put("$", "");
        dict.put("#", "");
        dict.put(".", "");
        dict.put(",", "");
        StringBuilder result = new StringBuilder();
        for (char c : String.join("", parts).toCharArray()) {
            String replacement = dict.get(String.valueOf(c));
            if (replacement != null) {
                result.append(replacement);
            } else {
                result.append(c);
            }
        }
        
        return result.toString().trim();
    }
}
