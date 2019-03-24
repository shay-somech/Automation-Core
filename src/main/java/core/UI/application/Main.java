package core.UI.application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage window;

    @Override
    public void start(Stage primaryStage) {
        try {
            window = primaryStage;
            window.setTitle("GINI-APPS Automation");
            Parent root = FXMLLoader.load(getClass().getResource("/view/Main.fxml"));
            Scene scene = new Scene(root, Region.USE_COMPUTED_SIZE, 500);
            window.setScene(scene);
            window.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getWindow() {
        return window;
    }
}
