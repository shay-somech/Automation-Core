package core.UI.controller.main;

import core.UI.controller.tab.advancedTab.AdvancedTabController;
import core.UI.controller.tab.homeTab.HomeTabController;
import javafx.scene.control.Accordion;
import javafx.scene.layout.VBox;

import java.io.IOException;

class MainContract {

    interface View {

        void onHomeButtonClicked() throws IOException;

        void onAdvancedSettingsButtonClicked() throws IOException;

        void closeStage();
    }

    interface Presenter {

        HomeTabController getHomeController() throws IOException;

        AdvancedTabController getAdvancedSettingsController() throws IOException;

        VBox getHomeScene() throws IOException;

        Accordion getAdvancedSettingsScene() throws IOException;

        void onHomeButtonClicked() throws IOException;

        void onAdvancedSettingsButtonClicked() throws IOException;

        void onRunButtonClicked();
    }
}
