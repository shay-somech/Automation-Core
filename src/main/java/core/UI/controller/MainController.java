package core.UI.controller;

import core.UI.application.Main;
import core.UI.controller.tab.Tab1Controller;
import core.UI.controller.tab.Tab2Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;


public class MainController {
    @FXML
    private Tab1Controller tab1Controller;
    @FXML
    private Tab2Controller tab2Controller;


    @FXML
    public void initialize() {
        System.out.println("Launching Automation Interface ...");
        tab1Controller.init(this);
        tab2Controller.init(this);

        tab1Controller.setTestsComboBox();
        tab1Controller.setDevicesComboBox();
        tab1Controller.setDevicesComboBox2();

        tab2Controller.setParallelModeComboBox();
        tab2Controller.setComboBoxesDefaults();
    }

    @FXML
    private void runButtonClicked() {
        if (validateSelections()) {
            getTab1Selections();
            getTab2Selections();
            Main.getWindow().close();
        }
    }

    private void getTab1Selections() {
        tab1Controller.getTestClassNameSelection();
        tab1Controller.getPlatformSelection();
        tab1Controller.getPlatformSelection2();
        tab1Controller.getDeviceSelection();
        tab1Controller.getDeviceSelection2();
        tab1Controller.getAppSelection();
        tab1Controller.isInstallAppSelected();
        tab1Controller.isNoResetSelected();
        tab1Controller.isSingleRunSelected();
        tab1Controller.isParallelRunSelected();
    }

    private void getTab2Selections() {
        tab2Controller.getTestNGSuiteName();
        tab2Controller.getTestNGTestName();
        tab2Controller.getTestNGDeviceName();
        tab2Controller.getTestNGVerbose();
        tab2Controller.getTestNGThreadCount();
        tab2Controller.getParallelMode();
    }

    private boolean validateSelections() {
        if (tab1Controller.getTestClassNameSelection() == null || tab1Controller.getTestClassNameSelection().isEmpty()) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "No Test selected", "Please select a Test");
            return false;
        }

        if (tab1Controller.getPlatformSelection() == null || tab1Controller.getPlatformSelection().isEmpty()) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "No Platform selected", "Please set a Platform");
            return false;
        }

        if (tab1Controller.getDeviceSelection() == null || tab1Controller.getDeviceSelection().isEmpty()) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "No Device selected", "Please set a Device");
            return false;
        }

        if (tab1Controller.isDuplicateDevicesSet()) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "Duplicate Devices", "Duplicate Devices Selected, please set a different Device");
            return false;
        }

        return true;
    }
}
