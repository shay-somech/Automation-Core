package core.UI.controller.main;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainView implements MainContract.View, Initializable {

    @FXML
    private BorderPane root;
    public Button runButton;
    private MainPresenter presenter;

    public enum UiSelections {
        PLATFORM, DEVICE, SECOND_DEVICE, NO_RESET, APP
    }

    public static Map<UiSelections, Object> uiSelection = new HashMap<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        presenter = new MainPresenter(this);
//        presenter.onRunButtonClicked();
    }

    @Override
    public void onHomeButtonClicked() throws IOException {
        root.setCenter(presenter.getHomeScene());
    }

    @Override
    public void onAdvancedSettingsButtonClicked() throws IOException {
        root.setCenter(presenter.getAdvancedSettingsScene());
    }

    @Override
    public void closeStage() {
        runButton.setOnAction(event -> {
            Stage stage = (Stage) root.getScene().getWindow();
            stage.close();
        });
    }
}
