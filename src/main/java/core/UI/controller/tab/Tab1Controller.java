package core.UI.controller.tab;

import core.UI.controller.AlertBoxController;
import core.UI.controller.MainController;
import core.utils.AndroidHelper;
import core.utils.IOSHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;

import java.io.File;
import java.util.ArrayList;

public class Tab1Controller {

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

    @FXML
    private RadioButton singleRun;
    @FXML
    private RadioButton parallelRun;
    @FXML
    private ComboBox<String> testClassComboBox;
    @FXML
    private ComboBox<String> platformComboBox;
    @FXML
    private ComboBox<String> platformComboBox2;
    @FXML
    private ComboBox<String> deviceComboBox;
    @FXML
    private ComboBox<String> deviceComboBox2;
    @FXML
    private ComboBox<String> appComboBox;
    @FXML
    private CheckBox noReset;
    @FXML
    private CheckBox installApp;


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

    public void setTestsComboBox() {
        File baseDirectory = new File(System.getProperty("user.dir") + "/src/test/java");
        String packagePath = System.getProperty("user.dir") + "/Tests";
        testClassComboBox.getItems().addAll(getAllTestClasses(baseDirectory, packagePath));
    }

    public void setDevicesComboBox() {
        setDeviceComboBoxSelection(platformComboBox, deviceComboBox);
    }

    public void setDevicesComboBox2() {
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

    public String getTestClassNameSelection() {
        return testClassName = testClassComboBox.getValue();
    }

    public String getPlatformSelection() {
        return platform = platformComboBox.getValue();
    }

    public String getPlatformSelection2() {
        return platform2 = platformComboBox2.getValue();
    }

    public String getDeviceSelection() {
        if (deviceComboBox.getValue() != null) {
            return device = deviceComboBox.getValue().substring(deviceComboBox.getValue().indexOf("||") + 3);
        } else {
            return null;
        }
    }

    public String getDeviceSelection2() {
        if (deviceComboBox2.getValue() != null) {
            return device2 = deviceComboBox2.getValue().substring(deviceComboBox2.getValue().indexOf("||") + 3);
        } else {
            return null;
        }
    }

    public String getAppSelection() {
        return app = appComboBox.getValue();
    }

    public boolean isNoResetSelected() {
        return isNoReset = noReset.isSelected();
    }

    public boolean isSingleRunSelected() {
        return isSingleRun = singleRun.isSelected();
    }

    public boolean isParallelRunSelected() {
        return isParallelRun = parallelRun.isSelected();
    }

    public boolean isInstallAppSelected() {
        return isInstallApp = installApp.isSelected();
    }

    public boolean isDuplicateDevicesSet() {
        return deviceComboBox.getValue().equals(deviceComboBox2.getValue());
    }

    public void init(MainController mainController) {
        main = mainController;
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
}