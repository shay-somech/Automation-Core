package core.managers;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class MyLogger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";


    public static Logger logger = Logger.getLogger("WhereDoYouSeeMe?");
    private static Logger myLogger;


    public static Logger getLogger() {
        if (myLogger == null) {
            myLogger = Logger.getLogger("WhereDoYouSeeMe?-2");
            myLogger.setLevel(Level.DEBUG);

        }
        return logger;
    }

    public static void logSys(String message) {
        System.out.println(message);
    }

    public static void logSysInfo(String message) {
        System.out.println(ANSI_PURPLE + "\n" + message + ANSI_RESET);
    }
}
