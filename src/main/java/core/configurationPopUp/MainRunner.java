package core.configurationPopUp;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

import static core.configurationPopUp.DropDownExecutioner.*;

public class MainRunner extends Application {

    private Stage window;
    private static final String Android = "Android";
    private static final String iOS = "iOS";

    private Label selectPlatformLabel;
    private Label selectDeviceLabel;
    private Label instrumentAppLabel;
    private Label noResetLabel;
    private Label testRunTypeLabel;
    private Label selectTestListLabel;
    private ChoiceBox<String> selectPlatformChoiceBox;
    private ChoiceBox<String> selectDeviceChoiceBox;
    private ChoiceBox<String> instrumentAppChoiceBox;
    private ChoiceBox<String> noResetChoiceBox;
    private ChoiceBox<String> testRunTypeChoiceBox;
    private ChoiceBox<String> selectTestToRunChoiceBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Please select Test Configuration");
        Button button = new Button("Run");

        //Handles the close button events
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20, 20, 20, 20));

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Labels
        setLabels();

        //ChoiceBoxes
        setChoiceBoxes();

        //getItems returns the ObservableList object which you can add items to
        selectPlatformChoiceBox.getItems().addAll(iOS, Android);
        instrumentAppChoiceBox.getItems().addAll("true", "false");
        noResetChoiceBox.getItems().addAll("true", "false");
        testRunTypeChoiceBox.getItems().addAll("Single Run", "Suite");

        //Set a default value
        instrumentAppChoiceBox.setValue("true");
        noResetChoiceBox.setValue("false");

        //Listen for selection changes in Platform Selection
        selectPlatformChoiceBox.setOnAction(event -> {
            selectPlatformChoiceBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> selectPlatformChoiceBox.getValue());
            String platform = selectPlatformChoiceBox.getValue();

            if (platform.equals(Android)) {
                selectDeviceChoiceBox.getItems().clear();
                selectDeviceChoiceBox.getItems().addAll(getAvailableAndroidDevices());
            } else {
                selectDeviceChoiceBox.getItems().clear();
                selectDeviceChoiceBox.getItems().addAll(getAvailableIOSDevices());
            }
        });

        //Listen for selection changes in Test Type Selection and present the relevant information
        testRunTypeChoiceBox.setOnAction(event -> {
            testRunTypeChoiceBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> testRunTypeChoiceBox.getValue());
            String testRunType = testRunTypeChoiceBox.getValue();

            if (testRunType.equals("Single Run")) {
                selectTestToRunChoiceBox.getItems().clear();

                ArrayList classNames = getJavaClassNameByFolderPath("/Users/automationstation/Evrit/src/test/java");
                for (Object className : classNames) {
                    String name = className.toString();
                    selectTestToRunChoiceBox.getItems().add(name.substring(name.indexOf("java/"), name.indexOf(".java")).replace("java/", "").replace("/", "."));
                }

            } else if (testRunType.equals("Suite")) {
                selectTestToRunChoiceBox.getItems().clear();

                ArrayList xmlsNames = getXMLClassNameByFolderPath("/Users/automationstation/Evrit/src/test/java");
                for (Object xmlName : xmlsNames) {
                    selectTestToRunChoiceBox.getItems().add(xmlName.toString());
                }
            }
        });

        button.setOnAction(event ->
        {
            if (!isRequiredFieldsEmpty()) {
                instrumentApp(instrumentAppChoiceBox);
                noReset(noResetChoiceBox);
                selectDevice(selectDeviceChoiceBox);
                setPlatform(selectPlatformChoiceBox);
//                runTestNGSuite(selectTestToRunChoiceBox.getSelectionModel().getSelectedItem());
                runTestNGSuite("test.java.libraryTests.PositiveLoginTest.java");
                window.close();
            }
        });

        layout.getChildren().addAll(testRunTypeLabel, testRunTypeChoiceBox, selectTestListLabel, selectTestToRunChoiceBox, selectPlatformLabel, selectPlatformChoiceBox, selectDeviceLabel, selectDeviceChoiceBox, instrumentAppLabel, instrumentAppChoiceBox, noResetLabel, noResetChoiceBox, button);

        Scene scene = new Scene(layout, 350, 450);
        window.setScene(scene);
        window.show();
    }

    private void setLabels() {
        selectPlatformLabel = new Label("Select Platform:");
        GridPane.setConstraints(selectPlatformLabel, 0, 0);

        selectDeviceLabel = new Label("Select Device:");
        GridPane.setConstraints(selectDeviceLabel, 0, 0);

        instrumentAppLabel = new Label("Instrument App:");
        GridPane.setConstraints(instrumentAppLabel, 0, 0);

        noResetLabel = new Label("Reset App:");
        GridPane.setConstraints(noResetLabel, 0, 0);

        testRunTypeLabel = new Label("Select Test Run Type:");
        GridPane.setConstraints(testRunTypeLabel, 0, 0);

        selectTestListLabel = new Label("Select Test to Run:");
        GridPane.setConstraints(selectTestListLabel, 0, 0);
    }

    private void setChoiceBoxes() {
        selectPlatformChoiceBox = new ChoiceBox<>();
        selectDeviceChoiceBox = new ChoiceBox<>();
        instrumentAppChoiceBox = new ChoiceBox<>();
        noResetChoiceBox = new ChoiceBox<>();
        testRunTypeChoiceBox = new ChoiceBox<>();
        selectTestToRunChoiceBox = new ChoiceBox<>();
    }

    private boolean isRequiredFieldsEmpty() {
        if (selectDeviceChoiceBox.getItems().isEmpty() || selectPlatformChoiceBox.getItems().isEmpty() || selectTestToRunChoiceBox.getItems().isEmpty() || testRunTypeChoiceBox.getItems().isEmpty()) {
            AlertBox.display("Configurations Not Set", "Please set all required configurations");
            return true;
        }
        return false;
    }

    private void closeProgram() {
        boolean answer = core.configurationPopUp.QuitDialogBox.display("Quiting Automation", "This will terminate the Test Session\n\n Are you sure you want to quit? ");
        if (answer)
            window.close();
    }
}