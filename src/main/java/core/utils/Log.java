package core.utils;

import org.apache.log4j.Logger;

public class Log {
    public enum TextColor {
        ANSI_BLACK("\u001B[30m"),
        ANSI_RED("\u001B[31m"),
        ANSI_GREEN("\u001B[32m"),
        ANSI_YELLOW("\u001B[33m"),
        ANSI_BLUE("\u001B[34m"),
        ANSI_PURPLE("\u001B[35m"),
        ANSI_CYAN("\u001B[36m"),
        ANSI_WHITE("\u001B[37m");

        private String color;

        public String getColor() {
            return this.color;
        }

        TextColor(String color) {
            this.color = color;
        }
    }

    //Initialize Log4j instance
    private static Logger log = Logger.getLogger(Log.class.getName());

    //We can use it when starting tests
    public static void startLog(String testClassName) {
        log.info(testClassName + " Test is Starting...");
    }

    //We can use it when ending tests
    public static void endLog(String testClassName) {
        log.info(testClassName + " Test is Ending...");
    }

    //Info Level Logs
    public static void info(String message) {
        log.info(message);
    }

    //Warn Level Logs
    public static void warn(String message) {
        log.warn(message);
    }

    //Error Level Logs
    public static void error(String message) {
        log.error(message);
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        log.fatal(message);
    }

    //Debug Level Logs
    public static void debug(String message) {
        log.debug(message);
    }
}
