package core.UI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import static core.UI.Buttons.setCustomButton;
import static core.UI.Labels.setCustomLabel;

class AlertBox {

    static void display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        //Clicking will set answer and close window
        Button okButton = setCustomButton("OK", 12);
        okButton.setOnAction(e -> {
            e.consume();
            window.close();
        });

        VBox layout = new VBox(10);

        //Add buttons
        layout.getChildren().addAll(setCustomLabel(message), okButton);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout, 50, 80);
        window.setScene(scene);
        window.showAndWait();
    }
}
