package core.UI.controller.tab.advancedTab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.testng.xml.XmlSuite.ParallelMode;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class AdvancedTabController implements Initializable {

    public static String testClassName, suiteName, testName, deviceName, app;
    public static boolean isSingleRun, isParallelRun, isInstallApp;
    public static int verbose, threadCount;
    public static ParallelMode parallelMode;

    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane testConfig, testNGConfig;
    @FXML
    private RadioButton singleRun, parallelRun;
    @FXML
    private CheckBox recordVideo, installApp;
    @FXML
    private TextField testNGSuiteName, testNGTestName, testNGDeviceName;
    @FXML
    private ComboBox<String> testClassComboBox, appComboBox;
    @FXML
    private ComboBox<Integer> testNGVerbose, testNGThreadCount;
    @FXML
    private ComboBox<ParallelMode> testNGParallel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setComboBoxesValues();
        handleVideoRecordSelection();
        accordion.setExpandedPane(testConfig);
    }

    private void setTestsComboBox() {
        File baseDirectory = new File(System.getProperty("user.dir") + "/src/test/java");
        String packagePath = System.getProperty("user.dir") + "/Tests";
        testClassComboBox.getItems().addAll(getAllTestClasses(baseDirectory, packagePath));
    }

    private void setComboBoxesValues() {
        testNGParallel.getItems().addAll(ParallelMode.NONE, ParallelMode.CLASSES, ParallelMode.TESTS, ParallelMode.METHODS, ParallelMode.INSTANCES);
        testNGVerbose.getItems().addAll(1, 2, 3, 4);
        testNGThreadCount.getItems().addAll(1, 2, 3, 4);

        setTestsComboBox();
    }

    private void setComboBoxesDefaults() {
        testNGParallel.setValue(ParallelMode.NONE);
        testNGVerbose.setValue(0);
        testNGThreadCount.setValue(0);
    }

    /**
     * Recursive method used to find all classes in a given baseDirectory and subdirs.
     *
     * @param baseDirectory The base directory
     * @param packageName   The package name for classes found inside the base baseDirectory
     * @return The classes
     */
    private ArrayList<String> getAllTestClasses(File baseDirectory, String packageName) {
        ArrayList<String> classes = new ArrayList<>();

        if (!baseDirectory.exists()) {
            return classes;
        }

        File[] files = baseDirectory.listFiles();

        assert files != null;

        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");

                classes.addAll(getAllTestClasses(file, file.getName()));

            } else {
                String cls = String.format("%s.%s", packageName, file.getName()).replace("/", ".").replace(".java", "");
                classes.add(cls);
            }
        }

        return classes;
    }

    @FXML
    private void handleAppInstallation() {
//        if (installApp.isSelected()) {
//            appComboBox.setVisible(true);
//            switch (getPlatform()) {
//                case ANDROID:
//                    appComboBox.getItems().clear();
//                    appComboBox.getItems().addAll("getAvailableAPKs");
//                    break;
//
//                case IOS:
//                    appComboBox.getItems().clear();
//                    appComboBox.getItems().addAll("getAvailableIPAs");
//                    break;
//
//                default:
//                    new AlertBoxController(Alert.AlertType.ERROR, "App Error", "Please select Platform before selecting App to install").showAlert();
//
//            }
//        } else {
//            appComboBox.setVisible(false);
//        }
    }

    private void handleVideoRecordSelection() {
//        if (recordVideo.isSelected()) {
//            Log.info("Starting Screen Recording");
//            ScreenRecorderHelper screenRecorder = new ScreenRecorderHelper();
//
//            switch (getPlatform()) {
//                case ANDROID:
//                    screenRecorder.startRecording(screenRecorder.androidRecordingOptions().withTimeLimit(Duration.ofSeconds(120)));
//                    break;
//
//                case IOS:
//                    screenRecorder.startRecording(screenRecorder.iOSRecordingOptions().withTimeLimit(Duration.ofSeconds(120)));
//                    break;
//            }
//        }
    }

    public void getSelections() {
        if (testClassName != null) {
            testClassName = testClassComboBox.getValue();
            suiteName = testNGSuiteName.getText();
            testName = testNGTestName.getText();
            deviceName = testNGDeviceName.getText();
            verbose = testNGVerbose.getValue();
            threadCount = testNGThreadCount.getValue();
            parallelMode = testNGParallel.getValue();
            app = appComboBox.getValue();
            isSingleRun = singleRun.isSelected();
            isParallelRun = parallelRun.isSelected();
            isInstallApp = installApp.isSelected();
        }
    }
}
