package core.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static core.UI.Buttons.setCustomButton;
import static core.UI.Labels.setCustomLabel;

class QuitDialogBox {

    private static boolean answer;

    static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        //Create two buttons
        Button yesButton = setCustomButton("Yes", 12);
        Button noButton = setCustomButton("No", 12);

        //Clicking will set answer and close window
        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        noButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(setCustomLabel(message), yesButton, noButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 150, 150);
        window.setScene(scene);
        window.showAndWait();

        //Make sure to return answer
        return answer;
    }
}
