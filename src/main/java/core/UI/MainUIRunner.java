package core.UI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

import static core.UI.UIExecutioner.*;
import static core.configurationPopUp.AlertBox.display;
import static core.utils.AndroidHelper.getAvailableAPKs;
import static core.utils.IOSHelper.getAvailableIPAs;

public class MainUIRunner extends Application {

    private Stage window;
    private static final String Android = "Android";
    private static final String iOS = "iOS";

    private Label selectPlatformLabel = new Label();
    private Label selectDeviceLabel = new Label();
    private Label shouldInstallAppLabel = new Label();
    private Label selectAppToInstallLabel = new Label();
    private Label autoInstrumentLabel = new Label();
    private Label noResetLabel = new Label();
    private Label testRunTypeLabel = new Label();
    private Label selectTestListLabel = new Label();
    private ChoiceBox<String> testRunTypeChoiceBox;
    public static ChoiceBox<String> selectTestToRunChoiceBox;
    public static ChoiceBox<String> selectDeviceChoiceBox;
    public static ChoiceBox<String> instrumentAppChoiceBox;
    public static ChoiceBox<String> noResetChoiceBox;
    public static ChoiceBox<String> selectPlatformChoiceBox;
    public static ChoiceBox<String> shouldInstallAppChoiceBox;
    public static ChoiceBox<String> selectAppToInstallChoiceBox;

    public MainUIRunner() {
        initChoiceBoxes();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Please select Test Configuration");

        Button button = new Button();
        button.setText("Run");
        button.setFont(new Font("Times New Roman", 24));

        //Handles the close button events
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));
        layout.setBackground(new Background(new BackgroundFill(Color.MINTCREAM, null, null)));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Set Labels values
        setLabels();

        //getItems returns the ObservableList object which you can add items to
        selectPlatformChoiceBox.getItems().addAll(iOS, Android);
        instrumentAppChoiceBox.getItems().addAll("true", "false");
        noResetChoiceBox.getItems().addAll("true", "false");
        testRunTypeChoiceBox.getItems().addAll("Single Run", "Suite");
        shouldInstallAppChoiceBox.getItems().addAll("true", "false");

        //Set a default value
        instrumentAppChoiceBox.setValue("true");
        noResetChoiceBox.setValue("false");
        shouldInstallAppChoiceBox.setValue("false");

        //Listen for selection changes in Platform Selection
        selectPlatformChoiceBox.setOnAction(event -> {
            selectPlatformChoiceBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> selectPlatformChoiceBox.getValue());
            String platform = selectPlatformChoiceBox.getValue();

            if (platform.equals(Android)) {
                selectDeviceChoiceBox.getItems().clear();
                selectDeviceChoiceBox.getItems().addAll(getAvailableAndroidDevices());

                selectAppToInstallChoiceBox.getItems().clear();
                ArrayList apkList = getAvailableAPKs("/src/main/resources/");
                for (Object apk : apkList) {
                    selectAppToInstallChoiceBox.getItems().add(apk.toString());
                }

            } else {
                selectDeviceChoiceBox.getItems().clear();
                selectDeviceChoiceBox.getItems().addAll(getAvailableIOSDevices());

                selectAppToInstallChoiceBox.getItems().clear();
                ArrayList ipaList = getAvailableIPAs("/src/main/resources/");
                for (Object ipa : ipaList) {
                    selectAppToInstallChoiceBox.getItems().add(ipa.toString());
                }
            }
        });

        //Listen for selection changes in Test Type Selection and present the relevant information
        testRunTypeChoiceBox.setOnAction(event -> {
            testRunTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> testRunTypeChoiceBox.getValue());
            String testRunType = testRunTypeChoiceBox.getValue();

            if (testRunType.equals("Single Run")) {
                selectTestToRunChoiceBox.getItems().clear();

                ArrayList classNames = getJavaClassNameByFolderPath(System.getProperty("user.dir") + "/src/test/java/");
                for (Object className : classNames) {
                    String name = className.toString();
                    selectTestToRunChoiceBox.getItems().add(name.substring(name.indexOf("java/"), name.indexOf(".java")).replace("java/", "").replace("/", "."));
                }

            } else if (testRunType.equals("Suite")) {
                selectTestToRunChoiceBox.getItems().clear();

                ArrayList xmlsNames = getXMLClassNameByFolderPath(System.getProperty("user.dir") + "/src/test/java/");
                for (Object xmlName : xmlsNames) {
                    selectTestToRunChoiceBox.getItems().add(xmlName.toString());
                }
            }
        });

        button.setOnAction(event ->
        {
            if (!isRequiredFieldsEmpty()) {
                window.close();
            }
        });

        //Build layout
        layout.getChildren().add(testRunTypeLabel);
        layout.getChildren().add(testRunTypeChoiceBox);
        layout.getChildren().add(selectTestListLabel);
        layout.getChildren().add(selectTestToRunChoiceBox);
        layout.getChildren().add(selectPlatformLabel);
        layout.getChildren().add(selectPlatformChoiceBox);
        layout.getChildren().add(selectDeviceLabel);
        layout.getChildren().add(selectDeviceChoiceBox);
        layout.getChildren().add(shouldInstallAppLabel);
        layout.getChildren().add(shouldInstallAppChoiceBox);

        //Listen to shouldInstallApp choice box then Add\Remove App path accordingly (Based also on Platform type)
        shouldInstallAppChoiceBox.setOnAction(event -> {
            shouldInstallAppChoiceBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> shouldInstallAppChoiceBox.getValue());
            String shouldInstallApp = shouldInstallAppChoiceBox.getValue();
            if (shouldInstallApp.equals("true")) {
                layout.getChildren().add(selectAppToInstallLabel);
                layout.getChildren().add(selectAppToInstallChoiceBox);
            } else {
                layout.getChildren().remove(selectAppToInstallLabel);
                layout.getChildren().remove(selectAppToInstallChoiceBox);
            }
        });

        layout.getChildren().add(autoInstrumentLabel);
        layout.getChildren().add(instrumentAppChoiceBox);
        layout.getChildren().add(noResetLabel);
        layout.getChildren().add(noResetChoiceBox);
        layout.getChildren().add(button);

        Scene scene = new Scene(layout, 450, 700);
        window.setScene(scene);
        window.show();
    }

    private void initChoiceBoxes() {
        testRunTypeChoiceBox = new ChoiceBox<>();
        selectTestToRunChoiceBox = new ChoiceBox<>();
        selectDeviceChoiceBox = new ChoiceBox<>();
        instrumentAppChoiceBox = new ChoiceBox<>();
        noResetChoiceBox = new ChoiceBox<>();
        selectPlatformChoiceBox = new ChoiceBox<>();
        shouldInstallAppChoiceBox = new ChoiceBox<>();
        selectAppToInstallChoiceBox = new ChoiceBox<>();
    }

    private void createNewLabel(Label labelName, String text, Color labelColor, String font, int fontSize) {
        labelName.setText(text);
        labelName.setTextFill(labelColor);
        labelName.setFont(new Font(font, fontSize));
        GridPane.setConstraints(labelName, 0, 0);
    }

    private void setLabels() {
        Color labelColor = Color.BLACK;
        String font = "Times new roman";
        int fontSize = 20;

        createNewLabel(selectPlatformLabel, "Select Platform:", labelColor, font, fontSize);
        createNewLabel(selectDeviceLabel, "Select Device:", labelColor, font, fontSize);
        createNewLabel(autoInstrumentLabel, "Instrument App:", labelColor, font, fontSize);
        createNewLabel(noResetLabel, "Reset App:", labelColor, font, fontSize);
        createNewLabel(testRunTypeLabel, "Select Test Run Type:", labelColor, font, fontSize);
        createNewLabel(selectDeviceLabel, "Select Device:", labelColor, font, fontSize);
        createNewLabel(selectTestListLabel, "Select Test to Run:", labelColor, font, fontSize);
        createNewLabel(shouldInstallAppLabel, "Install Application ?", labelColor, font, fontSize);
        createNewLabel(selectAppToInstallLabel, "Select Application to install:", labelColor, font, fontSize);
    }

    private boolean isRequiredFieldsEmpty() {
        if (selectDeviceChoiceBox.getItems().isEmpty() || selectPlatformChoiceBox.getItems().isEmpty() || selectTestToRunChoiceBox.getItems().isEmpty() || testRunTypeChoiceBox.getItems().isEmpty()) {
            display("Configurations Not Set Properly", "Please set all required configurations");
            return true;
        }
        return false;
    }

    private void closeProgram() {
        boolean answer = QuitDialogBox.display("Quiting Automation", "This will terminate the Test Session\n\n Are you sure you want to quit? ");
        if (answer)
            window.close();
    }
}