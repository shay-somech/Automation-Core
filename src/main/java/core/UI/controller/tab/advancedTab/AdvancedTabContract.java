package core.UI.controller.tab.advancedTab;

import java.util.ArrayList;

public interface AdvancedTabContract {

    interface View {

        void setTestNGComboBoxDefault();

        void updateTestsClassesComboBox();

        void setAppComboBoxVisibility(boolean visibility);

        boolean isVideoRecordSelected();

        boolean isInstallAppSelected();

        void clearAppComboBoxItems();

        void setAppComboBoxApks();

        void setAppComboBoxIpas();

        void updateSelections();
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