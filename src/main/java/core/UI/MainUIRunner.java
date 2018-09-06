package core.UI;

import core.managers.MyLogger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import static core.UI.Buttons.setCustomButton;
import static core.UI.Buttons.setRunButtonActions;
import static core.UI.ComboBoxes.*;
import static core.UI.Labels.*;

public class MainUIRunner extends Application {

    private VBox labelsVBox;
    private VBox comboBoxesVBox;
    private HBox buttonHBox;
    static Stage window;
    private Button runButton = setCustomButton("RUN", 24);
    private ObservableList<Label> labelsList = FXCollections.observableArrayList();
    private ObservableList<ComboBox> comboBoxesList = FXCollections.observableArrayList();

    public MainUIRunner() {
        initLabels();
        initChoiceBoxes();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        window = primaryStage;
        window.setTitle("Please select Test Configuration");

        labelsVBox = new VBox();
        comboBoxesVBox = new VBox();
        buttonHBox = new HBox();
        BorderPane borderPane = new BorderPane();

        borderPane.setLeft(labelsVBox);
        borderPane.setCenter(comboBoxesVBox);
        borderPane.setBottom(buttonHBox);
        borderPane.setPadding(new Insets(10));

        buildLayout();

        Scene scene = new Scene(borderPane, 550, 300);
        window.setScene(scene);
        window.show();
    }

    private void buildLayout() {
        labelsVBox.setPadding(new Insets(10));
        labelsVBox.setSpacing(10);

        comboBoxesVBox.setPadding(new Insets(10));
        comboBoxesVBox.setSpacing(10);

        getMainLabels();
        getComboBoxes();

        platformChoiceBoxSelectionListener();
        testTypeChoiceBoxSelectionListener();

        labelsList.addAll(selectPlatformLabel, selectDeviceLabel, noResetLabel, shouldInstallAppLabel);
        comboBoxesList.addAll(selectPlatformComboBox, selectDeviceComboBox, noResetComboBox, shouldInstallAppComboBox);

        shouldInstallAppComboBox.setOnAction(event ->
        {
            shouldInstallAppComboBox.getSelectionModel().selectedItemProperty().addListener((currentValue, oldValue, newValue) -> shouldInstallAppComboBox.getValue());
            String shouldInstallApp = shouldInstallAppComboBox.getValue();

            if (shouldInstallApp.equals("true")) {
                MyLogger.logSys("true");
                labelsList.add(selectAppToInstallLabel);
                comboBoxesList.add(selectAppToInstallComboBox);
            }
        });

        labelsVBox.getChildren().addAll(labelsList);
        comboBoxesVBox.getChildren().addAll(comboBoxesList);
        buttonHBox.getChildren().add(runButton);

        //Handles the close button events
        window.setOnCloseRequest(e -> {
            e.consume();
            closeProgram();
        });

        setRunButtonActions(runButton);
    }

    private void closeProgram() {
        boolean answer = QuitDialogBox.display("Quiting Automation", "This will terminate the Test Session\n\n Are you sure you want to quit? ");
        if (answer)
            window.close();
    }
}