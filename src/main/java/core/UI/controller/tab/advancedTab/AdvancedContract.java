package core.UI.controller.tab.advancedTab;

import core.UI.application.UiSelection;

import java.util.ArrayList;

public interface AdvancedContract {

    interface View {

        void setTestNGComboBoxDefault();

        void updateTestsClassesComboBox();

        void setAppComboBoxVisibility(boolean visibility);

        boolean isVideoRecordSelected();

        boolean isInstallAppSelected();

        void clearAppComboBoxItems();

        void setAppComboBoxApks();

        void setAppComboBoxIpas();

        void updateSelections(UiSelection uiSelection);
    }

    interface Presenter {

        ArrayList<String> getAllTestClasses();

        void initComboBoxesDefaults();

        void initTestComboBox();

        void handleVideoRecordSelection();

        void handleAppInstallationSelection();

        void updateSelection();
    }

}
