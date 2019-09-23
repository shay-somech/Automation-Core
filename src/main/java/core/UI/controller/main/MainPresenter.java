package core.UI.controller.main;

import core.UI.controller.tab.advancedTab.AdvancedController;
import core.UI.controller.tab.homeTab.HomeController;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    private FXMLLoader loader;

    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    private FXMLLoader getScene(String xml) {
        return loader = new FXMLLoader(getClass().getResource(xml));
    }

    @Override
    public HomeController getHomeController() throws IOException {
        getScene("/view/tab/Tab1.fxml").load();
        return loader.getController();
    }

    @Override
    public AdvancedController getAdvancedSettingsController() throws IOException {
        getScene("/view/tab/Tab2.fxml").load();
        return loader.getController();
    }

    @Override
    public AnchorPane getHomeScene() throws IOException {
        return getScene("/view/tab/Tab1.fxml").load();
    }

    @Override
    public Accordion getAdvancedSettingsScene() throws IOException {
        return getScene("/view/tab/Tab2.fxml").load();
    }

    @Override
    public void onHomeButtonClicked() throws IOException {
        view.onHomeButtonClicked();
    }

    @Override
    public void onAdvancedSettingsButtonClicked() throws IOException {
        view.onAdvancedSettingsButtonClicked();
    }

    @Override
    public void onRunButtonClicked() {
        view.closeStage();
    }
}
