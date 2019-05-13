package core.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    public enum TextColor {
        ANSI_BLACK,
        ANSI_RED,
        ANSI_GREEN,
        ANSI_YELLOW,
        ANSI_BLUE,
        ANSI_PURPLE,
        ANSI_CYAN;

        @Override
        public String toString() {
            switch (this) {
                case ANSI_BLACK:
                    return "\u001B[30m";

                case ANSI_RED:
                    return "\u001B[31m";

                case ANSI_GREEN:
                    return "\u001B[32m";

                case ANSI_YELLOW:
                    return "\u001B[33m";

                case ANSI_BLUE:
                    return "\u001B[34m";

                case ANSI_PURPLE:
                    return "\u001B[35m";

                case ANSI_CYAN:
                    return "\u001B[36m";

                default:
                    return "\u001B[37m";
            }
        }
    }

    //Initialize Log4j instance
    private static Logger log = LogManager.getLogger(Log.class.getName());

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

    public static void info(TextColor color, String message) {
        log.info(color.toString() + message);
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
