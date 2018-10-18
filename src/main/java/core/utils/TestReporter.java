package core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestReporter {

    public static void presentAllureReports() {
        try {
            Log.info("Generating Allure test report ...");
            String projectDir = System.getProperties().get("user.dir").toString();
            Process generateReport = Runtime.getRuntime().exec("allure serve " + projectDir + "/allure-results");
            BufferedReader input = new BufferedReader(new InputStreamReader(generateReport.getInputStream()));
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: 10/10/2018 Find a way to shut this down gracefully
//    public static void presentAllureReports() throws IOException {
//
//        Log.info("Generating Allure test report ...");
//
//        String projectDir = System.getProperties().get("user.dir").toString();
//        String command = "allure serve " + projectDir + "/allure-results";
//
//        Process process = Runtime.getRuntime().exec(command);
//        // Read the output
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(process.getInputStream()));
//
//        String line;
//        while ((line = reader.readLine()) != null) {
//            Log.info(line + "\n");
//
//            if (line.contains("Press <Ctrl+C> to exit")) {
//                Runtime.getRuntime().addShutdownHook(new Thread(() -> Log.info("Exited!")));
//            }
//        }
//    }
}
