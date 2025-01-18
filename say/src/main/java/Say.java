import java.util.Dictionary;
import java.util.Hashtable;

public class Say {

    public String say(long number) {
        Dictionary<Long, String> dict = new Hashtable<>();
        dict.put(0L, "zero");
        dict.put(1L, "one");
        dict.put(2L, "two");
        dict.put(3L, "three");
        dict.put(4L, "four");
        dict.put(5L, "five");
        dict.put(6L, "six");
        dict.put(7L, "seven");
        dict.put(8L, "eight");
        dict.put(9L, "nine");
        dict.put(10L, "ten");
        dict.put(11L, "eleven");
        dict.put(12L, "twelve");
        dict.put(13L, "thirteen");
        dict.put(14L, "fourteen");
        dict.put(15L, "fifteen");
        dict.put(16L, "sixteen");
        dict.put(17L, "seventeen");
        dict.put(18L, "eighteen");
        dict.put(19L, "nineteen");
        dict.put(20L, "twenty");
        dict.put(30L, "thirty");
        dict.put(40L, "forty");
        dict.put(50L, "fifty");
        dict.put(60L, "sixty");
        dict.put(70L, "seventy");
        dict.put(80L, "eighty");
        dict.put(90L, "ninety");

        if (number == 0) {
            return dict.get(0L);
        }
        if (number < 0 || number >= 1_000_000_000_000L) {
            throw new IllegalArgumentException();
        }

        StringBuilder result = new StringBuilder();
        if (number >= 1_000_000_000) {
            long billions = number / 1_000_000_000;
            result.append(" ").append(say(billions)).append(" billion");
            number %= 1_000_000_000;
        }

        if (number >= 1_000_000) {
            long millions = number / 1_000_000;
            result.append(" ").append(say(millions)).append(" million");
            number %= 1_000_000;
        }

        if (number >= 1_000) {
            long thousands = number / 1_000;
            result.append(" ").append(say(thousands)).append(" thousand");
            number %= 1_000;
        }

        if (number >= 100) {
            long hundreds = number / 100;
            result.append(" ").append(say(hundreds)).append(" hundred");
            number %= 100;
        }

        if (number > 0) {
            if (number < 20) {
                result.append(" ").append(dict.get(number));
            } else {
                long tens = (number / 10) * 10;
                long units = number % 10;
                result.append(" ").append(dict.get(tens));
                if (units > 0) {
                    result.append("-").append(dict.get(units));
                }
            }
        }

        return result.toString().trim();
    }
}