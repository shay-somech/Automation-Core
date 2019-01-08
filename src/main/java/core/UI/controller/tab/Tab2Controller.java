package core.UI.controller.tab;

import core.UI.controller.MainController;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import org.testng.xml.XmlSuite.ParallelMode;

public class Tab2Controller {

    private MainController main;

    public static String suiteName;
    public static String testName;
    public static String deviceName;
    public static int verbose;
    public static int threadCount;
    public static ParallelMode parallelMode;

    @FXML
    private TextField testNGSuiteName;
    @FXML
    private TextField testNGTestName;
    @FXML
    private TextField testNGDeviceName;
    @FXML
    private ComboBox<Integer> testNGVerbose;
    @FXML
    private ComboBox<Integer> testNGThreadCount;
    @FXML
    private ComboBox<ParallelMode> testNGParallel;

    public void setParallelModeComboBox() {
        testNGParallel.getItems().addAll(ParallelMode.NONE, ParallelMode.CLASSES, ParallelMode.TESTS, ParallelMode.METHODS, ParallelMode.INSTANCES);
    }

    public void setComboBoxesDefaults() {
        testNGVerbose.setValue(0);
        testNGThreadCount.setValue(1);
        testNGParallel.setValue(ParallelMode.NONE);
    }

    public String getTestNGSuiteName() {
        return suiteName = testNGSuiteName.getText();
    }

    public String getTestNGTestName() {
        return testName = testNGTestName.getText();
    }

    public String getTestNGDeviceName() {
        return deviceName = testNGDeviceName.getText();
    }

    public int getTestNGVerbose() {
        return verbose = testNGVerbose.getValue();
    }

    public int getTestNGThreadCount() {
        return threadCount = testNGThreadCount.getValue();
    }

    public ParallelMode getParallelMode() {
        switch (testNGParallel.getValue()) {
            case NONE:
                return parallelMode = ParallelMode.NONE;

            case CLASSES:
                return parallelMode = ParallelMode.CLASSES;

            case TESTS:
                return parallelMode = ParallelMode.TESTS;

            case METHODS:
                return parallelMode = ParallelMode.METHODS;

            case INSTANCES:
                return parallelMode = ParallelMode.INSTANCES;

            default:
                return parallelMode = ParallelMode.NONE;
        }
    }

    public void init(MainController mainController) {
        main = mainController;
    }
}
