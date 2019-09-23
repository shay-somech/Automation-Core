package core.UI.controller.tab.advancedTab;

import core.UI.application.UiSelection;
import core.UI.controller.AlertBoxController;
import core.utils.Log;
import core.utils.ScreenRecorderHelper;
import javafx.scene.control.Alert;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;

class AdvancedPresenter implements AdvancedContract.Presenter {

    private AdvancedContract.View view;
    private UiSelection uiSelection;

    AdvancedPresenter(AdvancedContract.View view) {
        this.view = view;
        uiSelection = UiSelection.getInstance();
    }

    @Override
    public ArrayList<String> getAllTestClasses() {
        ArrayList<String> classes = new ArrayList<>();
        File baseDirectory = new File(System.getProperty("user.dir") + "/src/test/java");
        String packagePath = System.getProperty("user.dir") + "/Tests";

        if (!baseDirectory.exists()) {
            return classes;
        }

        File[] files = baseDirectory.listFiles();
        assert files != null;

        for (File file : files) {
            if (file.isDirectory()) {
                assert !file.getName().contains(".");
                classes.addAll(getAllTestClasses());

            } else {
                String cls = String.format("%s.%s", packagePath, file.getName()).replace("/", ".").replace(".java", "");
                classes.add(cls);
            }
        }

        return classes;
    }

    @Override
    public void initComboBoxesDefaults() {
        view.setTestNGComboBoxDefault();
    }

    @Override
    public void initTestComboBox() {
        view.updateTestsClassesComboBox();
    }

    @Override
    public void handleVideoRecordSelection() {
        if (view.isVideoRecordSelected()) {
            Log.info("Starting Screen Recording");
            ScreenRecorderHelper screenRecorder = new ScreenRecorderHelper();

            switch (uiSelection.getPlatform()) {
                case ANDROID:
                    screenRecorder.startRecording(screenRecorder.androidRecordingOptions().withTimeLimit(Duration.ofSeconds(120)));
                    break;

                case IOS:
                    screenRecorder.startRecording(screenRecorder.iOSRecordingOptions().withTimeLimit(Duration.ofSeconds(120)));
                    break;
            }
        }
    }

    @Override
    public void handleAppInstallationSelection() {
        if (view.isInstallAppSelected()) {
            view.setAppComboBoxVisibility(true);

            switch (uiSelection.getPlatform()) {
                case ANDROID:
                    view.clearAppComboBoxItems();
                    view.setAppComboBoxApks();
                    break;

                case IOS:
                    view.clearAppComboBoxItems();
                    view.setAppComboBoxIpas();
                    break;

                default:
                    new AlertBoxController(Alert.AlertType.ERROR, "App Error", "Please select Platform before selecting App to install").showAlert();

            }

        } else {
            view.setAppComboBoxVisibility(false);
        }
    }

    @Override
    public void updateSelection() {
        view.updateSelections(uiSelection);
    }
}