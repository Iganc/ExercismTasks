

public class LogLine {
    private String logLine;
    public LogLine(String logLine) {
        this.logLine = logLine;
    }

    public LogLevel getLogLevel() {
        String[] arr = logLine.split(":");
        String level = arr[0];
        switch(level){
            case "[TRC]":
                return LogLevel.TRACE;
            case "[DBG]":
                return LogLevel.DEBUG;
            case "[INF]":
                return LogLevel.INFO;
            case "[WRN]":
                return LogLevel.WARNING;
            case "[ERR]":
                return LogLevel.ERROR;
            case "[FTL]":
                return LogLevel.FATAL;
            default:
                return LogLevel.UNKNOWN;

        }
    }

    public String getOutputForShortLog() {
        String[] arr = logLine.split(":");
        String level = arr[0];
        String msg = arr[1].trim();

        switch(level){
            case "[TRC]":
                return "1:"+ msg;
            case "[DBG]":
                return "2:"+msg;
            case "[INF]":
                return "4:"+msg;
            case "[WRN]":
                return "5:"+msg;
            case "[ERR]":
                return "6:"+msg;
            case "[FTL]":
                return "42:"+msg;
            default:
                return "0:"+msg;

        }
    }
}
