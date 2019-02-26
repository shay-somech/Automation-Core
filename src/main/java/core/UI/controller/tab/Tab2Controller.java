package core.UI.controller.tab;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import core.UI.controller.MainController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.testng.xml.XmlSuite.ParallelMode;

import java.net.URL;
import java.util.ResourceBundle;

public class Tab2Controller implements Initializable {

    private MainController main;

    public static String suiteName;
    public static String testName;
    public static String deviceName;
    public static int verbose;
    public static int threadCount;
    public static ParallelMode parallelMode;

    @FXML
    private JFXTextField testNGSuiteName;
    @FXML
    private JFXTextField testNGTestName;
    @FXML
    private JFXTextField testNGDeviceName;
    @FXML
    private JFXComboBox<Integer> testNGVerbose;
    @FXML
    private JFXComboBox<Integer> testNGThreadCount;
    @FXML
    private JFXComboBox<ParallelMode> testNGParallel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setParallelModeComboBox();
        setComboBoxesDefaults();
    }

    private void setParallelModeComboBox() {
        testNGParallel.getItems().addAll(ParallelMode.NONE, ParallelMode.CLASSES, ParallelMode.TESTS, ParallelMode.METHODS, ParallelMode.INSTANCES);
        testNGParallel.setValue(ParallelMode.NONE);
    }

    private void setComboBoxesDefaults() {
        testNGVerbose.getItems().addAll(1, 2, 3, 4);
        testNGThreadCount.getItems().addAll(1, 2, 3, 4);

        testNGVerbose.setValue(0);
        testNGThreadCount.setValue(0);
    }

    public void getSelections() {
        suiteName = testNGSuiteName.getText();
        testName = testNGTestName.getText();
        deviceName = testNGDeviceName.getText();
        verbose = testNGVerbose.getValue();
        threadCount = testNGThreadCount.getValue();
        parallelMode = testNGParallel.getValue();
    }

    public void init(MainController mainController) {
        main = mainController;
    }
}
