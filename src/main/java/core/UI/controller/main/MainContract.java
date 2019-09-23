package core.UI.controller.main;

import core.UI.controller.tab.advancedTab.AdvancedController;
import core.UI.controller.tab.homeTab.HomeController;
import javafx.scene.control.Accordion;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

class MainContract {

    interface View {

        void onHomeButtonClicked() throws IOException;

        void onAdvancedSettingsButtonClicked() throws IOException;

        void closeStage();
    }

    interface Presenter {

        HomeController getHomeController() throws IOException;

        AdvancedController getAdvancedSettingsController() throws IOException;

        AnchorPane getHomeScene() throws IOException;

        Accordion getAdvancedSettingsScene() throws IOException;

        void onHomeButtonClicked() throws IOException;

        void onAdvancedSettingsButtonClicked() throws IOException;

        void onRunButtonClicked();
    }
}
