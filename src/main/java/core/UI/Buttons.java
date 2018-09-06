package core.UI;

import javafx.scene.control.Button;
import javafx.scene.text.Font;

import static core.UI.AlertBox.display;
import static core.UI.ComboBoxes.isRequiredChoiceBoxEmpty;
import static core.UI.MainUIRunner.window;

class Buttons {

    static Button setCustomButton(String text, int fontSize) {
        Button runButton = new Button();
        runButton.setText(text);
        runButton.setFont(new Font("Tahoma", fontSize));

        return runButton;
    }

    static void setRunButtonActions(Button button) {
        button.setOnAction(event ->
        {
            if (isRequiredChoiceBoxEmpty()) {
                display("Configurations Not Set Properly", "Please set all required configurations");
            } else {
                window.close();
            }
        });
    }
}
