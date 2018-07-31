package core.managers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class AppiumServerManager {

    static String APPIUM_COMMAND = "appium";

//    /**
//     * Setup method in Base Driver that executes before tests
//     */
//    public static void setUp() throws Exception {
//        // Initialize configuration, objects, driver... set capabilities.
//        startAppiumServer("4273");
//    }
//
//    /**
//     * Teardown method in Base Driver that executes before tests, to clean up.
//     */
//    public static void tearDown() throws Exception {
//        // quit driver, cleanup code.
//        stopAppiumServer("4273");
//    }


    /**
     * To start Appium server
     *
     * @param port - port number on which the server starts
     * @throws Exception
     */
    public static void startAppiumServer(String port) throws Exception {
        MyLogger.logSys(String.format("Starting Appium server on port %s", port));

        // Check if Appium server already up and running
        if (!isAppiumServerRunning(port)) {
            // command to start Appium server --> appium -p 4273
            String completeAppiumCommand = String.format("%s -p %s", APPIUM_COMMAND, port);
            MyLogger.logSys("Starting Server");
            try {
                MyLogger.logSys("Appium server started with version: " + runCMD(completeAppiumCommand));
            } catch (Exception serverNotStarted) {
                MyLogger.logSys("Could not start Appium Server");
                throw new Exception(serverNotStarted.getMessage());
            }
        } else {
            MyLogger.logSys("Appium server already started");
        }
    }


    /**
     * To check if Appium server is already up and running on the desired port
     *
     * @param port desired port for server to start
     * @return true if server running, else false.
     * @throws Exception
     */
    private static boolean isAppiumServerRunning(String port) throws Exception {
        MyLogger.logSys(String.format("Checking if Appium server is executing on port %s", port));

        // command to check if Appium service running on port --> sh -c lsof -P | grep ':4723'
        String checkCommand[] = new String[]{"sh", "-c", String.format("lsof -P | grep :%s", port)};
        if (runCommandAndWaitToComplete(checkCommand).equals("")) {
            MyLogger.logSys(String.format("Appium server is not running on port %s", port));
            return false;
        } else {
            MyLogger.logSys(String.format("Appium server is running on port %s", port));
            return true;
        }
    }

    /**
     * To stop appium server
     *
     * @param port desired port for server to stop
     * @throws Exception
     */
    public static void stopAppiumServer(String port) throws Exception {
        MyLogger.logSys(String.format("Stopping Appium server on port %s", port));

        // command to stop Appium service running on port --> sh -c lsof -P | grep ':4723' | awk '{print $2}' | xargs kill -9
        String stopCommand[] = new String[]{"sh", "-c", String.format("lsof -P | grep ':%s' | awk '{print $2}' | xargs kill -9", port)};
        runCommandAndWaitToComplete(stopCommand);
    }

    /**
     * To execute a terminal command, and get the complete log response.
     *
     * @param command - command we intend to execute via terminal
     * @return - the execution log. We can scan this to check if the command executed was a success or failure.
     * @throws Exception
     */
    public static String runCommandAndWaitToComplete(String[] command) throws Exception {
        String completeCommand = String.join(" ", command);
        MyLogger.logSys("Executing command: " + completeCommand);
        String line;
        String returnValue = "";

        try {
            Process processCommand = Runtime.getRuntime().exec(command);
            BufferedReader response = new BufferedReader(new InputStreamReader(processCommand.getInputStream()));

            try {
                processCommand.waitFor();
            } catch (InterruptedException commandInterrupted) {
                throw new Exception("Were waiting for process to end but something interrupted it" + commandInterrupted.getMessage());
            }

            while ((line = response.readLine()) != null) {
                returnValue = returnValue + line + "\n";
            }

            response.close();

        } catch (Exception e) {
            throw new Exception("Unable to run command: " + completeCommand + ". Error: " + e.getMessage());
        }

        MyLogger.logSys("Response : runCMDAndWaitToComplete(" + completeCommand + ") : " + returnValue);
        return returnValue;
    }

    /**
     * Helper method to run an arbitrary command-line 'command', waits for few seconds after command executes
     *
     * @param command string that will be sent to command-line
     * @return The first line response after executing command. (can be used to verify)
     */
    public static String runCMD(String command) throws Exception {
        MyLogger.logSys("Executing command: " + command);
        try {
            Process process = Runtime.getRuntime().exec((command));
            process.waitFor(10, TimeUnit.SECONDS);
            BufferedReader response = new BufferedReader(new InputStreamReader(process.getInputStream()));
            return response.readLine();
        } catch (Exception e) {
            MyLogger.logSys("Unable to run command: " + command + ". Error: " + e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}