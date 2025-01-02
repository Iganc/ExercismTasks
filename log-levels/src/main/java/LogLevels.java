public class LogLevels {

    public static String message(String logLine) {
        String[] arr = logLine.split(":");
        return arr[1].trim();
    }

    public static String logLevel(String logLine) {
        String[] arr = logLine.split(":");
        return arr[0].replace("[", "").replace("]", "").toLowerCase().trim();

    }

    public static String reformat(String logLine) {
        String loglevel = logLevel(logLine);
        String message = message(logLine);
        return message + "("+loglevel+")";
    }
}
