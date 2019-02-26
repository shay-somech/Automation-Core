package core.UI.controller.tab;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import core.UI.controller.AlertBoxController;
import core.UI.controller.MainController;
import core.utils.AndroidHelper;
import core.utils.IOSHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Tab1Controller implements Initializable {

    private MainController main;

    public static String testClassName;
    public static String platform;
    public static String platform2;
    public static String device;
    public static String device2;
    public static String app;
    public static boolean isSingleRun;
    public static boolean isParallelRun;
    public static boolean isNoReset;
    public static boolean isInstallApp;
    public static boolean isGenerateReports;

    @FXML
    private JFXRadioButton singleRun;
    @FXML
    private JFXRadioButton parallelRun;
    @FXML
    private JFXComboBox<String> testClassComboBox;
    @FXML
    private JFXComboBox<String> platformComboBox;
    @FXML
    private JFXComboBox<String> platformComboBox2;
    @FXML
    private JFXComboBox<String> deviceComboBox;
    @FXML
    private JFXComboBox<String> deviceComboBox2;
    @FXML
    private JFXComboBox<String> appComboBox;
    @FXML
    private JFXCheckBox noReset;
    @FXML
    private JFXCheckBox installApp;
    @FXML
    private JFXCheckBox generateReport;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setTestsComboBox();
        setPlatformComboBoxesValues();
        setDevicesComboBox();
        setDevicesComboBox2();
    }

    public void init(MainController mainController) {
        main = mainController;
    }

    @FXML
    private void setSingleRunSelection(ActionEvent event) {
        System.out.println("Executing a Single Run");
        platformComboBox2.setVisible(false);
        deviceComboBox2.setVisible(false);
    }

    @FXML
    private void setParallelRunSelection(ActionEvent event) {
        System.out.println("Executing a Parallel Run");
        platformComboBox2.setVisible(true);
        deviceComboBox2.setVisible(true);
    }

    @FXML
    private void setAppInstallationComboBox(ActionEvent event) {
        if (platformComboBox.getSelectionModel().isEmpty()) {
            new AlertBoxController().alertBox(Alert.AlertType.ERROR, "App setup error", "Please set Platform type before selecting App to install");
            installApp.setSelected(false);
        }

        if (installApp.isSelected()) {
            appComboBox.setVisible(true);
            handleAppInstallation();
        } else {
            appComboBox.setVisible(false);
        }
    }

    @FXML
    private void setGenerateReport(ActionEvent event) {
        isGenerateReports = generateReport.isSelected();
    }

    private void setTestsComboBox() {
        File baseDirectory = new File(System.getProperty("user.dir") + "/src/test/java");
        String packagePath = System.getProperty("user.dir") + "/Tests";
        testClassComboBox.getItems().addAll(getAllTestClasses(baseDirectory, packagePath));
    }

    private void setPlatformComboBoxesValues() {
        platformComboBox.getItems().addAll("Android", "iOS");
        platformComboBox2.getItems().addAll("Android", "iOS");
    }

    private void setDevicesComboBox() {
        setDeviceComboBoxSelection(platformComboBox, deviceComboBox);
    }

    private void setDevicesComboBox2() {
        setDeviceComboBoxSelection(platformComboBox2, deviceComboBox2);
    }

    private void setDeviceComboBoxSelection(ComboBox<String> platform, ComboBox<String> device) {
        platform.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                switch (oldValue) {
                    case "Android":
                        device.getItems().clear();
                        break;

                    case "iOS":
                        device.getItems().clear();
                        break;
                }
            }

            if (newValue != null) {
                switch (newValue) {
                    case "Android":
                        device.getItems().addAll(AndroidHelper.getAndroidDeviceWithDetails());
                        break;

                    case "iOS":
                        device.getItems().addAll(IOSHelper.getIOSDevicesWithDetails());
                        break;
                }
            }
        });
    }

    private void handleAppInstallation() {
        platformComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue != null) {
                switch (oldValue) {
                    case "Android":
                        appComboBox.getItems().clear();
                        break;

                    case "iOS":
                        appComboBox.getItems().clear();
                        break;
                }
            }

            if (newValue != null) {
                switch (newValue) {
                    case "Android":
                        appComboBox.getItems().addAll("getAvailableAPKs");
                        break;

                    case "iOS":
                        appComboBox.getItems().addAll("getAvailableIPAs");
                        break;
                }
            }
        });
    }

    public void getSelections() {
        testClassName = testClassComboBox.getValue();
        platform = platformComboBox.getValue();
        platform2 = platformComboBox2.getValue();

        if (deviceComboBox.getValue() != null) {
            device = deviceComboBox.getValue().substring(deviceComboBox.getValue().indexOf("||") + 3);
        }

        if (deviceComboBox2.getValue() != null) {
            device2 = deviceComboBox2.getValue().substring(deviceComboBox2.getValue().indexOf("||") + 3);
        }

        app = appComboBox.getValue();
        isNoReset = noReset.isSelected();
        isSingleRun = singleRun.isSelected();
        isParallelRun = parallelRun.isSelected();
        isInstallApp = installApp.isSelected();
    }

    public boolean isDuplicateDevicesSet() {
        return deviceComboBox.getValue().equals(deviceComboBox2.getValue());
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

    public String getPlatformSelection() {
        return platformComboBox.getValue();
    }

    public String getDeviceSelection() {
        return deviceComboBox.getValue();
    }
}