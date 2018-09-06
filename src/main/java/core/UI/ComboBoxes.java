package core.UI;

import javafx.scene.control.ComboBox;

import java.util.ArrayList;

import static core.UI.UIExecutioner.*;
import static core.constants.PlatformType.ANDROID;
import static core.constants.PlatformType.IOS;
import static core.utils.AndroidHelper.getAvailableAPKs;
import static core.utils.IOSHelper.getAvailableIPAs;

public class ComboBoxes {

    static ComboBox<String> testRunTypeComboBox;
    public static ComboBox<String> selectTestToRunComboBox;
    public static ComboBox<String> selectDeviceComboBox;
    public static ComboBox<String> instrumentAppComboBox;
    public static ComboBox<String> noResetComboBox;
    public static ComboBox<String> selectPlatformComboBox;
    public static ComboBox<String> shouldInstallAppComboBox;
    public static ComboBox<String> selectAppToInstallComboBox;

    static void initChoiceBoxes() {
        testRunTypeComboBox = new ComboBox<>();
        selectTestToRunComboBox = new ComboBox<>();
        selectDeviceComboBox = new ComboBox<>();
        instrumentAppComboBox = new ComboBox<>();
        noResetComboBox = new ComboBox<>();
        selectPlatformComboBox = new ComboBox<>();
        shouldInstallAppComboBox = new ComboBox<>();
        selectAppToInstallComboBox = new ComboBox<>();
    }

    private static void setComboBoxesPromptText() {
        testRunTypeComboBox.setPromptText("Select Test Run Type :");
        selectTestToRunComboBox.setPromptText("Select Test to Run :");
        selectDeviceComboBox.setPromptText("Select Device :");
        instrumentAppComboBox.setPromptText("Instrument App :");
        noResetComboBox.setPromptText("Keep App Data :");
        selectPlatformComboBox.setPromptText("Select Platform :");
        shouldInstallAppComboBox.setPromptText("Install App :");
        selectAppToInstallComboBox.setPromptText("Select App to Install :");
    }

    static void getComboBoxes() {
        //getItems returns the ObservableList object which you can add items to
        selectPlatformComboBox.getItems().addAll(IOS, ANDROID);
        instrumentAppComboBox.getItems().addAll("true", "false");
        noResetComboBox.getItems().addAll("true", "false");
        testRunTypeComboBox.getItems().addAll("Single Run", "Suite");
        shouldInstallAppComboBox.getItems().addAll("true", "false");

        // Set a default value
        instrumentAppComboBox.setValue("true");
        noResetComboBox.setValue("true");
        shouldInstallAppComboBox.setValue("false");
    }

    static void platformChoiceBoxSelectionListener() {

        //Listen for selection changes in Platform Selection
        selectPlatformComboBox.setOnAction(event ->
        {
            selectPlatformComboBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> selectPlatformComboBox.getValue());
            String platform = selectPlatformComboBox.getValue();

            if (platform.equals(ANDROID)) {
                selectDeviceComboBox.getItems().clear();
                selectDeviceComboBox.getItems().addAll(getAvailableAndroidDevices());

                selectAppToInstallComboBox.getItems().clear();
                ArrayList apkList = getAvailableAPKs("/src/main/resources/");
                for (Object apk : apkList) {
                    selectAppToInstallComboBox.getItems().add(apk.toString());
                }

            } else {
                selectDeviceComboBox.getItems().clear();
                selectDeviceComboBox.getItems().addAll(getAvailableIOSDevices());

                selectAppToInstallComboBox.getItems().clear();
                ArrayList ipaList = getAvailableIPAs("/src/main/resources/");
                for (Object ipa : ipaList) {
                    selectAppToInstallComboBox.getItems().add(ipa.toString());
                }
            }
        });
    }

    static void testTypeChoiceBoxSelectionListener() {

        //Listen for selection changes in Test Type Selection and present the relevant information
        testRunTypeComboBox.setOnAction(event ->
        {
            testRunTypeComboBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> testRunTypeComboBox.getValue());
            String testRunType = testRunTypeComboBox.getValue();

            if (testRunType.equals("Single Run")) {
                selectTestToRunComboBox.getItems().clear();

                ArrayList classNames = getJavaClassNameByFolderPath(System.getProperty("user.dir") + "/src/test/java/");
                for (Object className : classNames) {
                    String name = className.toString();
                    selectTestToRunComboBox.getItems().add(name.substring(name.indexOf("java/"), name.indexOf(".java")).replace("java/", "").replace("/", "."));
                }

            } else if (testRunType.equals("Suite")) {
                selectTestToRunComboBox.getItems().clear();

                ArrayList xmlsNames = getXMLClassNameByFolderPath(System.getProperty("user.dir") + "/src/test/java/");
                for (Object xmlName : xmlsNames) {
                    selectTestToRunComboBox.getItems().add(xmlName.toString());
                }
            }
        });
    }

    static boolean isRequiredChoiceBoxEmpty() {
        return selectDeviceComboBox.getItems().isEmpty() || selectPlatformComboBox.getItems().isEmpty();
    }
}
