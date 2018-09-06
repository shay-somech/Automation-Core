package core.UI;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

class Labels {

    static Label selectPlatformLabel;
    static Label selectDeviceLabel;
    static Label shouldInstallAppLabel;
    static Label selectAppToInstallLabel;
    static Label noResetLabel;
    private static Label autoInstrumentLabel;
    private static Label testRunTypeLabel;
    private static Label selectTestListLabel;

    static void initLabels() {
        selectPlatformLabel = new Label();
        selectDeviceLabel = new Label();
        shouldInstallAppLabel = new Label();
        selectAppToInstallLabel = new Label();
        autoInstrumentLabel = new Label();
        noResetLabel = new Label();
        testRunTypeLabel = new Label();
        selectTestListLabel = new Label();
    }

    static Label setCustomLabel(String message) {
        Label customLabel = new Label();
        customLabel.setText(message);
        return customLabel;
    }

    static void getMainLabels() {
        createNewLabel(testRunTypeLabel, "Select Test Run Type:");
        createNewLabel(selectTestListLabel, "Select Test to Run:");
        createNewLabel(selectPlatformLabel, "Select Platform:");
        createNewLabel(selectDeviceLabel, "Select Device:");
        createNewLabel(autoInstrumentLabel, "Instrument App:");
        createNewLabel(noResetLabel, "Keep App Data ?");
        createNewLabel(selectDeviceLabel, "Select Device:");
        createNewLabel(shouldInstallAppLabel, "Install Application ?");
        createNewLabel(selectAppToInstallLabel, "Select Application to install:");
    }

    private static void createNewLabel(Label labelName, String text) {
        Color labelColor = Color.BLACK;
        int fontSize = 20;
        String font = "Tahoma";

        labelName.setText(text);
        labelName.setTextFill(labelColor);
        labelName.setFont(new Font(font, fontSize));
        labelName.setAlignment(Pos.CENTER);
        GridPane.setConstraints(labelName, 0, 0);
    }
}
