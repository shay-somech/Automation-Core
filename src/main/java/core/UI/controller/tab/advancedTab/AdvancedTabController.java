package core.UI.controller.tab.advancedTab;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.testng.xml.XmlSuite.ParallelMode;

import java.net.URL;
import java.util.ResourceBundle;

import static core.UI.controller.main.MainView.UiSelections.APP;
import static core.UI.controller.main.MainView.uiSelection;

public class AdvancedTabController implements AdvancedTabContract.View, Initializable {

    @FXML
    private Accordion accordion;
    @FXML
    private TitledPane testConfig, testNGConfig;
    @FXML
    private RadioButton singleRun, parallelRun;
    @FXML
    private CheckBox videoRecord, installApp;
    @FXML
    private TextField testNGSuiteName, testNGTestName, testNGDeviceName;
    @FXML
    private ComboBox<String> testClassComboBox, appComboBox;
    @FXML
    private ComboBox<ParallelMode> testNGParallel;

    private AdvancedTabContract.Presenter advancedPresenter;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        advancedPresenter = new AdvancedTabPresenter(this);
        advancedPresenter.initComboBoxesDefaults();
        advancedPresenter.initTestComboBox();
        advancedPresenter.handleVideoRecordSelection();
    }

    @Override
    public void setTestNGComboBoxDefault() {
        testNGParallel.setValue(ParallelMode.NONE);
    }

    @Override
    public void updateTestsClassesComboBox() {
        testClassComboBox.getItems().addAll(advancedPresenter.getAllTestClasses());
    }

    @Override
    public void setAppComboBoxVisibility(boolean visibility) {
        appComboBox.setVisible(visibility);
    }

    @Override
    public boolean isVideoRecordSelected() {
        return videoRecord.isSelected();
    }

    @Override
    public boolean isInstallAppSelected() {
        return installApp.isSelected();
    }

    @Override
    public void clearAppComboBoxItems() {
        appComboBox.getItems().clear();
    }

    @Override
    public void setAppComboBoxApks() {
        appComboBox.getItems().addAll("getAvailableAPKs");
    }

    @Override
    public void setAppComboBoxIpas() {
        appComboBox.getItems().addAll("getAvailableIPAs");
    }

    @Override
    public void updateSelections() {
        uiSelection.put(APP, appComboBox.getValue());
    }
}
